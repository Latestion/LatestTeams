package me.latestion.teams.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.latestion.teams.LatestTeams;

public class DataManager {
	
	private LatestTeams plugin;
	private FileConfiguration dataConfig = null;
	private File configFile = null;
	
	private String type;
	
	public DataManager(LatestTeams plugin, String type) {
		this.plugin = plugin;
		this.type = type;
		saveDefaultConfig();
	}

	public void reloadConfig() {
		if (this.configFile == null)
			this.configFile = new File(this.plugin.getDataFolder(), type + ".yml");
		
		this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
		
		InputStream defaultStream = this.plugin.getResource(type + ".yml");
		if (defaultStream != null) {
			YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
			this.dataConfig.setDefaults(defaultConfig);
			
		}
			
	}
	
	public FileConfiguration getConfig() {
		
		if (this.dataConfig == null)
			reloadConfig();
		
		return this.dataConfig;		
	}
	
	public void saveConfig() {
		if (this.dataConfig == null || this.configFile == null)
			return;
		
		try {
			this.getConfig().save(this.configFile);
		} catch (IOException e) {
			plugin.getLogger().log(Level.SEVERE, "Could Not Save Config to" + this.configFile, e);
		
		}
	}
	
	public void saveDefaultConfig() {
		if (this.configFile == null) 
			this.configFile = new File(this.plugin.getDataFolder(), type + ".yml");
		
		if (!this.configFile.exists()) {
			this.plugin.saveResource(type + ".yml", false);
		}
	}
}
