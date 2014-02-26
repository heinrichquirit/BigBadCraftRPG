package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;


public class Commandspawnmob extends BaseCommand{

	private BigBadCraftRPG plugin;
	public Commandspawnmob(BigBadCraftRPG plugin){
		this.plugin = plugin;
	}
	
	@Override
	public void execute(Player player, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 0){
			Utils.makeMessage(player, "Incorrect syntax, usage: /spawnmob <mob> <amount>");
		}
		else if (args.length == 1){
			String mob = args[0].toUpperCase();
			@SuppressWarnings("deprecation")
			Block block = player.getTargetBlock(null, 30);
			Location location;
			if (block != null) {
				location = block.getLocation();
			} else {
				location = player.getLocation();
			}
			
				Bukkit.getWorld(player.getWorld().getName()).spawnEntity(location.add(0.0D, 1.0D, 0.0D), EntityType.valueOf(mob));
				Utils.makeMessage(player, "Spawned a " + mob.toLowerCase() + ".");
		}
		else if (args.length == 2){
			String mob = args[0].toUpperCase();
			@SuppressWarnings("deprecation")
			Block block = player.getTargetBlock(null, 30);
			Location location;
			if (block != null){
				location = block.getLocation();
			} else {
				location = player.getLocation();
			}
			int amount = 1;
			try{
				amount = Integer.parseInt(args[1]);
			}catch(NumberFormatException e){
				Utils.makeMessage(player, "You must enter a number.");
			}
			if (amount > plugin.maxSpawnmobLimit){
				Utils.makeMessage(player, "You cannot spawn more than " + plugin.maxSpawnmobLimit + " mobs.");
				return;
			}
			for (int i=0;i<amount;i++){
				Bukkit.getWorld(player.getWorld().getName()).spawnEntity(location.add(0.0D, 1.0D, 0.0D), EntityType.valueOf(mob));
			}
			Utils.makeMessage(player, "Spawned " + amount + " " + mob.toLowerCase() + ".");
		}
	}

}
