package me.latestion.teams.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import me.latestion.teams.LatestTeams;

public class EntityDamage implements Listener {

	private LatestTeams plugin;
	
	public EntityDamage(LatestTeams plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	public void enDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			ItemStack[] contents = player.getInventory().getArmorContents();
			if (contents[0] == null || contents[1] == null || contents[3] == null) return;
			if (contents[0].getTypeId() == plugin.getConfig().getInt("Spetnaz-ID.helmet") 
					&& contents[1].getTypeId() == plugin.getConfig().getInt("Spetnaz-ID.chest") 
						&& contents[3].getTypeId() == plugin.getConfig().getInt("Spetnaz-ID.boots")) { 
				if (contents[2] != null) {
					if (contents[2].getType() != Material.AIR) {
						/*
						 * Player. BAN
						 */
					}
				}
			}
		}
	}
}
