package me.latestion.teams.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.latestion.teams.LatestTeams;

public class ListInventory {

	private LatestTeams plugin;
	public List<Inventory> invs = new ArrayList<>();
	
	public ListInventory(LatestTeams plugin) {
		this.plugin = plugin;
		prepareInv();
	}

	public Inventory getInv() {
		return invs.get(0);
	}

	public void prepareInv() {
		invs.clear();
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Teams");
		inv.setItem(53, arrow(true));
		inv.setItem(45, arrow(false));
		inv.setItem(49, exit());
		
		int i = 0;
		for (String s : plugin.lateTeam.keySet()) {
			ItemStack item = teamStack(s);
			if (item == null) continue;
			inv.addItem(item);
			i++;
			if (i == 45) {
				Inventory set;
				set = inv;
				invs.add(set);
				inv = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Teams");
				inv.setItem(53, arrow(true));
				inv.setItem(45, arrow(false));
				inv.setItem(49, exit());
				i = 0;
			}
		}
		if (invs.size() == 0) invs.add(inv);
	}
	
	private ItemStack teamStack(String s) {
		ItemStack item = new ItemStack(Material.NAME_TAG);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.format(s));
		item.setItemMeta(meta);
		return item;
	}
	
	public ItemStack arrow(boolean t) {
		ItemStack item = new ItemStack(Material.ARROW);
		ItemMeta meta = item.getItemMeta();
		if (t) meta.setDisplayName(ChatColor.GOLD + "Next Page!");
		else meta.setDisplayName(ChatColor.GOLD + "Previous Page!");
		item.setItemMeta(meta);
		return item;
	}
	
	public ItemStack exit() {
		ItemStack item = new ItemStack(Material.BED);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BOLD + "" + ChatColor.RED + "Close");
		item.setItemMeta(meta);
		return item;
	}
}
