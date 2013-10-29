package com.comze_instancelabs.drakonnasessentials;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.comze_instancelabs.commands.Info;
import com.comze_instancelabs.commands.Rocket;
import com.comze_instancelabs.commands.motd;
import com.comze_instancelabs.utils.Utils;


/*
 * @author InstanceLabs
 */

public class Main extends JavaPlugin {

	@Override
	public void onEnable(){
		getLogger().info("**%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%**");
		getLogger().info("DrakonnasEssentials starting.. WITH LOVE REGARDS FROM INSTANCELABS <3");
		// config
		getLogger().info("Loading configuration..");
		FileConfiguration m = Utils.loadMainConfiguration();
		Utils.loadDrakonnasMotdConfiguration();
		//commands
		//TODO: add all commands into plugin.yml
		getLogger().info("Loading commands..");
		getCommand("info").setExecutor(new Info(this));
		getCommand("version").setExecutor(new Info(this));
		getCommand("motd").setExecutor(new motd(this));
		getCommand("rocket").setExecutor(new Rocket(this));
		// listener
		getLogger().info("Loading Listener..");
		PluginManager pm = getServer().getPluginManager();
		//pm.registerEvents(new JumpPadListener(this), this);
		getLogger().info("Finished loading. Have fun <3");
		getLogger().info("**%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%**");
	}
	
	@Override
	public void onDisable(){
		
	}
	
}
