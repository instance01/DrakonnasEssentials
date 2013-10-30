package com.comze_instancelabs.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import com.comze_instancelabs.config.DrakonnasGodsConfig;
import com.comze_instancelabs.config.DrakonnasMotdConfig;
import com.comze_instancelabs.config.DrakonnasVoteConfig;
import com.comze_instancelabs.config.Mainconfig;

public class Utils {

	public static String newline = System.getProperty("line.separator");
	
	public static StringBuilder getVersionInfo(Plugin p){
		StringBuilder n = new StringBuilder();
		n.append("§2DrakonnasEssentials§3 was developed by InstanceLabs with best love regards <3" + newline);
		n.append("§3This is version§5 " + p.getDescription().getVersion() + newline);
		n.append("§3DrakonnasEssentials combines a few cool plugins made for Drakonnas into one big one and is extended from time to time to new needs of Drakonnas." + newline);
		n.append("§4Have fun <3");
		return n;
	}
	
	public static StringBuilder getOSInfo(){
		StringBuilder n = new StringBuilder();
		String osname = System.getProperty("os.name");
		String osarch = System.getProperty("os.arch");
		String osversion = System.getProperty("os.version");
		String java_version = System.getProperty("java.version");
		n.append("§3OS Name: " + osname + newline);
		n.append("§3OS Arch: " + osarch + newline);
		n.append("§3OS Version: " + osversion + newline);
		n.append("§3Java Version: " + java_version + newline);
		return n;
	}
	
	public static StringBuilder getAllCommands(Plugin p){
		StringBuilder n = new StringBuilder();
		for(String cmd : p.getDescription().getCommands().keySet()){
			n.append("§a" + cmd + " §7| " + p.getDescription().getCommands().get(cmd).toString() + newline);
		}
		return n;
	}
	
	public static StringBuilder getHelp(Plugin p){
		StringBuilder n = new StringBuilder();
		n.append("§3DrakonnasEssentials Help: ");
		n.append(getAllCommands(p));
		return n;
	}
	
	public static FileConfiguration loadMainConfiguration(){
		Mainconfig m = new Mainconfig();
		return m.getConfig();
	}
	
	public static FileConfiguration loadDrakonnasMotdConfiguration(){
		DrakonnasMotdConfig m = new DrakonnasMotdConfig();
		return m.getConfig();
	}
	
	public static FileConfiguration loadDrakonnasVoteConfiguration(){
		DrakonnasVoteConfig m = new DrakonnasVoteConfig();
		return m.getConfig();
	}
	
	public static FileConfiguration loadDrakonnasGodsConfiguration(){
		DrakonnasGodsConfig m = new DrakonnasGodsConfig();
		return m.getConfig();
	}
	
	public static FileConfiguration getDrakonnasMotdConfiguration(){
		File file = new File("plugins/DrakonnasEssentials/", "drakonnasmotd.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		return cfg;
	}
	
	public static FileConfiguration getDrakonnasVoteConfiguration(){
		File file = new File("plugins/DrakonnasEssentials/", "drakonnasvote.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		return cfg;
	}
	
	public static FileConfiguration getDrakonnasGodsConfiguration(){
		File file = new File("plugins/DrakonnasEssentials/", "drakonnasgods.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		return DrakonnasGodsConfig.getConfig();
	}
	
	public static void saveDrakonnasGodsConfiguration(){
		DrakonnasGodsConfig.saveConfig();
	}
	
	public static void reloadConfiguration(File f){
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		try {
			cfg.load(f);
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("Error while reloading: File not found!");
		} catch (IOException e) {
			System.out.println("Error while reloading: " + e.getMessage());
		} catch (InvalidConfigurationException e) {
			System.out.println("Error while reloading: Invalid Configuration at " + f.getName());
		}
	}
	
	public static void saveConfiguration(File f){
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		try {
			cfg.save(f);
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			//System.out.println("Error while saving: File not found!");
		} catch (IOException e) {
			//System.out.println("Error while saving: " + e.getMessage());
		}
	}
}
