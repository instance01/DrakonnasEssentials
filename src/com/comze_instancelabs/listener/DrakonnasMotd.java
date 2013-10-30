package com.comze_instancelabs.listener;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import com.comze_instancelabs.utils.Utils;

public class DrakonnasMotd implements Listener {

	Plugin main = null;
	
	public DrakonnasMotd(Plugin p){
		main = p;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(Utils.getDrakonnasMotdConfiguration().isSet("motd0")){
			ArrayList<String> keys = new ArrayList<String>();
	        keys.addAll(Utils.getDrakonnasMotdConfiguration().getKeys(false));
	        for(int i = 0; i < keys.size(); i++){
	            p.sendMessage(Utils.getDrakonnasMotdConfiguration().getString("motd" + Integer.toString(i)));
	        }	
		}
	}
	
}
