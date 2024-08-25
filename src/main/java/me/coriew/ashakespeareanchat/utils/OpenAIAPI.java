package me.coriew.ashakespeareanchat.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.coriew.ashakespeareanchat.AShakespeareanChat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * An API wrapper for the OpenAI API.
 */
public class OpenAIAPI {
    private final String _apiKey;
    private final String _model;

    public OpenAIAPI(String apiKey, String model) {
        this._apiKey = apiKey;
        this._model = model;
    }

    public String sendPrompt(String prompt, String message) throws IOException {
        HttpClient client = new HttpClient("https://api.openai.com/v1/chat/completions", _apiKey);

        // Create the data
        // Surround the message in quotes
        message = "\"" + message + "\"";
        Message[] messages = new Message[] {
            new Message("system", prompt),
            new Message("user", message)
        };

        // Create the body
        JSONObject body = new JSONObject();
        body.put("model", _model);
        body.put("temperature", 0);
        body.put("top_p", 0);
        body.put("messages", messages);
        JSONArray messagesJsonArr = new JSONArray();
        for (Message messageItem : messages) {
            messagesJsonArr.add(messageItem.toJSONObject());
        }
        body.put("messages", messagesJsonArr);

        // Send the request
        JsonNode response = client.post(body.toJSONString());

        // Get the content from the response
        String content = response.get("choices").get(0).get("message").get("content").asText();

        // Remove the quotes from the content if they exist
        if (content.startsWith("\"")) {
            content = content.substring(1);
        }
        if (content.endsWith("\"")) {
            content = content.substring(0, content.length() - 1);
        }

        return content;
    }

    private class Message {
        private final String role;
        private final String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public JSONObject toJSONObject() {
            JSONObject obj = new JSONObject();
            obj.put("role", role);
            obj.put("content", content);
            return obj;
        }
    }
}
