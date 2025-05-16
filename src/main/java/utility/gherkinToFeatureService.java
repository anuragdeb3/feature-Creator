/*
package utility;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class gherkinToFeatureService {

    private static final String OPENAI_API_KEY = "your_openai_api_key";
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/completions";

    public String convertGherkinToFeature(String gherkinStory) throws Exception {
        // Create the request payload
        JSONObject payload = new JSONObject();
        payload.put("model", "text-davinci-003");
        payload.put("prompt", "Convert the following Gherkin story to a Cucumber feature file:\n" + gherkinStory);
        payload.put("max_tokens", 512);

        // Open a connection to the OpenAI API
        HttpURLConnection connection = (HttpURLConnection) new URL(OPENAI_API_URL).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + OPENAI_API_KEY);
        connection.setDoOutput(true);

        // Write the payload to the request body
        try (OutputStream os = connection.getOutputStream()) {
            os.write(payload.toString().getBytes());
        }

        // Read the response from the API
        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        // Parse the response to extract the generated text
        JSONObject jsonResponse = new JSONObject(response.toString());
        return jsonResponse.getJSONArray("choices").getJSONObject(0).getString("text").trim();
    }
}*/
