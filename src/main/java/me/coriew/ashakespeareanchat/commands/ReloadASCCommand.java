package me.coriew.ashakespeareanchat.commands;

import me.coriew.ashakespeareanchat.AShakespeareanChat;
import me.coriew.ashakespeareanchat.data.Configuration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadASCCommand extends ASCCommand {
    public ReloadASCCommand(AShakespeareanChat plugin) {
        super(plugin, "reload", "asc.reload");
    }

    @Override
    public boolean onThisCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        // Reload the configuration
        _plugin.reloadConfig();
        sender.sendMessage("§aConfiguration reloaded!");

        _plugin.setConfiguration(Configuration.fromConfig(_plugin.getConfig()));

        return true;
    }
}