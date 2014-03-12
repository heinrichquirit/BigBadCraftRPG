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
		if (args.length < 2){
			Utils.makeMessage(player, "Incorrect syntax, usage: /give <player> <item> (amount)");
		}
		else if (args.length == 2){
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null){
				Utils.makeMessage(player, args[0] + " is offline.");
				return;
			}
			Material material = null;
			try{
				material = Material.valueOf(args[1].toUpperCase());
			}catch(IllegalArgumentException e){
				Utils.makeMessage(player, "You must enter a valid item to spawn.");
			}
			if (material != null){
				ItemStack stack = new ItemStack(material, 1);
				player.getInventory().addItem(stack);
				Utils.makeMessage(player, "Gave " + stack.getAmount() + " of " + args[1] + " to " + target.getName() + "."); 
				Utils.makeMessage(target, player.getName() + " has given you " + stack.getAmount() + " of " + args[1] + ".");
			}
		}
		else if (args.length == 3){
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null){
				Utils.makeMessage(player, args[0] + " is offline.");
				return;
			}
			Material material = null;
			int amount = 0;
			try{
				material = Material.valueOf(args[1].toUpperCase());
			}catch(IllegalArgumentException e){
				Utils.makeMessage(player, "You must enter a valid item to spawn.");
			}
			try{
				amount = Integer.parseInt(args[2]);
			}catch(NumberFormatException e){
				Utils.makeMessage(player, "You must use a number.");
			}
			if (material != null){
				ItemStack stack = new ItemStack(material, amount);
				player.getInventory().addItem(stack);
				Utils.makeMessage(player, "Gave " + stack.getAmount() + " of " + args[1] + " to " + target.getName() + "."); 
				Utils.makeMessage(target, player.getName() + " has given you " + stack.getAmount() + " of " + args[1] + ".");
			}
		}
	}

}
