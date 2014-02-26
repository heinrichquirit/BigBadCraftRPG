package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Commandgamemode extends BaseCommand{

	@Override
	public void execute(Player player, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 0){
			if (player.getGameMode() == GameMode.SURVIVAL){
				player.setGameMode(GameMode.CREATIVE);
				Utils.makeMessage(player, "Switched to creative mode.");
			}
			else if (player.getGameMode() == GameMode.CREATIVE){
				player.setGameMode(GameMode.SURVIVAL);
				Utils.makeMessage(player, "Switched to survival mode.");
			}
			else if (player.getGameMode() == GameMode.ADVENTURE){
				player.setGameMode(GameMode.SURVIVAL);
				Utils.makeMessage(player, "Switched to survival mode.");
			}
		}
		else if (args.length == 1){
			if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s")){
				player.setGameMode(GameMode.SURVIVAL);
				Utils.makeMessage(player, "Switched to survival mode.");
			}
			else if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")){
				player.setGameMode(GameMode.CREATIVE);
				Utils.makeMessage(player, "Switched to creative mode.");
			}
			else if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a")){
				player.setGameMode(GameMode.ADVENTURE);
				Utils.makeMessage(player, "Switched to adventure mode.");
			}
		}
	}

}
