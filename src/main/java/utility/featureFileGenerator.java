package utility;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class featureFileGenerator {
    public void generate(String key, String summary, List<String> steps) throws Exception {

        File dir = new File("features/generated");
        if (!dir.exists()) {
            dir.mkdirs(); // Create the directory structure
        }


        try (FileWriter writer = new FileWriter("features/generated/" + key + ".feature")) {
            writer.write("@" + key + "\n");
            writer.write("Feature: " + summary + "\n\n");
            writer.write("  Scenario: Auto-generated from JIRA\n");
            for (String step : steps) {
                writer.write("    " + step + "\n");
            }
        }
    }
}