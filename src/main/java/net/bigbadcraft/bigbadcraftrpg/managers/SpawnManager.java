package main.java.net.bigbadcraft.bigbadcraftrpg.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.google.common.base.Joiner;

public class SpawnManager {
	
	private BigBadCraftRPG p;
	public SpawnManager(BigBadCraftRPG plugin){
		p = plugin;
	}
	
	public void teleportSpawn(Player player){
		if (p.spawns.isEmpty()) return;
		for (String line:p.spawns){
			if (line.contains("default")){
				player.teleport(getLocation(line));
			}
		}
		Utils.makeMessage(player, "You have teleported to spawn.");
	}
	
	public void teleportRegularSpawn(String spawn, Player player){
		if (p.spawns.isEmpty()){
			Utils.makeMessage(player, "There are no spawn points to teleport to.");
			return;
		}
		for (String line:p.spawns){
			if (line.contains(spawn)){
				player.teleport(getLocation(line));
			}
		}
		Utils.makeMessage(player, "Teleported to " + spawn + ".");
	}
	
	public void saveSpawn(Player player){
		p.confHandler.reloadSpawnsConf();
		p.spawns.add(formatLocation("default", player.getLocation()));
		p.confHandler.saveSpawnsConf();
	}
	
	public void saveRegularSpawns(String spawn, Player player){
		Iterator<String> iterator = p.spawns.iterator();
		if (!iterator.hasNext()){
			p.confHandler.reloadSpawnsConf();
			p.spawns.add(formatLocation(spawn, player.getLocation()));
			p.confHandler.saveSpawnsConf();
			return;
		}
		while (iterator.hasNext()){
			if (!iterator.next().contains(spawn)){
				p.confHandler.reloadSpawnsConf();
				p.spawns.add(formatLocation(spawn, player.getLocation()));
				p.confHandler.saveSpawnsConf();
			} else {
				Utils.makeMessage(player, spawn + " spawn point already exists.");
			}
		}
		Utils.makeMessage(player, spawn + " spawn point successfully saved.");
	}
	
	public void removeRegularSpawn(String spawn, Player player){
		if (p.spawns.isEmpty()) return;
		Iterator<String> iterator = p.spawns.iterator();
		while (iterator.hasNext()){
			if (iterator.next().contains(spawn)){
				p.confHandler.reloadSpawnsConf();
				iterator.remove();
				p.confHandler.saveSpawnsConf();
			}
		}
		Utils.makeMessage(player, spawn + " spawn point successfully removed."); 
	}
	
	public void listRegularSpawns(Player player){
		if (p.spawns.isEmpty()){
			Utils.makeMessage(player, "There are no spawn points to list.");
			return;
		}
		List<String> spawnNames = new ArrayList<String>();
		for (String line:p.spawns){
			spawnNames.add(line.split(":")[0]);
		}
		spawnNames.remove("default");
		Utils.makeMessage(player, "Use /spawn to <spawn> Here are a list of spawn points: " + Joiner.on(", ").join(spawnNames)+".");
	}
	
	private Location getLocation(String str){
		String[] a = str.split(":");
		World b = Bukkit.getWorld(a[1]);
		int x = Utils.parseInt(a[2]);
		int y = Utils.parseInt(a[3]);
		int z = Utils.parseInt(a[4]);
		float yaw = Utils.parseFloat(a[5]);
		float pitch = Utils.parseFloat(a[6]);
		return new Location(b,x,y+1.0D,z,yaw,pitch);
	}
	
	private String formatLocation(String spawnName, Location loc){
		String world = loc.getWorld().getName();
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		float yaw = loc.getYaw();
		float pitch = loc.getPitch();
		return spawnName+":"+world+":"+x+":"+y+":"+z+":"+yaw+":"+pitch;
	}
	
}
