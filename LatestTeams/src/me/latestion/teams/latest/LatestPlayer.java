package me.latestion.teams.latest;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LatestPlayer {

	private LatestTeam team;
	private UUID id;
	
	public boolean inCombat = false;
	
	public LatestPlayer() {
		
	}
	
	public LatestTeam getTeam() {
		return team;
	}

	public boolean inTeam() {
		return !(team == null);
	}
	
	public UUID getPlayerUUID() {	
		return id;
	}
	
	public Player getPlayer() {
		return Bukkit.getPlayer(id);
	}
	
	public void setID(UUID uuid) {
		this.id = uuid;
	}
	
	public void setTeam(LatestTeam t) {
		this.team = t;
	}
	
	public void removeTeam() {
		this.team = null;
	}
	
}
