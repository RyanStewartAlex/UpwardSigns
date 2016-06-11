package me.RyanStewart.UpwardSigns;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import net.md_5.bungee.api.ChatColor;


public class ListenerClass implements Listener{
	
	UpwardSignsMain plugin;
	FileConfiguration config;
	
	public ListenerClass(UpwardSignsMain plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent e){
		if (e.getPlayer().hasPermission("upwardsigns.create") && e.getLine(0).equalsIgnoreCase("[up]")){
			e.setLine(0, "§1[Up]");
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getState() instanceof Sign){
			Sign sign = (Sign) e.getClickedBlock().getState();
			
			if (sign.getLine(0).equalsIgnoreCase("§1[Up]")){
				if (e.getPlayer().hasPermission("upwardsigns.use")){
					Location pLoc = e.getPlayer().getLocation();
					int y = pLoc.getBlockY();
					
					//system stuff
					while (true){
						Location sLoc = new Location(pLoc.getWorld(), pLoc.getBlockX(), y, pLoc.getBlockZ());
						
						if (e.getPlayer().getWorld().getBlockAt(sLoc).getType() != Material.AIR){
							if (sLoc.add(new Location(e.getPlayer().getWorld(), 0, 1, 0)).getBlock().getType().equals(Material.AIR) && sLoc.add(new Location(e.getPlayer().getWorld(), 0, 1, 0)).getBlock().getType().equals(Material.AIR)){
								//play sound effects
								e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
								//teleport to that location
								e.getPlayer().sendMessage(ChatColor.GREEN + "Teleported to the next available level!");
								e.getPlayer().teleport(sLoc.subtract(new Location(e.getPlayer().getWorld(), 0, 1, 0)));
								break;
							}else{
								y++;
							}
						}else{
							y++;
							if (y - pLoc.getBlockY() >= 30){
								e.getPlayer().sendMessage(ChatColor.RED + "There is no platform to teleport you to!");
								break;
							}
						}
					}
				}else{
					e.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to use that.");
				}
			}
		}
	}
	
}
