package dev.jkm.whereami;

import dev.jkm.whereami.WhereAmIPlugin;
import dev.jkm.whereami.zone.Zone;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The /whereami command
 */
public class WhereAmICommand implements CommandExecutor {
    private WhereAmIPlugin plugin;

    public WhereAmICommand(WhereAmIPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;

        Zone zone = plugin.getZone(player.getLocation());

        if (zone == null) {
            player.sendMessage("§7You are not in a zone");
        } else {
            player.sendMessage("§7You are in §6§l" + zone.getName() + "§r");
        }

        return true;
    }
}
