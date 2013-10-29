package com.comze_instancelabs.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import com.comze_instancelabs.utils.Utils;

public class Info implements CommandExecutor {

	Plugin main = null;
	
	public Info(Plugin p){
		main = p;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("version") || cmd.getName().equalsIgnoreCase("info")){ 
			sender.sendMessage(Utils.getVersionInfo(main).toString());
			return true;
		}
		return false;
	}

}
