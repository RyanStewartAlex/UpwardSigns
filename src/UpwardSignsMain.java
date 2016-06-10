package me.RyanStewart.UpwardSigns;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class UpwardSignsMain extends JavaPlugin{
	
	/*
	 * Summary:
	 * This plugin is made so when you have a sign that says "[Up]" on the first line, it formats the color and such.
	 * When the sign is right-clicked, it brings the player to the next highest platform.
	 */
	
	public Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable(){
		new ListenerClass(this);
		PluginManager pm = this.getServer().getPluginManager();
		pm.addPermission(new Permission("upwardsigns.create"));
		pm.addPermission(new Permission("upwardsigns.use"));
	}
	
	public void onDisable(){
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){		
		return false;
	}	
}
