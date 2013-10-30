package com.comze_instancelabs.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.comze_instancelabs.utils.Utils;

public class Gods implements CommandExecutor {

	Plugin main = null;
	
	public Gods(Plugin p){
		main = p;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("gods")){
			if(sender.hasPermission("gods.create")){
				if(args.length > 0){
					String action = args[0];
					if(action.equalsIgnoreCase("create")){
						if(args.length > 3){
							String effect = args[3];
							if(effect.equals("BLINDNESS") || effect.equals("CONFUSION") || effect.equals("DAMAGE_RESISTANCE") || effect.equals("FAST_DIGGING") || effect.equals("FIRE_RESISTANCE") || effect.equals("HARM") || effect.equals("HEAL") || effect.equals("INCREASE_DAMAGE") || effect.equals("INVISIBILITY") || effect.equals("JUMP") || effect.equals("NIGHT_VISION") || effect.equals("POISON") || effect.equals("REGENERATION") || effect.equals("SLOW") || effect.equals("SLOW_DIGGING") || effect.equals("SPEED") || effect.equals("WATER_BREATHING") || effect.equals("WEAKNESS") || effect.equals("WITHER")){
								String godname = args[1];
								Utils.getDrakonnasGodsConfiguration().set(godname + ".xp", Integer.parseInt(args[2]));
								Utils.getDrakonnasGodsConfiguration().set(godname + ".potion", args[3]);
								Utils.saveDrakonnasGodsConfiguration();
								sender.sendMessage("§f[§3DrakonnasGods§f] §2A new God has been created!");	
							}else{
								sender.sendMessage("§4There's no such potion effect. Use §3/gods potioneffects §4to get a list.");
							}
						}else{
							sender.sendMessage("§2/gods create [name] [points] [potion] [duration]");
						}
					}else if(action.equalsIgnoreCase("remove")){
						if(args.length > 1){
							String godname = args[1];
							Utils.getDrakonnasGodsConfiguration().set(godname, null);
							Utils.saveDrakonnasGodsConfiguration();
							//Utils.saveConfiguration(new File("plugins/DrakonnasEssentials/", "drakonnasgods.yml"));
							sender.sendMessage("§f[§3DrakonnasGods§f] §2You removed " + godname + ".");
						}
					}else if(action.equalsIgnoreCase("potioneffects")){
						sender.sendMessage("§2SPEED, SLOW, FAST_DIGGING, SLOW_DIGGING, INCREASE_DAMAGE, HEAL, HARM, JUMP, CONFUSION, REGENERATION, DAMAGE_RESISTANCE, FIRE_RESISTANCE, WATER_BREATHING, INVISIBILITY, BLINDNESS, NIGHT_VISION, WEAKNESS, POISON, WITHER");
					}else if(action.equalsIgnoreCase("reload")){
						Utils.reloadConfiguration(new File(main.getDataFolder(), "drakonnasgods.yml"));
						sender.sendMessage("§f[§3DrakonnasGods§f] §2Successfully reloaded Gods configuration.");
					}else if(action.equalsIgnoreCase("resetcooldown")){
						if(args.length > 1){
							String player = args[1];
							Utils.getDrakonnasGodsConfiguration().set(player, null);
							Utils.saveConfiguration(new File("plugins/DrakonnasEssentials/", "drakonnasgods.yml"));
							sender.sendMessage("§f[§3DrakonnasGods§f] §2Successfully reset " + player + "'s cooldown from the config.");
						}
					}else if(action.equalsIgnoreCase("list")){
						Utils.reloadConfiguration(new File(main.getDataFolder(), "drakonnasgods.yml"));
						sender.sendMessage("§f[§3DrakonnasGods§f] §2Too many..");
					}else if(action.equalsIgnoreCase("info")){
						sender.sendMessage("§3DrakonnasGods §2was developed by instancelabs with fucking great help of hades700. ");
					}
				}else{
					sender.sendMessage("§3DrakonnasGods help:");
					sender.sendMessage("§2/gods create [name] [points] [potion] [duration]");
					sender.sendMessage("§2/gods remove [name]");
					sender.sendMessage("§2/gods reload");
					sender.sendMessage("§2/gods resetcooldown [player]");
					sender.sendMessage("§2/gods list");
					sender.sendMessage("§2/gods info");
					sender.sendMessage("§2/gods potioneffects");
				}
			}else{
				sender.sendMessage("§4You don't have permission!");
			}
			return true;
		}
		return false;
	}

}
