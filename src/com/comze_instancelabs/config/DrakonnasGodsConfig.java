package com.comze_instancelabs.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class DrakonnasGodsConfig {
	
	FileConfiguration config = null;
	
	public DrakonnasGodsConfig() {
		File file = new File("plugins/DrakonnasEssentials/", "drakonnasgods.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		config = cfg;
		cfg.options().header("DrakonnasGods Configuration by InstanceLabs");
		cfg.addDefault("config.broadcast", true);
		
		cfg.options().copyDefaults(true);
		cfg.options().copyHeader(true);
		try {
			cfg.save(file);
		} catch (IOException e) {
		}
	}
	
	public FileConfiguration getConfig(){
		return config;
	}
}
