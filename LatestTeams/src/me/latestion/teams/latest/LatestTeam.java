package me.latestion.teams.latest;

import java.util.ArrayList;
import java.util.List;

public class LatestTeam {

	private String name;
	private List<LatestPlayer> team = new ArrayList<>();
	
	public LatestPlayer leader;
	
	public LatestTeam(String name) {
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
	
	public void setLeader(LatestPlayer p) {
		this.leader = p;
	}
	
}
