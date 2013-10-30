package com.comze_instancelabs.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import com.comze_instancelabs.utils.Utils;

public class dhelp implements CommandExecutor {

	Plugin main = null;
	
	public dhelp(Plugin p){
		main = p;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("dhelp") || cmd.getName().equalsIgnoreCase("drakonnashelp")){ 
			sender.sendMessage(Utils.getHelp(main).toString());
			return true;
		}
		return false;
	}

}
