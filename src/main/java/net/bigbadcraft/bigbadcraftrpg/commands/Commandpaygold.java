package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Commandpaygold extends BaseCommand{

	private BigBadCraftRPG plugin;
	public Commandpaygold(BigBadCraftRPG plugin){
		this.plugin = plugin;
	}
	
	@Override
	public void execute(Player player, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if (args.length < 2){
			Utils.makeMessage(player, "Incorrect syntax, usage: /paygold <player> <amount>");
		}
		else if (args.length == 2){
			Player target = Bukkit.getPlayer(args[0]);
			try{
				if (target == null){
					Utils.makeMessage(player, "Cannot pay offline player.");
					return;
				}
				int amount = Integer.parseInt(args[1]);
				int balance = plugin.goldConf.getInt(player.getName());
				if (balance < amount){
					Utils.makeMessage(player, "Cannot pay " + target.getName() + ", you need " + (amount-balance) + " gold.");
					return;
				}
				plugin.confHandler.reloadGoldConf();
				plugin.goldConf.set(player.getName(), balance - amount);
				plugin.goldConf.set(target.getName(), plugin.goldConf.getInt(target.getName()) + amount);
				plugin.confHandler.saveGoldConf();
				Utils.makeMessage(player, "Successfully paid " + target.getName() + " " + amount + " gold.");
				Utils.makeMessage(target, player.getName() + " has paid you " + amount + " gold.");
			}catch(NumberFormatException e){
				Utils.makeMessage(player, args[1] + " is not a number.");
			}
		}
	}

}
