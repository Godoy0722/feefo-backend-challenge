package com.feefo.challenge.services.normaliser;

import com.feefo.challenge.interfaces.LevenshteinCalculator;
import com.feefo.challenge.services.levenshtein.OverlappingLevenshteinDistance;
import com.feefo.challenge.enums.NormalisedJobTitle;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Guilherme Henrique Lemes de Godoy
 *
 * @implNote Class used for normalize job titles passed as arguments
 *
 */
public class Normaliser {

    private final LevenshteinCalculator levenshteinDistance = new OverlappingLevenshteinDistance();
    private String normalisedJob;


    /**
     *
     * @param jobTitle the title passed as argument for the user
     * @return The job title padronized for the business rule and closest to all normalized jobs.
     */
    public String normalise(String jobTitle) {
        String sanitizedJobTitle = jobTitle.trim();

        if (sanitizedJobTitle.isEmpty()) {
            throw new NullPointerException("Job description shouldn't be null");
        }

        if (sanitizedJobTitle.matches("[0-9]+")) {
            throw new IllegalArgumentException("Invalid job description");
        }

        Double mainQualityScore = 0.0;

        List<String> normalisedJobTitleDescriptions = this.getNormalizedJobDescriptionsList();

        for (String normalisedJobTitle : normalisedJobTitleDescriptions) {
            Integer operationsForNormalisedJobTitle = this.levenshteinDistance
                    .calculate(sanitizedJobTitle, normalisedJobTitle);

            Integer maxJobTitleLength = Math.max(normalisedJobTitle.length(), sanitizedJobTitle.length());
            Double normalizedJobTitleQualityScore = this.getJobTitleQualityScore(operationsForNormalisedJobTitle,
                    maxJobTitleLength);

            if (mainQualityScore >= normalizedJobTitleQualityScore) {
                continue;
            }

            mainQualityScore = normalizedJobTitleQualityScore;
            this.normalisedJob = normalisedJobTitle;
        }

        return this.normalisedJob;
    }

    private List<String> getNormalizedJobDescriptionsList() {
        return Arrays.stream(NormalisedJobTitle.values()).map(NormalisedJobTitle::getDescription)
                .collect(Collectors.toList());
    }

    private Double getJobTitleQualityScore(Integer numberOfOperations, Integer jobTitleLength) {
        Double qualityScore = (double) ((numberOfOperations * 100) / jobTitleLength) / 100;
        return (double) 1 - qualityScore;
    }
}
