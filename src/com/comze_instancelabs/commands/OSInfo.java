package com.comze_instancelabs.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import com.comze_instancelabs.utils.Utils;

public class OSInfo implements CommandExecutor {

	Plugin main = null;
	
	public OSInfo(Plugin p){
		main = p;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("osinfo") || cmd.getName().equalsIgnoreCase("os")){ 
			sender.sendMessage(Utils.getOSInfo().toString());
			return true;
		}
		return false;
	}

}
