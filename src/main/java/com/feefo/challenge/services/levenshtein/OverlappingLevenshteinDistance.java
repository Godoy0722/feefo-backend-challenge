package com.feefo.challenge.services.levenshtein;

import com.feefo.challenge.interfaces.LevenshteinCalculator;

import java.util.Arrays;

/**
 * @author Guilherme Henrique Lemes de Godoy
 *
 * @implNote Implementation of Levenshtein Distance using the overlapping theory
 */
public class OverlappingLevenshteinDistance implements LevenshteinCalculator {

    public Integer calculate(String jobTitle, String normalizedJobTitle) {
        if (jobTitle.isEmpty()) {
            return normalizedJobTitle.length();
        }

        if (normalizedJobTitle.isEmpty()) {
            return jobTitle.length();
        }

        Integer[][] overlappingMatrix = new Integer[jobTitle.length() + 1][normalizedJobTitle.length() + 1];

        for (int i = 0; i <= jobTitle.length(); i++) {
            for (int j = 0; j <= normalizedJobTitle.length(); j++) {
                if (i == 0) {
                    overlappingMatrix[i][j] = j;
                } else if (j == 0) {
                    overlappingMatrix[i][j] = i;
                } else {
                    Integer substitutionCost = overlappingMatrix[i - 1][j - 1] + costOfSubstitution(
                            String.valueOf(jobTitle.charAt(i - 1)),
                            String.valueOf(normalizedJobTitle.charAt(j - 1))
                    );

                    Integer deletionCost = overlappingMatrix[i - 1][j] + 1;
                    Integer insertionCost = overlappingMatrix[i][j - 1] + 1;

                    overlappingMatrix[i][j] = this.min(substitutionCost, deletionCost, insertionCost);
                }
            }
        }

        return overlappingMatrix[jobTitle.length()][normalizedJobTitle.length()];
    }

    private Integer costOfSubstitution(String a, String b) {
        return a.equals(b) ? 0 : 1;
    }

    private Integer min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }
}
