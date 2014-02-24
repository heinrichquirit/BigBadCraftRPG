package main.java.net.bigbadcraft.bigbadcraftrpg.listeners;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class GodModeListener implements Listener {

	private BigBadCraftRPG plugin;
	public GodModeListener(BigBadCraftRPG plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerDamage(EntityDamageEvent event){
		if (event.getEntity() instanceof Player){
			if (plugin.godmode.contains(((Player) event.getEntity()).getName())){
				event.setCancelled(true);
			}
		}
	}
}
