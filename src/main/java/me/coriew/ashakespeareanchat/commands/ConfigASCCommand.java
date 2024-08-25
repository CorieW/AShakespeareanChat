package me.coriew.ashakespeareanchat.commands;

import me.coriew.ashakespeareanchat.AShakespeareanChat;
import me.coriew.ashakespeareanchat.data.Configuration;
import me.coriew.ashakespeareanchat.data.ModelType;
import me.coriew.ashakespeareanchat.data.StyleType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ConfigASCCommand extends ASCCommand {
    public ConfigASCCommand(AShakespeareanChat plugin) {
        super(plugin, "config", "asc.config");
    }

    @Override
    public boolean onThisCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        Configuration configuration = _plugin.getConfiguration();
        StyleType styleType = configuration.getStyle();
        ModelType modelType = configuration.getModel();

        sender.sendMessage("§aCurrent style: §e" + configuration.getStyle().getReadableName());
        if (styleType == StyleType.CUSTOM) {
            sender.sendMessage("§aCustom style prompt: §e" + configuration.getPrompt());
        }

        sender.sendMessage("§aCurrent model: §e" + modelType.getApiName());
        sender.sendMessage("§aCurrent auth: §e" + (configuration.getAuth().isEmpty() ? "Not set" : "Set"));
        if (modelType == ModelType.CUSTOM) {
            sender.sendMessage("§aCustom model path: §e" + configuration.getModelUrl());
        }

        return true;
    }
}