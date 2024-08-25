package me.coriew.ashakespeareanchat.commands;

import me.coriew.ashakespeareanchat.AShakespeareanChat;
import me.coriew.ashakespeareanchat.data.Configuration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CurrentASCCommand extends ASCCommand {
    public CurrentASCCommand(AShakespeareanChat plugin) {
        super(plugin, "current", "asc.current");
    }

    @Override
    public boolean onThisCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        Configuration configuration = _plugin.getConfiguration();
        sender.sendMessage("§aCurrent style: §e" + configuration.getStyle().getReadableName());

        return true;
    }
}