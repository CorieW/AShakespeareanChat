package me.coriew.ashakespeareanchat.commands;

import me.coriew.ashakespeareanchat.AShakespeareanChat;
import me.coriew.ashakespeareanchat.data.StyleType;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ChangeASCCommand extends ASCCommand {
    public ChangeASCCommand(AShakespeareanChat plugin) {
        super(plugin, "change", "asc.change");
    }

    @Override
    public boolean onThisCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        StringBuilder styleTypes = new StringBuilder();
        for (StyleType styleType : StyleType.values()) {
            // Custom style type cannot be set from command
            if (styleType == StyleType.CUSTOM) {
                continue;
            }

            boolean isCurrentlySet = _plugin.getConfiguration().getStyle() == styleType;

            styleTypes.append(isCurrentlySet ? "§e§n" : "§e").append(styleType.toString()).append("§c, ");
        }
        styleTypes.delete(styleTypes.length() - 2, styleTypes.length());

        // No arguments
        if (args.length < 1) {
            sender.sendMessage("§cUsage: /ascchange <style type>");
            sender.sendMessage("§cAvailable style types: " + styleTypes.toString());
            return true;
        }

        // Too many arguments
        if (args.length > 1) {
            sender.sendMessage("§cToo many arguments.");
            sender.sendMessage("§cUsage: /ascchange <style type>");
            return true;
        }

        // Invalid style type
        StyleType styleType = StyleType.fromName(args[0]);
        if (styleType == null) {
            sender.sendMessage("§cInvalid style type: §e" + args[0]);
            sender.sendMessage("§cAvailable style types: " + styleTypes.toString());
            return true;
        }

        // Custom style type cannot be set from command
        if (styleType == StyleType.CUSTOM) {
            sender.sendMessage("§cCustom style types must be set in the configuration file.");
            return true;
        }

        _plugin.getConfiguration().setStyle(styleType);
        sender.sendMessage("§aConversion type set to §e" + styleType.getReadableName());

        sender.sendMessage("§aSaving configuration...");
        _plugin.getConfiguration().writeTo(_plugin.getConfig());
        _plugin.saveConfig();
        sender.sendMessage("§aConfiguration saved!");

        return true;
    }
}
