package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Commandgod extends BaseCommand {

	private BigBadCraftRPG plugin;
	public Commandgod(BigBadCraftRPG plugin){
		this.plugin = plugin;
	}
	
	@Override
	public void execute(Player player, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 0){
			if (!plugin.godmode.contains(player.getName())){
				plugin.godmode.add(player.getName());
				Utils.makeMessage(player, "Toggled god mode on.");
			} else {
				plugin.godmode.remove(player.getName());
				Utils.makeMessage(player, "Toggled god mode off.");
			}
		}
	}

}
