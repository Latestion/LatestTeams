package me.latestion.teams.utils;

import org.bukkit.Chunk;
import org.bukkit.Location;

import me.latestion.teams.LatestTeams;
import me.latestion.teams.latest.LatestTeam;

public class ChunkClaiming {

	private LatestTeams plugin;
	
	public ChunkClaiming(LatestTeams plugin) {
		this.plugin = plugin;
	}
	
	public boolean isChunkClaimed(Chunk c) {
		for (LatestTeam team : plugin.lateTeam.values()) {
			if (team.getTeamChunks().contains(c)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isChunkClaimed(Location loc) {
		Chunk c = loc.getChunk();
		for (LatestTeam team : plugin.lateTeam.values()) {
			if (team.getTeamChunks().contains(c)) {
				return true;
			}
		}
		return false;
	}
	
}
