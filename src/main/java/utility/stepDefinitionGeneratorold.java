/*
package utility;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class stepDefinitionGeneratorold {

    public static void main(String[] args) {
        // Path to the feature file
        String featureFilePath = "src/test/resources/features/user_login.feature";
        // Path to the generated step definition file
        String stepDefinitionFilePath = "src/test/java/com/example/stepdefinitions/UserLoginStepDefinitions.java";

        try {
            // Read the feature file
            List<String> featureFileLines = Files.readAllLines(Paths.get(featureFilePath));

            // Generate step definitions
            String stepDefinitions = generateStepDefinitions(featureFileLines);

            // Write the step definitions to the step definition file
            Files.write(Paths.get(stepDefinitionFilePath), stepDefinitions.getBytes());

            System.out.println("Step definition file generated successfully: " + stepDefinitionFilePath);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public static String generateStepDefinitions(List<String> featureFileLines) {
        StringBuilder stepDefinitions = new StringBuilder();
        Set<String> uniqueSteps = new HashSet<>();

        // Package and class declaration
        stepDefinitions.append("package com.example.stepdefinitions;\n\n")
                .append("import io.cucumber.java.en.Given;\n")
                .append("import io.cucumber.java.en.When;\n")
                .append("import io.cucumber.java.en.Then;\n\n")
                .append("public class UserLoginStepDefinitions {\n\n");

        // Regex pattern to match Gherkin steps
        Pattern stepPattern = Pattern.compile("^(Given|When|Then) (.+)");

        for (String line : featureFileLines) {
            Matcher matcher = stepPattern.matcher(line.trim());
            if (matcher.find()) {
                String stepType = matcher.group(1); // Given, When, or Then
                String stepText = matcher.group(2); // Step description

                // Avoid duplicate step definitions
                if (!uniqueSteps.contains(stepText)) {
                    uniqueSteps.add(stepText);

                    // Generate method name
                    String methodName = generateMethodName(stepText);

                    // Append step definition method
                    stepDefinitions.append("    @").append(stepType).append("(\"").append(stepText).append("\")\n")
                            .append("    public void ").append(methodName).append("() {\n")
                            .append("        // TODO: Implement this step\n")
                            .append("        System.out.println(\"Step: ").append(stepText).append("\");\n")
                            .append("    }\n\n");
                }
            }
        }

        stepDefinitions.append("}\n");
        return stepDefinitions.toString();
    }

    public static String generateMethodName(String stepText) {
        // Convert step text to a valid Java method name
        return stepText.toLowerCase()
                .replaceAll("[^a-zA-Z0-9]", "_") // Replace non-alphanumeric characters with underscores
                .replaceAll("_+", "_")          // Replace multiple underscores with a single underscore
                .replaceAll("^_|_$", "");        // Remove leading/trailing underscores
    }
}*/
