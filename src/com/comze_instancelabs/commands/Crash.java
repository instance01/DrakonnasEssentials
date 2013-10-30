package com.comze_instancelabs.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.comze_instancelabs.utils.ParticleEffectNew;

public class Crash implements CommandExecutor {

	Plugin main = null;
	
	public Crash(Plugin p){
		main = p;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("crash")){
    		if(args.length > 0){
	    		Player p = Bukkit.getPlayer(args[0]);
	    		if(p != null){
	    			ParticleEffectNew heart = ParticleEffectNew.HEART;
					ParticleEffectNew smoke = ParticleEffectNew.SMOKE;
					ParticleEffectNew mob = ParticleEffectNew.MOB_SPELL;
					ParticleEffectNew explode = ParticleEffectNew.EXPLODE;
					heart.animateToPlayer(p, 10000, 10000);
					smoke.animateToPlayer(p, 10000, 10000);
					mob.animateToPlayer(p, 10000, 10000);
					explode.animateToPlayer(p, 10000, 10000);
	    		}else{
	    			sender.sendMessage("§4Player not found!");
	    		}
    		}else{
    			// no player provided
    		}
    		return true;
    	}
    	return false;
	}

}
