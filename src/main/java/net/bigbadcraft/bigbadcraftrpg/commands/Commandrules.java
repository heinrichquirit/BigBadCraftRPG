package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import java.util.List;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Commandrules extends BaseCommand{

	private BigBadCraftRPG p;
	public Commandrules(BigBadCraftRPG p){
		this.p = p;
	}
	
	@Override
	public void execute(Player player, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 0){
			List<String> rules = p.getConfig().getStringList("rules");
			int count = 0;
			Utils.makeMessage(player, "Here are a list of rules.");
			for (String rule:rules){
				player.sendMessage((count++) + ". " + ChatColor.translateAlternateColorCodes('&', rule));
			}
		}
	}

}
