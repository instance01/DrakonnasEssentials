package com.comze_instancelabs.listener;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
//import org.bukkit.material.Sign;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.comze_instancelabs.utils.Utils;


/**
 * 
 * @author instancelabs
 *
 */

public class DrakonnasGods implements Listener {

	Plugin main = null;
	
	public DrakonnasGods(Plugin p){
		main = p;
	}


	
	// if player places sign and diamond block there -> new temple
	
	// Sign:
	// [god]
	// name
	
   @EventHandler
    public void onSignChange(SignChangeEvent event) {
        Player p = event.getPlayer();
        if(event.getLine(0).toLowerCase().equalsIgnoreCase("[god]") && p.hasPermission("gods.create")){
    		String godname = event.getLine(1);
    		if(Utils.getDrakonnasGodsConfiguration().isSet(godname)){
    			if(isValidTemple(event.getBlock())){
    				event.setLine(0, "§f[§bDR Gods§f]");
    				event.setLine(2, Integer.toString(Utils.getDrakonnasGodsConfiguration().getInt(godname + ".xp")) + " DR Points");
    				p.sendMessage("§f[§3DrakonnasGods§f] §2You successfully created a temple!");
    			}else{
    				event.setLine(0, "§f[§4DR Gods§f]");
    				p.sendMessage("§f[§3DrakonnasGods§f] §cA temple sign needs two diamond blocks to be attached to!");
    			}
    		}else{
    			p.sendMessage("§f[§3DrakonnasGods§f] §cThere's no god by that name!");
    			event.getBlock().breakNaturally();
    		}
        }
    }
	
	
	/*
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		
	}*/
	
	
	// if player uses sign and one hour away -> give xp
	@EventHandler
	public void onSignUse(PlayerInteractEvent event)
	{
		if (event.hasBlock() && event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
		    if (event.getClickedBlock().getType() == Material.SIGN_POST || event.getClickedBlock().getType() == Material.WALL_SIGN)
		    {
		        final Sign s = (Sign) event.getClickedBlock().getState();
		        if(s.getLine(0).equalsIgnoreCase("§f[§bdr gods§f]")){
		        	String godname = s.getLine(1);
		        	if(isValidTemple(event.getClickedBlock())){
		        		if(!Utils.getDrakonnasGodsConfiguration().isSet(event.getPlayer().getName() + ".hoursleft")){
			        		SimpleDateFormat sdfToDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			        		StringBuilder test = new StringBuilder(sdfToDate.format(new Date()));
			        		Utils.getDrakonnasGodsConfiguration().set(event.getPlayer().getName() + ".hoursleft", test.toString());
			        		Utils.saveConfiguration(new File("plugins/DrakonnasEssentials/", "drakonnasgods.yml"));
			        		//event.getPlayer().giveExp(Utils.getDrakonnasGodsConfiguration().getInt(godname + ".xp"));
			        		main.getServer().dispatchCommand(main.getServer().getConsoleSender(), "enjin addpoints " + event.getPlayer().getName() + " " + Integer.toString(Utils.getDrakonnasGodsConfiguration().getInt(godname + ".xp")));
			        		//PotionEffect speed = PotionEffectType.SPEED.createEffect(99999999, 7);
			        		PotionEffect speed = PotionEffectType.getByName(Utils.getDrakonnasGodsConfiguration().getString(godname + ".potion")).createEffect(12000, 1);
				            event.getPlayer().addPotionEffect(speed, true);
				            event.getPlayer().playEffect(event.getPlayer().getLocation(), Effect.POTION_BREAK, 5);
				            event.getPlayer().sendMessage("§f[§3DrakonnasGods§f] §cYou got " + Utils.getDrakonnasGodsConfiguration().getInt(godname + ".xp") + " DR Points!");
		        		}else{
			        		if(checkHours(event.getPlayer())){
			        			//event.getPlayer().giveExp(Utils.getDrakonnasGodsConfiguration().getInt(godname + ".xp"));
			        			main.getServer().dispatchCommand(main.getServer().getConsoleSender(), "enjin addpoints " + event.getPlayer().getName() + " " + Integer.toString(Utils.getDrakonnasGodsConfiguration().getInt(godname + ".xp")));
			        			SimpleDateFormat sdfToDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			        			StringBuilder test = new StringBuilder(sdfToDate.format(new Date()));
				        		Utils.getDrakonnasGodsConfiguration().set(event.getPlayer().getName() + ".hoursleft", test.toString());
				        		Utils.saveConfiguration(new File("plugins/DrakonnasEssentials/", "drakonnasgods.yml"));
				        		PotionEffect speed = PotionEffectType.getByName(Utils.getDrakonnasGodsConfiguration().getString(godname + ".potion")).createEffect(12000, 1);
					            event.getPlayer().addPotionEffect(speed, true);
					            event.getPlayer().playEffect(event.getPlayer().getLocation(), Effect.POTION_BREAK, 5);
				        		event.getPlayer().sendMessage("§f[§3DrakonnasGods§f] §cYou got " + Utils.getDrakonnasGodsConfiguration().getInt(godname + ".xp") + " DR Points!");
				        		
				        		if(Utils.getDrakonnasGodsConfiguration().getBoolean("config.broadcast")){
				        			main.getServer().broadcastMessage("§f[§3DrakonnasGods§f] " + event.getPlayer().getName() + " just prayed to " + godname + " and earned 40 DR Points!");
				        		}
			        		}else{
			        			event.getPlayer().sendMessage("§f[§3DrakonnasGods§f] §cYou need to wait 24 hours between praying.. :/");
			        		}	
		        		}
		        		
		        	}
		        }
		    }
		}
	}
	
	
	/***
	 * Returns if sign is attached to two diamond blocks and thus is a valid temple
	 * @param b the sign
	 * @return returns true if valid, false if not
	 */
	public boolean isValidTemple(Block b){
		org.bukkit.material.Sign s = (org.bukkit.material.Sign) b.getState().getData();
		Block attachedBlock = b.getRelative(s.getAttachedFace());
		if(attachedBlock.getType() == Material.DIAMOND_BLOCK && attachedBlock.getRelative(BlockFace.DOWN).getType() == Material.DIAMOND_BLOCK){
			// we have tow diamond blocks!
			return true;
		}else{
			return false;
		}
	}
	
	
	/***
	 * Checks if player is able to use the action again
	 * @param p Player to check
	 * @return returns true if last use 1 hour ago, false if not
	 */
	public boolean checkHours(Player p){
		SimpleDateFormat sdfToDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date datecurrent = new Date();
		String daysdate = Utils.getDrakonnasGodsConfiguration().getString(p.getName() + ".hoursleft");
		//p.sendMessage(daysdate);
		Date date1 = null;
		try {
			date1 = sdfToDate.parse(daysdate);
			System.out.println(date1);
		} catch (ParseException ex2){
			ex2.printStackTrace();
		}
		Integer between = this.hoursBetween(datecurrent, date1);
		main.getLogger().info(Integer.toString(between));
		if(between > 23 || between < -23){
			return true;
		}else{
			return false;
		}
	}
	
		
	public int hoursBetween(Date d1, Date d2){
	    long differenceMilliSeconds = d2.getTime() - d1.getTime();
	    long hours = differenceMilliSeconds / 1000 / 60 / 60;
	    return (int) hours;
	}
	
}
