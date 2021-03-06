package me.latestion.teams.commands;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.latestion.teams.LatestTeams;
import me.latestion.teams.latest.LatestPlayer;
import me.latestion.teams.latest.LatestTeam;
import me.latestion.teams.utils.ChunkClaiming;
import me.latestion.teams.utils.Utils;

public class CommandExe implements CommandExecutor {

	private LatestTeams plugin;
	
	public CommandExe(LatestTeams plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {	
		if (label.equalsIgnoreCase("t") || label.equalsIgnoreCase("team") || label.equalsIgnoreCase("teams") ) {
			
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
				
				if (plugin.doesTeamExists(name)) {
					// Name Taken
					return false;
				}
				
				if (name.length() > 15) {
					// Too long
					return false;
				}
				
				if (plugin.getLatestPlayer(player).inTeam()) {
					// Player Already In Taem
					return false;
				}
				
				LatestTeam team = new LatestTeam(plugin, name, plugin.getLatestPlayer(player));
				plugin.lateTeam.put(name, team);
				plugin.inv.prepareInv();
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
				
				if (!plugin.getLatestPlayer(player).inTeam()) {
					// Player Not In Taem
					return false;
				}
				
				if (plugin.doesTeamExists(name)) {
					// Name Taken
					return false;
				}
				
				if (plugin.getPlayerTeam(player).leader != plugin.getLatestPlayer(player)) {
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
			
			if (args[0].equalsIgnoreCase("t") || args[0].equalsIgnoreCase("team") || args[0].equalsIgnoreCase("p") || args[0].equalsIgnoreCase("player")) {
				
				if (args.length == 1) {
					if (!plugin.getLatestPlayer(player).inTeam()) {
						// Not In Team
					}
					String name = plugin.getPlayerTeam(player).getName();
					String details = util.getTeamDetails(name);
					player.sendMessage(details);
				}
				
				else {
					String name = util.getMessage(args, 1);
					if (args[0].equalsIgnoreCase("p") || args[0].equalsIgnoreCase("player")) {
						UUID id = getPlayerIDFromName(args[1]);
						if (id == null) {
							// Invalid Player
							return false;
						}
						name = plugin.getPlayerTeam(id).getName();
					}
					if (!plugin.doesTeamExists(name)) {
						// Team Dosent Exists
					}
					String details = util.getTeamDetails(name);
					player.sendMessage(details);
				}
			}
			
			if (args[0].equalsIgnoreCase("desc")) {
				
				if (args.length == 1) {
					// Format
					return false;
				}
				
				if (!plugin.getLatestPlayer(player.getUniqueId()).inTeam()) {
					// Player Not In Taem
					return false;
				}
				
				if (plugin.getPlayerTeam(player).leader != plugin.getLatestPlayer(player)) {
					// Not Leader
					return false;
				}
				
				String desc = util.getMessage(args, 1);
				plugin.getPlayerTeam(player.getUniqueId()).setDesc(desc);
				// Desc set msg	
			}			
			if (args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("inv") || args[0].equalsIgnoreCase("i")) {
				
				if (args.length == 1 || args.length == 2) {
					// Format
					return false;
				}
				
				if (!plugin.getLatestPlayer(player).inTeam()) {
					// Player Not In Taem
					return false;
				}
				
				if (plugin.getPlayerTeam(player).leader != plugin.getLatestPlayer(player.getUniqueId())) {
					// Not Leader
					return false;
				}
				
				UUID id = getPlayerIDFromName(args[2]);
				
				if (id == null) {
					// Invalid Player
					return false;
				}
				
				if (args[1].equalsIgnoreCase("a") || args[1].equalsIgnoreCase("add")) {
				
					if (plugin.getLatestPlayer(id).inTeam()) {
						// Already In Team
						return false;
					}
					
					if (plugin.getLatestPlayer(id).getPlayer().isOnline()) {
						// Invite MSG
						plugin.getPlayerTeam(player).invitePlayer(plugin.getLatestPlayer(id));
					}
				}
				
				if (args[1].equalsIgnoreCase("remove") || args[1].equalsIgnoreCase("r")) {
					
					if (args[2].equals("all")) {
						plugin.getPlayerTeam(player).invited.clear();
						return false;
					}
					
					if (plugin.getPlayerTeam(player).invited.contains(plugin.getLatestPlayer(id))) {
						plugin.getPlayerTeam(player).invited.remove(plugin.getLatestPlayer(id));
						// Remove Message
					}
					else {
						// Does Not Contain
						return false;
					}
				}
				
				if (args[1].equalsIgnoreCase("l") || args[1].equalsIgnoreCase("list")) {
					// Send Player Message
				}
			}
			if (args[0].equalsIgnoreCase("join")) {

				if (args.length == 1) {
					// Format
					return false;
				}
				
				String name = util.getMessage(args, 1);
				
				if (!plugin.doesTeamExists(name)) {
					// Team Dosent Exists
					return false;
				}
				
				if (plugin.getLatestTeam(name).invited.contains(plugin.getLatestPlayer(player))) {
					// Accpeted
					plugin.getLatestTeam(name).invited.remove(plugin.getLatestPlayer(player));
					plugin.getLatestTeam(name).addPlayer(plugin.getLatestPlayer(player));
				}
				else {
					// Not Invited
				}
			}
			if (args[0].equalsIgnoreCase("leave")) {
				
				if (!plugin.getLatestPlayer(player).inTeam()) {
					// Player Not In Taem
					return false;
				}
				
				if (plugin.getPlayerTeam(player).leader == plugin.getLatestPlayer(player.getUniqueId())) {
					// Leader
					return false;
				}
				
				// Send Message
				plugin.getPlayerTeam(player).removePlayer(plugin.getLatestPlayer(player));
				
			}
			if (args[0].equalsIgnoreCase("disband")) {
			
				if (!plugin.getLatestPlayer(player).inTeam()) {
					// Player Not In Taem
					return false;
				}
				
				if (plugin.getPlayerTeam(player).leader != plugin.getLatestPlayer(player.getUniqueId())) {
					// Not Leader
					return false;
				}
				
				// Send Message
				plugin.getPlayerTeam(player).disband();
				
			}
			if (args[0].equalsIgnoreCase("money")) {
				
				if (args.length == 1) {
					
					if (!plugin.getLatestPlayer(player).inTeam()) {
						// Player Not In Taem
						return false;
					}
					
					int i = plugin.getPlayerTeam(player).getValue();
					// Send Msg

					return false;
				}
				
				String name = util.getMessage(args, 1);
				int i = plugin.getLatestTeam(name).getValue();
				// Send Msg
			}
			if (args[0].equalsIgnoreCase("claim")) {
				
				if (args.length == 1) {
					// Format
					return false;
				}
				
				if (!plugin.getLatestPlayer(player).inTeam()) {
					// Player Not In Taem
					return false;
				}
				
				LatestTeam team = plugin.getPlayerTeam(player);
				LatestPlayer lplayer = plugin.getLatestPlayer(player);
				
				if (team.leader != lplayer) {
					// Not Leader
					return false;
				}
				
				if (args[1].equalsIgnoreCase("o") || args[1].equalsIgnoreCase("one")) {
					
					Location loc = player.getLocation();
					ChunkClaiming claim = new ChunkClaiming(plugin);
					
					if (claim.isChunkClaimed(loc)) {
						// Chunk Already Claimed
						return false;
					}
					
					if (team.getTeamChunks().size() < plugin.getConfig().getInt("Max-Chunk-Claim-Limit")) {
						// Claimed Chunk
						team.addChunk(loc.getChunk());
					}
					
					else {
						// Already At Max Limit
						return false;
					}
					
				}
				
				if (args[1].equalsIgnoreCase("a") || args[1].equalsIgnoreCase("auto")) {		
					if (lplayer.autoUnclaiming) {
						// Already Unclaiming
						return false;
					}
					
					if (team.getTeamChunks().size() == plugin.getConfig().getInt("Max-Chunk-Claim-Limit")) {
						// Already At Max Limit
						return false;
					}
					
					// Send Message
					lplayer.toggleClaiming();				
				}
			}
			if (args[0].equalsIgnoreCase("unclaim")) {
				
				if (args.length == 1) {
					// Format
					return false;
				}
				

				if (!plugin.getLatestPlayer(player).inTeam()) {
					// Player Not In Taem
					return false;
				}
				
				LatestTeam team = plugin.getPlayerTeam(player);
				LatestPlayer lplayer = plugin.getLatestPlayer(player);
				
				if (team.leader != lplayer) {
					// Not Leader
					return false;
				}
				
				if (args[1].equalsIgnoreCase("o") || args[1].equalsIgnoreCase("one")) {
					
					Location loc = player.getLocation();
					ChunkClaiming claim = new ChunkClaiming(plugin);
					
					if (!claim.isChunkClaimed(loc)) {
						// Chunk Is Not Claimed
						return false;
					}
					
					if (team.getTeamChunks().contains(loc.getChunk())) {
						// Unclaimed Message
						team.removeChunk(loc.getChunk());
					}
					
					else {
						// They Dont Own
						return false;
					}
					
				}
				
				if (args[1].equalsIgnoreCase("a") || args[1].equalsIgnoreCase("auto")) {
					if (lplayer.autoClaiming) {
						// Already Claiming
						return false;
					}
					
					if (team.getTeamChunks().size() == 0) {
						// They Dont Have Any Chunks
						return false;
					}
					
					// Send Message
					lplayer.toggleUnClaiming();		
				}
				if (args[1].equalsIgnoreCase("all")) {
					// Send Message
					plugin.getPlayerTeam(player).clearChunk();
				}
			}
			if (args[0].equalsIgnoreCase("top")) {
				
				if (args.length == 1) {
					// format
					return false;
				}
				
				int i = util.getInt(args[1]);
				
				if (i == 0) {
					// Format
					return false;
				}
				
				
				
			}
			if (args[0].equalsIgnoreCase("raid")) {
				
				if (args.length == 1) {
					// Format 
					return false;
				}
				
				String name = util.getMessage(args, 1);
				
				if (!plugin.doesTeamExists(name)) {
					// Invalid
					return false;
				}
				
				if (name.equalsIgnoreCase(plugin.getPlayerTeam(player).getName())) {
					// Same Team Cant Raid
					return false;
				}
				
				
				
			}
			if (args[0].equalsIgnoreCase("chat")) {
				
				if (!plugin.getLatestPlayer(player).inTeam()) {
					// Player Not In Taem
					return false;
				}
				
				if (args.length == 1) {
					// Send message
					plugin.getLatestPlayer(player).toggleFChat();
					return false;
				}
				
				if (args[1].equalsIgnoreCase("toggle")) {
					// Send message
					plugin.getLatestPlayer(player).toggleFChat();
					return false;
				}
				
				String message = util.getMessage(args, 2);
				for (LatestPlayer p : plugin.getPlayerTeam(player).getPlayers()) {
					// Send Message
				}	
			}
			if (args[0].equalsIgnoreCase("leader")) {
				
				if (args.length == 1) {
					// Format
					return false;
				}
				
				String name = args[1];
				UUID id = getPlayerIDFromName(name);
				LatestPlayer lplayer = plugin.getLatestPlayer(id);
				
				if (player.getUniqueId() == id) {
					// Format
					return false;
				}
				
				if (!plugin.getLatestPlayer(player).inTeam()) {
					// Player Not In Team
					return false;
				}
				
				if (!lplayer.inTeam()) {
					// Player Not In Team
					return false;
				}
				
				LatestTeam team = plugin.getPlayerTeam(player);
				
				if (team.leader != plugin.getLatestPlayer(player.getUniqueId())) {
					// Not Leader
					return false;
				}
				
				if (!team.getPlayers().contains(lplayer)) {
					// Player Not in same team
					return false;
				}
				
				// Send Message
				team.setLeader(lplayer);

			}
		}
		return false;
	}
	
	public UUID getPlayerIDFromName(String name) {
		return UUID.fromString(plugin.data.getConfig().getString("names." + name));
	}
}
