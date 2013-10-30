package com.comze_instancelabs.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Endercrystal implements CommandExecutor {

	Plugin main = null;
	
	public Endercrystal(Plugin p){
		main = p;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ec") || cmd.getName().equalsIgnoreCase("endercrystal")) {
			if(sender instanceof Player){
				Player p = (Player) sender;
				p.getWorld().spawn(p.getLocation(), EnderCrystal.class);	
			}else{
				sender.sendMessage("§4Please execute this command ingame!");
			}
			
			return true;
		}
		return false;
	}

}
