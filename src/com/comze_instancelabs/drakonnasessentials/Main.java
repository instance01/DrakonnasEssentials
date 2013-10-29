package com.comze_instancelabs.drakonnasessentials;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.comze_instancelabs.commands.Endercrystal;
import com.comze_instancelabs.commands.Gods;
import com.comze_instancelabs.commands.Info;
import com.comze_instancelabs.commands.OSInfo;
import com.comze_instancelabs.commands.Rocket;
import com.comze_instancelabs.commands.fdstats;
import com.comze_instancelabs.commands.motd;
import com.comze_instancelabs.commands.vote;
import com.comze_instancelabs.listener.DrakonnasFounddiamonds;
import com.comze_instancelabs.listener.DrakonnasGods;
import com.comze_instancelabs.listener.DrakonnasShops;
import com.comze_instancelabs.listener.Mainlistener;
import com.comze_instancelabs.utils.Utils;


/*
 * @author InstanceLabs
 */

public class Main extends JavaPlugin {
	
	public static Economy econ = null;
	
	@Override
	public void onEnable(){
		// setup economy
		if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - No iConomy dependency found! Disabling Economy.", getDescription().getName()));
        }
		
		// load plugin
		getLogger().info("**%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%**");
		getLogger().info("DrakonnasEssentials starting.. WITH LOVE REGARDS FROM INSTANCELABS <3");
		// config
		getLogger().info("Loading configuration..");
		//TODO: disable components if disabled in main config
		FileConfiguration m = Utils.loadMainConfiguration();
		Utils.loadDrakonnasMotdConfiguration();
		Utils.loadDrakonnasVoteConfiguration();
		Utils.loadDrakonnasGodsConfiguration();
		//commands
		getLogger().info("Loading commands..");
		getCommand("info").setExecutor(new Info(this));
		getCommand("motd").setExecutor(new motd(this));
		getCommand("rocket").setExecutor(new Rocket(this));
		getCommand("endercrystal").setExecutor(new Endercrystal(this));
		getCommand("fdstats").setExecutor(new fdstats(this));
		getCommand("gods").setExecutor(new Gods(this));
		getCommand("osinfo").setExecutor(new OSInfo(this));
		getCommand("votes").setExecutor(new vote(this));
		// listener
		getLogger().info("Loading Listener..");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Mainlistener(this), this);
		pm.registerEvents(new DrakonnasShops(econ, this), this);
		pm.registerEvents(new DrakonnasFounddiamonds(this), this);
		pm.registerEvents(new DrakonnasGods(this), this);
		getLogger().info("Finished loading. Have fun!");
		getLogger().info("**%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%**");
	}
	
	@Override
	public void onDisable(){
		
	}
	
	private boolean setupEconomy() {
	 	if (getServer().getPluginManager().getPlugin("Vault") == null) {
        	return false;
    	}
    	RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
    	if (rsp == null) {
    		return false;
    	}
    	econ = rsp.getProvider();
    	return econ != null;
	}
	
}
