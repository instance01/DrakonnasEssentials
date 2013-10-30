package com.comze_instancelabs.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Mainconfig {
	FileConfiguration config = null;
	public Mainconfig() {
		File file = new File("plugins/DrakonnasEssentials/", "config.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		config = cfg;
		cfg.options().header("DrakonnasEssentials Main Configuration by InstanceLabs");
		cfg.addDefault("components.drakonnasmotd", true);
		cfg.addDefault("components.drakonnasvotes", true);
		cfg.addDefault("components.drakonnasgods", true);
		cfg.addDefault("components.drakonnasfounddiamonds", true);
		cfg.addDefault("components.drakonnasshops", true);

		
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
