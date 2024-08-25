package me.coriew.ashakespeareanchat.commands;

import me.coriew.ashakespeareanchat.AShakespeareanChat;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public abstract class ASCCommand implements CommandExecutor {
    protected final AShakespeareanChat _plugin;
    protected final String _commandPrefix = "asc";
    protected final String _commandName;
    protected final String _permission;

    public ASCCommand(AShakespeareanChat plugin, String commandName) {
        this._plugin = plugin;
        this._commandName = commandName;
        this._permission = null;
    }

    public ASCCommand(AShakespeareanChat plugin, String commandName, String permission) {
        this._plugin = plugin;
        this._commandName = commandName;
        this._permission = permission;
    }

    public void register() {
        String fullCommandName = _commandPrefix + _commandName;

        if (_plugin.getCommand(fullCommandName) == null) {
            throw new IllegalStateException("Command " + _commandName + " does not exist in plugin.yml");
        }

        _plugin.getCommand(fullCommandName).setExecutor(this);
    }

    @Override
    public final boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.Command command, @NotNull String label, String[] args) {
        String fullCommandName = _commandPrefix + _commandName;

        if (command.getName().equalsIgnoreCase(fullCommandName)) {
            if (!hasPermission(sender)) {
                sender.sendMessage("&cYou do not have permission to use this command.");
                return true;
            }

            return onThisCommand(sender, command, label, args);
        }

        return false;
    }

    public abstract boolean onThisCommand(@NotNull CommandSender sender, org.bukkit.command.Command command, @NotNull String label, String[] args);

    private boolean hasPermission(CommandSender sender) {
        boolean hasExactPermission = _permission != null && sender.hasPermission(_permission);
        boolean hasParentPermission = _permission != null && sender.hasPermission("asc.*");
        return hasExactPermission || hasParentPermission;
    }
}
