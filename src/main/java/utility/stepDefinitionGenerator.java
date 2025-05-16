package utility;

import java.io.File;
import java.io.FileWriter;
import java.util.List;


public class stepDefinitionGenerator {
    public void generate(String key, List<String> steps) throws Exception {
        String className = key.replace("-", "_") + "StepDefs";
        File dir = new File("src/test/java/stepdefinitions/generated");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File javaFile = new File(dir, className + ".java");
        try (FileWriter writer = new FileWriter(javaFile)) {

            writer.write("package stepdefinitions.generated;\n\n");
            writer.write("import io.cucumber.java.en.*;\n\n");
            writer.write("public class " + className + " {\n");

            for (String step : steps) {
                String annotation = "";
                String stepBody = "";

                if (step.startsWith("Given")) {
                    annotation = "@Given";
                    stepBody = step.substring(6);
                } else if (step.startsWith("When")) {
                    annotation = "@When";
                    stepBody = step.substring(5);
                } else if (step.startsWith("Then")) {
                    annotation = "@Then";
                    stepBody = step.substring(5);
                } else {
                    continue; // Skip unrecognized step
                }

                String methodName = stepBody
                        .replaceAll("[^a-zA-Z0-9]", "_")
                        .replaceAll("_+", "_")   // collapse multiple underscores
                        .replaceAll("^_+|_+$", "") // trim leading/trailing underscores
                        .toLowerCase();

                writer.write("  " + annotation + "(\"" + stepBody + "\")\n");
                writer.write("  public void " + methodName + "() {\n");
                writer.write("    // TODO: implement step\n");
                writer.write("  }\n\n");
            }

            writer.write("}\n");
        }
    }
}
