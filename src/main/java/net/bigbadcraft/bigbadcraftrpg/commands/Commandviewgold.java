package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Commandviewgold extends BaseCommand {

	private BigBadCraftRPG p;
	public Commandviewgold(BigBadCraftRPG p){
		this.p = p;
	}
	
	@Override
	public void execute(Player player, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 0) {
			Utils.makeMessage(player, "View other player's gold balance: /viewgold <player>");
			Utils.makeMessage(player, "Your gold balance is: " + p.goldHandler.getConfiguration().getInt(player.getName()));
		}
		else if (args.length == 1){
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null){
				Utils.makeMessage(player, args[0] + " is offline.");
				return;
			}
			Utils.makeMessage(player, target.getName() + " is gold balance is: " + p.goldHandler.getConfiguration().getInt(target.getName()));
		}
	}
	
}
