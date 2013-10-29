package com.comze_instancelabs.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.comze_instancelabs.utils.Utils;

public class motd implements CommandExecutor {

	Plugin main = null;
	
	public motd(Plugin p){
		main = p;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("motd")){
			Player p = (Player) sender;
			if(args.length > 0){
				String action = args[0];
				if(action.equalsIgnoreCase("reload")){
					if(sender.hasPermission("motd.reload")){
						Utils.reloadConfiguration(new File(main.getDataFolder(), "drakonnasmotd.yml"));
						sender.sendMessage("§2MOTD config successfully reloaded.");
					}else{
						sender.sendMessage("§4MOTD config successfully reloaded.");
					}
				}
			}else{
				sender.sendMessage("§3/motd reload");
			}
			return true;
		}
		return false;
	}

}
