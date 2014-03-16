package main.java.net.bigbadcraft.bigbadcraftrpg.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class NoMobSpawnerListener implements Listener{
	
	@EventHandler(priority=EventPriority.HIGH,ignoreCancelled=true)
	public void onSpawn(CreatureSpawnEvent event){
		if (event.getSpawnReason() == SpawnReason.SPAWNER){
			event.setCancelled(true);
		}
	}
}
