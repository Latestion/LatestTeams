package me.latestion.teams;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
		this.data = new DataManager(this, "data");
		this.message = new DataManager(this, "message");
		this.inv = new ListInventory(this);
	}

}
