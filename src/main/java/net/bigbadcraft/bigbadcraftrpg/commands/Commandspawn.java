package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Permission;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Commandspawn extends BaseCommand{

	private BigBadCraftRPG p;
	public Commandspawn(BigBadCraftRPG plugin){
		p = plugin;
	}
	
	@Override
	public void execute(Player player, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		String path = p.spawnConf.getString("default-spawn");
		Location loc = player.getLocation();
		if (args.length == 0){
			if (Utils.checkPermission(player, Permission.SPAWN)){
				if (Utils.isNull(path)){
					Utils.makeMessage(player, "There is no default spawn set.");
					return;
				}
				player.teleport(getLocation(path));
				Utils.makeMessage(player, "Teleported to spawn.");
			}
		}
		else if (args.length == 1){
			if (Utils.checkPermission(player, Permission.SPAWN_SET)){
				if (args[0].equalsIgnoreCase("set")){
					p.confHandler.reloadSpawnConf();
					Bukkit.getWorld(player.getWorld().getName()).setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
					p.spawnConf.set("default-spawn", formatLocation(loc));
					p.confHandler.saveSpawnConf();
					Utils.makeMessage(player, "Successfully set as default spawn.");
				}
			}
		}
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
	
	private String formatLocation(Location loc){
		String world = loc.getWorld().getName();
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		float yaw = loc.getYaw();
		float pitch = loc.getPitch();
		return world+":"+x+":"+y+":"+z+":"+yaw+":"+pitch;
	}
}
