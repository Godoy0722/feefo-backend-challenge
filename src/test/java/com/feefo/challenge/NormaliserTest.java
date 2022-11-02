package com.feefo.challenge;

import com.feefo.challenge.services.normaliser.Normaliser;
import com.feefo.challenge.enums.NormalisedJobTitle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NormaliserTest {

    private Normaliser normaliser;

    @BeforeEach
    public void initialize() {
        this.normaliser = new Normaliser();
    }

    @Test
    public void should_return_software_engineer_when_java_engineer_is_provided() {
        String javaEngineer = "Java engineer";
        String normalisedTitle = this.normaliser.normalise(javaEngineer);

        Assertions.assertEquals(NormalisedJobTitle.SOFTWARE_ENGINEER.getDescription(), normalisedTitle);
    }


    @Test
    public void should_return_accountant_when_chief_accountant_is_provided() {
        String javaEngineer = "Chief Accountant";
        String normalisedTitle = this.normaliser.normalise(javaEngineer);

        Assertions.assertEquals(NormalisedJobTitle.ACCOUNTANT.getDescription(), normalisedTitle);
    }

    @Test
    public void should_throws_an_error_when_job_title_is_empty() {
        try {
            String javaEngineer = "";
            String normalisedTitle = this.normaliser.normalise(javaEngineer);
            Assertions.fail("Forced exception");
        } catch (Exception e) {
            Assertions.assertEquals("Job description shouldn't be null", e.getMessage());
        }
    }

    @Test
    public void should_throws_an_error_job_title_is_invalid() {
        try {
            String javaEngineer = "12345678";
            String normalisedTitle = this.normaliser.normalise(javaEngineer);
            Assertions.fail("Forced exception");
        } catch (Exception e) {
            Assertions.assertEquals("Invalid job description", e.getMessage());
        }
    }
}
