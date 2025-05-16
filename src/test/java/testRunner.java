import utility.descriptionParser;
import utility.featureFileGenerator;
import utility.stepDefinitionGenerator;

import java.util.List;

public class testRunner {

    public static void main(String[] args) throws Exception {
        String mockStoryKey = "TEST-001";
        String mockSummary = "User password reset feature";
        String mockDescription = """
            As a user, I should be able to reset my password.
            When I click on the forgot password link.
            Then I should receive a reset email.
        """;

        descriptionParser parser = new descriptionParser();
        List<String> steps = parser.parseToSteps(mockDescription);

        featureFileGenerator featureGen = new featureFileGenerator();
        featureGen.generate(mockStoryKey, mockSummary, steps);
        stepDefinitionGenerator stepGen = new stepDefinitionGenerator();
        stepGen.generate(mockStoryKey, steps);

        System.out.println("Mock feature and step definition generated.");
    }
}
