package com.feefo.challenge;

import com.feefo.challenge.services.normaliser.Normaliser;

public class Application {
    public static void main(String[] args) {
        String jt = "Java engineer";
        Normaliser n = new Normaliser();

        String normalisedTitle = n.normalise(jt);
        System.out.println("input: " + jt + " | output: " + normalisedTitle);

        jt = "C# engineer";
        normalisedTitle = n.normalise(jt);
        System.out.println("input: " + jt + " | output: " + normalisedTitle);

        jt = "Chief Accountant";
        normalisedTitle = n.normalise(jt);
        System.out.println("input: " + jt + " | output: " + normalisedTitle);
    }
}
