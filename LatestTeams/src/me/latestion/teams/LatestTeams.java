package me.latestion.teams;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.latestion.teams.files.DataManager;
import me.latestion.teams.latest.LatestPlayer;
import me.latestion.teams.latest.LatestTeam;
import me.latestion.teams.utils.ListInventory;

public class LatestTeams extends JavaPlugin {

	public Map<UUID, LatestPlayer> latePlayer = new HashMap<>();
	public Map<String, LatestTeam> lateTeam = new HashMap<>();
	
	public DataManager data;
	public DataManager message;
	public ListInventory inv;
	
	@Override
	public void onEnable() {
		loadFiles();
	}

	@Override
	public void onDisable() {
		
	}
	
	private void loadFiles() {
		this.saveDefaultConfig();
		this.data = new DataManager(this, "data");
		this.message = new DataManager(this, "message");
		this.inv = new ListInventory(this);
	}

	public LatestTeam getPlayerTeam(Player player) {
		return latePlayer.get(player.getUniqueId()).getTeam();
	}
	
	public LatestPlayer getLatestPlayer(Player player) {
		return latePlayer.get(player.getUniqueId());
	}
	
	public LatestTeam getPlayerTeam(UUID player) {
		return latePlayer.get(player).getTeam();
	}
	
	public LatestPlayer getLatestPlayer(UUID player) {
		return latePlayer.get(player);
	}
	
	public LatestTeam getLatestTeam(String name) {
		for (String team : lateTeam.keySet()) {
			if (team.equalsIgnoreCase(name)) {
				return lateTeam.get(team);
			}
		}
		return null;
	}
	
	public boolean doesTeamExists(String team) {
		if (getLatestTeam(team) == null) return false;
		return true;
	}
	
}
