package com.comze_instancelabs.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class DrakonnasVoteConfig {
	
	FileConfiguration config = null;
	
	public DrakonnasVoteConfig() {
		File file = new File("plugins/DrakonnasEssentials/", "drakonnasvote.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		config = cfg;
		cfg.options().header("DrakonnasVote Configuration by InstanceLabs. You can add unlimted lines!");
		cfg.addDefault("info0", "§5You can vote here:");
		cfg.addDefault("info1", "§4");
		
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
