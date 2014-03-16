package main.java.net.bigbadcraft.bigbadcraftrpg.listeners;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathSpawnListener implements Listener{

	private BigBadCraftRPG p;
	public DeathSpawnListener(BigBadCraftRPG plugin){
		p = plugin;
	}
	
	@EventHandler(priority=EventPriority.HIGH,ignoreCancelled=true)
	public void onDeath(PlayerDeathEvent event){
		Player player = event.getEntity();
		player.teleport(getLocation(p.spawnConf.getString("default-spawn")));
	}
	
	private Location getLocation(String str){
		String[] values = str.split(":");
		World world = Bukkit.getWorld(values[0]);
		int x = Utils.parseInt(values[1]);
		int y = Utils.parseInt(values[2]);
		int z = Utils.parseInt(values[3]);
		float yaw = Utils.parseFloat(values[4]);
		float pitch = Utils.parseFloat(values[5]);
		return new Location(world, x, y, z, yaw, pitch);
	}
	
}
