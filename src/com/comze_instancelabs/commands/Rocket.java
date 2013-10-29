package com.comze_instancelabs.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class Rocket implements CommandExecutor {

	Plugin main = null;
	
	public Rocket(Plugin p){
		main = p;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("rocket")){
			if(args.length > 0){
				Player p = Bukkit.getPlayer(args[0]);
				
				if(p.isOnline()){
					//rocket
					Vector vec1 = p.getVelocity();
	        		//Vector vec2 = p.getLocation().getDirection();
					Vector vec2 = new Vector(0, 3, 0);
					p.setVelocity(vec1.add(vec2.multiply(3)));
				}
				
				return true;
			}else{
				Player p = (Player)sender;
				p.sendMessage("§2Usage: /rocket [player]");
				return true;
			}
		}
		
		return false;
	}

}
