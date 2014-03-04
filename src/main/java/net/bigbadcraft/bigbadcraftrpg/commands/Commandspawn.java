package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Permission;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

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
				p.spawnManager.teleportSpawn(player);
			}
		}
		else if (args.length == 1){
			if (Utils.checkPermission(player, Permission.SPAWN)){
				if (args[0].equalsIgnoreCase("list")){
					p.spawnManager.listRegularSpawns(player);
				}
			}
		}
		else if (args.length == 2){
			if (args[0].equalsIgnoreCase("set")){
				if (Utils.checkPermission(player, Permission.SPAWN_SET)){
					if (args[1].equalsIgnoreCase("default")){
						p.spawnManager.saveSpawn(player);
						Utils.makeMessage(player, "Successfully set as default spawn.");
					}
					else {
						String spawn = args[1].toLowerCase();
						p.spawnManager.saveRegularSpawns(spawn, player);
					}
				}
			}
			else if (args[0].equalsIgnoreCase("remove")){
				if (Utils.checkPermission(player, Permission.SPAWN_SET)){
					String spawn = args[1].toLowerCase();
					p.spawnManager.removeRegularSpawn(spawn, player);
				}
			}
			else if (args[0].equalsIgnoreCase("to")){
				if (Utils.checkPermission(player, Permission.SPAWN)){
					String spawn = args[1].toLowerCase();
					p.spawnManager.teleportRegularSpawn(spawn, player);
				}
			}
		}
	}

}
