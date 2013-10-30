package com.comze_instancelabs.commands;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.comze_instancelabs.utils.Utils;

public class vote implements CommandExecutor {

	Plugin main = null;
	
	public vote(Plugin p){
		main = p;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("votes")){
			if(args.length > 0){
				String action = args[0];
				if(action.equalsIgnoreCase("info")){
					ArrayList<String> keys = new ArrayList<String>();
			        keys.addAll(Utils.getDrakonnasVoteConfiguration().getKeys(false));
			        keys.remove("link");
			        for(int i = 0; i < keys.size(); i++){
			            sender.sendMessage(Utils.getDrakonnasVoteConfiguration().getString("info" + Integer.toString(i)));
			        }
				}else if(action.equalsIgnoreCase("reload")){
					if(sender.hasPermission("votes.reload")){
						Utils.reloadConfiguration(new File("plugins/DrakonnasEssentials/", "drakonnasvote.yml"));
						sender.sendMessage("§2Votes config successfully reloaded.");
					}else{
						sender.sendMessage("§4Votes config successfully reloaded.");
					}
				}
			}else{
				ArrayList<String> keys = new ArrayList<String>();
		        keys.addAll(Utils.getDrakonnasVoteConfiguration().getKeys(false));
		        keys.remove("link");
		        for(int i = 0; i < keys.size(); i++){
		        	sender.sendMessage(Utils.getDrakonnasVoteConfiguration().getString("info" + Integer.toString(i)));
		        }
			}
        	
			return true;
		}
		return false;
	}

}
