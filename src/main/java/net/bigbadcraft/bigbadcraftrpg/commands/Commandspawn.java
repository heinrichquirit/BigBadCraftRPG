package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import java.util.ArrayList;
import java.util.List;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Permission;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.google.common.base.Joiner;

public class Commandspawn extends BaseCommand {

	private BigBadCraftRPG p;
	public Commandspawn(BigBadCraftRPG plugin){
		p = plugin;
	}
	
	@Override
	public void execute(Player player, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 0){
			if (Utils.checkPermission(player, Permission.SPAWN)){
				if (!p.spawnPoints.isEmpty()){
					for (String line:p.spawnPoints){
						String[] values = line.split(":");
						if (values[0].equals("default")){
							World world = Bukkit.getWorld(values[1]);
							int x = Utils.parseInt(values[2]);
							int y = Utils.parseInt(values[3]);
							int z = Utils.parseInt(values[4]);
							float yaw = Utils.parseFloat(values[5]);
							float pitch = Utils.parseFloat(values[6]);
							player.teleport(new Location(world,x,y,z,yaw,pitch));
							Utils.makeMessage(player, "Teleported to spawn.");
						}
					}
				}
			}
		}
		else if (args.length == 1){
			if (Utils.checkPermission(player, Permission.SPAWN)){
				if (args[0].equalsIgnoreCase("list")){
					if (p.spawnPoints.isEmpty()){
						Utils.makeMessage(player, "There are no spawn points to list.");
						return;
					}
					String[] values = new String[p.spawnPoints.size()];
					List<String> spawnNames = new ArrayList<String>();
					for (String line:p.spawnPoints){
						values = line.split(":");
						spawnNames.add(values[0]);
					}
					String info = "Use /spawn to <name> Current spawn points: ";
					Utils.makeMessage(player, info + Joiner.on(", ").join(spawnNames)+".");
				}
			}
		}
		else if (args.length == 2){
			if (args[0].equalsIgnoreCase("set")){
				if (Utils.checkPermission(player, Permission.SPAWN_SET)){
					String spawnName = args[1];
					if (p.spawnPoints.contains(spawnName)){
						Utils.makeMessage(player, spawnName + " is already a spawn point.");
						return;
					}
					String world = player.getWorld().getName();
					int x = player.getLocation().getBlockX();
					int y = player.getLocation().getBlockY();
					int z = player.getLocation().getBlockZ();
					float yaw = player.getLocation().getYaw();
					float pitch = player.getLocation().getPitch();
					p.confHandler.reloadSpawnsConf();
					p.spawnPoints.add(spawnName+":"+world+":"+x+":"+y+":"+z+":"+yaw+":"+pitch);
					p.confHandler.saveSpawnsConf();
					Utils.makeMessage(player, "Successfully saved spawn point " + ChatColor.WHITE + spawnName);
				}
			}
			else if (args[0].equalsIgnoreCase("to")){
				if (Utils.checkPermission(player, Permission.SPAWN)){
					String spawnName = args[1];
					if (p.spawnPoints.isEmpty()){
						Utils.makeMessage(player, "There are no spawn points to teleport to.");
						return;
					}
					for (String line:p.spawnPoints){
						String[] values = line.split(":");
						if (values[0].equalsIgnoreCase(spawnName)){
							World world = Bukkit.getWorld(values[1]);
							int x = Utils.parseInt(values[2]);
							int y = Utils.parseInt(values[3]);
							int z = Utils.parseInt(values[4]);
							float yaw = Utils.parseFloat(values[5]);
							float pitch = Utils.parseFloat(values[6]);
							player.teleport(new Location(world,x,y,z,yaw,pitch));
							Utils.makeMessage(player, "Teleported to " + ChatColor.WHITE + spawnName);
						}
					}
				}
			}
		}
	}

}
