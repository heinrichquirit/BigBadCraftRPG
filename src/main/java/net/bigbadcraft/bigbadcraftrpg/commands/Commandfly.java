package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Commandfly extends BaseCommand {

	@Override
	public void execute(Player player, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 0){
			if (player.getAllowFlight() == false){
				player.setAllowFlight(true);
				player.setFlying(true);
				Utils.makeMessage(player, "Enabled flight mode.");
			} else {
				player.setAllowFlight(false);
				player.setFlying(false);
				Utils.makeMessage(player, "Disabled flight mode.");
			}
		}
		else if (args.length == 1){
			try{
				int speed = Integer.parseInt(args[0]);
				player.setFlySpeed(speed);
				Utils.makeMessage(player, "You've set your flight speed to " + speed + "."); 
			} catch (NumberFormatException e){
				Utils.makeMessage(player, "You must enter a number for your flight speed.");
			}
		}
	}
	

}
