package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Commandgive extends BaseCommand {

	@Override
	public void execute(Player player, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 0){
			Utils.makeMessage(player, "Incorrect syntax, usage: /give <player> <item:amount>");
		}
		else if (args.length == 1){
			String[] values = args[0].split(":");
			Material material = null;
			int amount = 0;
			try{
				material = Material.valueOf(values[0].toUpperCase());
			}catch(IllegalArgumentException e){
				Utils.makeMessage(player, "You must enter a valid item to spawn.");
			}
			try{
				amount = Integer.parseInt(values[1]);
			}catch(NumberFormatException e){
			Utils.makeMessage(player, "You must enter a number after the item.");
			}
			if (material != null){
				player.getInventory().addItem(new ItemStack(material, amount));
				Utils.makeMessage(player, "Spawned " + values[1] + " of " + values[0] + "."); 
			}
		}
		else if (args.length == 2){
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null){
				Utils.makeMessage(player, args[0] + " is offline.");
				return;
			}
			String[] values = args[1].split(":");
			Material material = null;
			int amount = 0;
			try{
				material = Material.valueOf(values[0].toUpperCase());
			}catch(IllegalArgumentException e){
				Utils.makeMessage(player, "You must enter a valid item to spawn.");
			}
			try{
				amount = Integer.parseInt(values[1]);
			}catch(NumberFormatException e){
			Utils.makeMessage(player, "You must enter a number after the item.");
			}
			if (material != null){
				player.getInventory().addItem(new ItemStack(material, amount));
				Utils.makeMessage(player, "Spawned " + values[1] + " of " + values[0] + "."); 
			}
		}
	}

}
