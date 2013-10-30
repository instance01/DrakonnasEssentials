package com.comze_instancelabs.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.comze_instancelabs.listener.DrakonnasFounddiamonds;
import com.comze_instancelabs.utils.Utils;

public class fdstats implements CommandExecutor {

	Plugin main = null;
	
	public fdstats(Plugin p){
		main = p;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("fdstats")){
			if(args.length > 0){
				sender.sendMessage("§3Statistics for " +  args[0] +  "'s session:");
				Player p = Bukkit.getPlayer(args[0]);
				sender.sendMessage("§4Join Date: " + DrakonnasFounddiamonds.joindate.get(p));
				sender.sendMessage("§bDiamond Ores: " + DrakonnasFounddiamonds.diaores.get(p));
				sender.sendMessage("§2Emerald Ores: " + DrakonnasFounddiamonds.emeraldores.get(p));
				sender.sendMessage("§eGold Ores: " + DrakonnasFounddiamonds.goldores.get(p));
				sender.sendMessage("§8Spawner: " + DrakonnasFounddiamonds.spawner.get(p));
			}else{
				sender.sendMessage("§4You need to provide a player!");
			}
			
			return true;
		}
	
		return false;
	}

}
