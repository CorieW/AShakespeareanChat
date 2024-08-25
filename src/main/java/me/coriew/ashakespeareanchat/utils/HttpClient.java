package me.coriew.ashakespeareanchat.utils;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.coriew.ashakespeareanchat.AShakespeareanChat;
import org.json.simple.JSONObject;

public class HttpClient {
    private final String _url;
    private final String _authToken;

    // Constructor
    public HttpClient(String url, String authToken) {
        this._url = url;
        this._authToken = authToken;
    }

    // Method to send a prompt and get the response
    public JsonNode post(String jsonPayload) throws IOException {
        // Create a URL object
        InputStream is = getInputStream(jsonPayload);
        StringBuilder response = new StringBuilder();
        try (InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            int c;
            while ((c = isr.read()) != -1) {
                response.append((char) c);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> responseMap = mapper.readValue(response.toString(), HashMap.class);
        return mapper.valueToTree(responseMap);
    }

    private InputStream getInputStream(@Nullable String jsonPayload) throws IOException {
        URL url = new URL(_url);
        // Open a connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set up the connection properties
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + _authToken);
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setDoOutput(true);

        // Write the JSON payload to the output stream
        if (jsonPayload != null)
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

        // Get the response code
        int responseCode = connection.getResponseCode();

        // Read the response
        return (responseCode == 200) ? connection.getInputStream() : connection.getErrorStream();
    }
}