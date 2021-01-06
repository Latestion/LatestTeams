package me.latestion.teams.latest;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import me.latestion.teams.LatestTeams;

public class LatestTeam {

	private String name;
	private List<LatestPlayer> team = new ArrayList<>();
	
	public LatestPlayer leader;
	public String desc;
	
	public List<LatestPlayer> invited = new ArrayList<>();
	private LatestTeams plugin;
	
	private int value = 0;
	
	public LatestTeam(LatestTeams plugin, String name) {
		this.plugin = plugin;
		this.name = name;
	}

	public String getName() {	
		return name;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public void addPlayer(LatestPlayer player) {
		team.add(player);
		player.setTeam(this);
	}
	
	public void removePlayer(LatestPlayer player) {
		player.setTeam(null);
		team.remove(player);
	}
	
	public void setLeader(LatestPlayer p) {
		this.leader = p;
	}

	public void setDesc(String desc2) {
		this.desc = desc2;
	}
	
	public void invitePlayer(LatestPlayer player) {
		invited.add(player);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
            	if (invited.contains(player)) {
            		invited.remove(player);
            		if (player.getPlayer().isOnline()) {
            			player.getPlayer().sendMessage(""); // Invite Expired
            		}
            	}
            }            
        }, 600 * 20);
	}
	
	public void disband() {
		/*
		 * Disband Code
		 */
	}
	
	public int getValue() {
		return value;
	}

	public void addValue(int i) {
		value = value + i;
	}
	
	public void subtractValue(int i) {
		value = value - i;
	}
	
	public void setValue(int v) {
		this.value = v;
	}

	public List<LatestPlayer> getPlayers() {
		return team;
	}
	
}
