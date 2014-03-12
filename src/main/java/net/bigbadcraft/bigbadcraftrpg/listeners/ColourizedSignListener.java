package main.java.net.bigbadcraft.bigbadcraftrpg.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class ColourizedSignListener implements Listener {

	@EventHandler(priority=EventPriority.HIGH,ignoreCancelled=true)
	public void onSignChange(SignChangeEvent event) {
		for (int i = 0; i < 4; i++) {
			event.setLine(i, ChatColor.translateAlternateColorCodes('&', event.getLine(i)));
		}
	}
	
}
