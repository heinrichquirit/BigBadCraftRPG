package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;


public class Commandnight extends BaseCommand {

	@Override
	public void execute(Player player, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 0){
			Bukkit.getWorld(player.getWorld().getName()).setTime(18000);
			Utils.makeMessage(player, "You've set time to night.");
		}
	}

}
