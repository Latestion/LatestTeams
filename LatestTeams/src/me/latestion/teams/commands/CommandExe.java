package me.latestion.teams.commands;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.latestion.teams.LatestTeams;
import me.latestion.teams.utils.Utils;

public class CommandExe implements CommandExecutor {

	private LatestTeams plugin;
	
	public CommandExe(LatestTeams plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {	
		if (label.equalsIgnoreCase("faction") || label.equalsIgnoreCase("factions") || label.equalsIgnoreCase("f") ) {
			
			if (args.length == 0) {
				/*
				 * Send Commands Here
				 */
				return false;
			}
			
			if (!(sender instanceof Player)) {
				// Need to be player
				return false;
			}
			
			Player player = (Player) sender;
			Utils util = new Utils();
			
			if (args[0].equalsIgnoreCase("create")) {
				
				if (args.length == 1) {
					// Format
					return false;
				}
				
				String name = util.getMessage(args, 1);
				
				if (plugin.lateTeam.containsKey(name)) {
					// Name Taken
					return false;
				}
				
				if (name.length() > 15) {
					// Too long
					return false;
				}
				
				if (plugin.latePlayer.get(player.getUniqueId()).inTeam()) {
					// Player Already In Taem
					return false;
				}
				
				/*
				 * Create Team
				 * Inv add team as stack
				 */
				
			}
			
			if (args[0].equalsIgnoreCase("name")) {
				
				if (args.length == 1) {
					// Format
					return false;
				}
				
				String name = util.getMessage(args, 1);
				
				if (name.length() > 15) {
					// Too long
					return false;
				}
				
				if (!plugin.latePlayer.get(player.getUniqueId()).inTeam()) {
					// Player Not In Taem
					return false;
				}
				
				if (plugin.lateTeam.containsKey(name)) {
					// Name Taken
					return false;
				}
				
				if (plugin.latePlayer.get(player.getUniqueId()).getTeam().leader != plugin.latePlayer.get(player.getUniqueId())) {
					// Not Leader
					return false;
				}
				
				/*
				 * Change Name
				 */
				
			}
			
			if (args[0].equalsIgnoreCase("list")) {
				player.openInventory(plugin.inv.getInv());	
			}
			
			if (args[0].equalsIgnoreCase("f") || args[0].equalsIgnoreCase("faction") || args[0].equalsIgnoreCase("p") || args[0].equalsIgnoreCase("player")) {
				
				if (args.length == 1) {
					if (!plugin.latePlayer.get(player.getUniqueId()).inTeam()) {
						// Not In Team
					}
					String name = plugin.latePlayer.get(player.getUniqueId()).getTeam().getName();
					String details = util.getTeamDetails(name);
					player.sendMessage(details);
				}
				
				else {
					String name = util.getMessage(args, 1);
					if (args[0].equalsIgnoreCase("p") || args[0].equalsIgnoreCase("player")) {
						name = plugin.latePlayer.get(getPlayerIDFromName(args[1])).getTeam().getName();
					}
					if (!plugin.lateTeam.containsKey(name)) {
						// Team Dosent Exists
					}
					String details = util.getTeamDetails(name);
					player.sendMessage(details);
				}
			}
			
			
			
		}
		return false;
	}
	
	public UUID getPlayerIDFromName(String name) {
		return UUID.fromString(plugin.data.getConfig().getString("names." + name));
	}
}
