package com.feefo.challenge.enums;

public enum NormalisedJobTitle {

    ARCHITECT("Architect"),
    SOFTWARE_ENGINEER("Software engineer"),
    QUANTITY_SURVEYOR("Quantity surveyor"),
    ACCOUNTANT("Accountant");

    private String description;

    NormalisedJobTitle(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
