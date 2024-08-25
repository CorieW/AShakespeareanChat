package me.coriew.ashakespeareanchat;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.coriew.ashakespeareanchat.commands.ChangeASCCommand;
import me.coriew.ashakespeareanchat.commands.ConfigASCCommand;
import me.coriew.ashakespeareanchat.commands.CurrentASCCommand;
import me.coriew.ashakespeareanchat.commands.ReloadASCCommand;
import me.coriew.ashakespeareanchat.data.Configuration;
import me.coriew.ashakespeareanchat.data.StyleType;
import me.coriew.ashakespeareanchat.data.ModelType;
import me.coriew.ashakespeareanchat.utils.HttpClient;
import me.coriew.ashakespeareanchat.utils.OpenAIAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public final class AShakespeareanChat extends JavaPlugin implements Listener {
    private static AShakespeareanChat _plugin;

    private Configuration _configuration;

    @Override
    public void onEnable() {
        _plugin = this;

        // Register commands
        new CurrentASCCommand(this).register();
        new ChangeASCCommand(this).register();
        new ConfigASCCommand(this).register();
        new ReloadASCCommand(this).register();

        // Save the default configuration file if it does not exist
        saveDefaultConfig();

        // Load the configuration file
        FileConfiguration config = getConfig();
        _configuration = Configuration.fromConfig(config);

        // Register the event listener
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    private void onChat(AsyncChatEvent e) {
        // Get the message
        Component msgComp = e.message();
        TextComponent msgTextComponent = (TextComponent)msgComp;
        String message = msgTextComponent.content();

        // Convert the message
        String convertedMessage = convertMessage(message, 0);

        // Send message as player
        msgTextComponent = msgTextComponent.content(convertedMessage);
        e.message(msgTextComponent);
    }

    private String convertMessage(String message, int retryCount) {
        StyleType styleType = _configuration.getStyle();

        // Skip conversion if style is set to none
        if (styleType == StyleType.NONE) {
            return message;
        }

        String prompt = styleType.getPrompt();
        // Use custom prompt if style is set to custom
        if (styleType == StyleType.CUSTOM) {
            prompt = _configuration.getPrompt();
        }

        ModelType modelType = _configuration.getModel();
        String convertedMessageText = "";
        try {
            switch (modelType) {
                case GPT4OMINI:
                case GPT4O:
                    OpenAIAPI api = new OpenAIAPI(_configuration.getAuth(), _configuration.getModel().getApiName());
                    convertedMessageText = api.sendPrompt(prompt, message);
                    break;
                case CUSTOM:
                    HttpClient client = new HttpClient(_configuration.getModelUrl(), _configuration.getAuth());
                    JSONObject body = new JSONObject();
                    body.put("prompt", prompt);
                    body.put("message", message);
                    convertedMessageText = client.post(body.toJSONString()).get("message").asText();
                    break;
            }
        } catch (IOException e) {
            this.getLogger().warning("Failed to convert message: " + message);
            int _conversionRetryLimit = 3;
            if (retryCount < _conversionRetryLimit) {
                convertMessage(message, retryCount + 1);
            }
        }

        return convertedMessageText;
    }

    @Override
    public void onDisable() {
        // Save the configuration file
        _configuration.writeTo(getConfig());
    }

    public static AShakespeareanChat getPlugin() {
        return _plugin;
    }

    public Configuration getConfiguration() {
        return _configuration;
    }

    public void setConfiguration(Configuration configuration) {
        _configuration = configuration;
    }
}
