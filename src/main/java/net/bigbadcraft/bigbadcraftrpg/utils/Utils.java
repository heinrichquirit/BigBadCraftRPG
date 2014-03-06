package main.java.net.bigbadcraft.bigbadcraftrpg.utils;

import java.io.File;
import java.util.logging.Level;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {
	
	public static void makeMessage(Player player, String message){
		player.sendMessage(ChatColor.valueOf(BigBadCraftRPG.getInstance().colourScheme.toUpperCase()) + message);
	}
	
	public static boolean checkPermission(Player player, String permission){
		if (player.isOp() || player.hasPermission("*") || player.hasPermission(permission)){
			return true;
		}
		player.sendMessage(ChatColor.RED + "You do not have permission: " + permission);
		return false;
	}
	
	public static int parseInt(String str){
		try{
			return Integer.parseInt(str);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public static float parseFloat(String str){
		try{
			return Float.parseFloat(str);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public static void loadFile(File file) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isNull(Object obj){
		if (obj == null){
			return true;
		} else {
			return false;
		}
	}
	
	public static void a(String msg){
		Bukkit.broadcastMessage(msg);
	}
	
	public static void log(Level level, String message) {
		Bukkit.getLogger().log(level, message);
	}
}
