package com.feefo.challenge.interfaces;

public interface LevenshteinCalculator {
    /**
     *
     * @param jobTitle the title passed as argument for the user
     * @param normalizedJobTitle title padronized for the business rule
     * @return the number of interations necessary for transform jobTitle into normalizedJobTitle
     */
    Integer calculate(String jobTitle, String normalizedJobTitle);
}
