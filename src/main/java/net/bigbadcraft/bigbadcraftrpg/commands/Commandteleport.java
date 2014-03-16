package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Commandteleport extends BaseCommand {

	@Override
	public void execute(Player player, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 0){
			Utils.makeMessage(player, "/tpp <player>, /tpp here <player>, /tpp coords <x> <y> <z>");
		}
		else if (args.length == 1){
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null){
				Utils.makeMessage(player, "Cannot teleport to offline player.");
				return;
			}
			player.teleport(target.getLocation());
			Utils.makeMessage(player, "You've teleported to " + target.getName() + ".");
		}
		else if (args.length == 2){
			if (args[0].equalsIgnoreCase("here")){
				Player target = Bukkit.getPlayer(args[1]);
				if (target == null){
					Utils.makeMessage(player, "You cannot summon an offline player.");
					return;
				}
				target.teleport(player.getLocation());
				Utils.makeMessage(player, "You have summoned " + target.getName() + ".");
				Utils.makeMessage(target, player.getName() + " has summoned you.");
			}
		}
		else if (args.length == 4){
			if (args[0].equalsIgnoreCase("coords")){
				int x = Utils.parseInt(args[1]);
				int y = Utils.parseInt(args[2]);
				int z = Utils.parseInt(args[3]);
				player.teleport(new Location(player.getWorld(), x, y, z));
				Utils.makeMessage(player, "Teleported to " + x + ", " + y + ", " + z + " in world: " + player.getWorld().getName());
			}
		}
	}
	
}
