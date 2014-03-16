package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

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
			Utils.makeMessage(player, "Your gold balance is: " + p.goldConf.getInt(player.getName()));
		}
	}
	
}
