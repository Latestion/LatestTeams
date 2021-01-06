package me.latestion.teams.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class Utils {

	public Utils() {
		
	}
	
	public static String getStringLoc(Location loc) {
		return loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
	}
	
	public static Location getLocFromString(String ss) {
		String[] s = ss.split(",");
		Location give = new Location(Bukkit.getWorld(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]));
		return give;
	}
	
	public String getMessage(String[] args, int start, int end) {
		StringBuffer sb = new StringBuffer();
      	for (int i = start; i < end; i++) {
      		if ((i + 1) == end) sb.append(args[i]);
      		else sb.append(args[i] + " ");
      	}
      	return sb.toString();
	}
	
	public String getMessage(String[] args, int start) {
		StringBuffer sb = new StringBuffer();
      	for (int i = start; i < args.length; i++) {
      		if ((i + 2) == args.length) sb.append(args[i]);
      		else sb.append(args[i] + " ");
      	}
      	return sb.toString();
	}
	
	public static String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public String getTeamDetails(String name) {
		
		return null;
	}

}
