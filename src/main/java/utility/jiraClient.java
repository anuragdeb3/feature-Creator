package utility;

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.io.*;


public class jiraClient {
     private final String baseUrl;
    private final String authHeader;

    public jiraClient(String baseUrl, String username, String token) {
        this.baseUrl = baseUrl;
        String credentials = username + ":" + token;
        this.authHeader = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    public JSONArray fetchStories(String jql) throws Exception {
        URL url = new URL(baseUrl + "/rest/api/2/search?jql=" + jql);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", authHeader);
        conn.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JSONObject result = new JSONObject(content.toString());
        return result.getJSONArray("issues");
    }
}

