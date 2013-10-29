package com.comze_instancelabs.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class DrakonnasMotdConfig {
	
	FileConfiguration config = null;
	
	public DrakonnasMotdConfig() {
		File file = new File("plugins/DrakonnasEssentials/", "drakonnasmotd.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		config = cfg;
		cfg.options().header("DrakonnasMotd Configuration by InstanceLabs. You can add unlimted lines!");
		cfg.addDefault("motd0", "§5Welcome to Drakonnas!");
		cfg.addDefault("motd1", "§4");
		
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
