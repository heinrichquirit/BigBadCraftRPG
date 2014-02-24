package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import java.util.ArrayList;
import java.util.List;

import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.google.common.base.Joiner;

public class Commandlist extends BaseCommand {
	
	@Override
	public void execute(Player player, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if (args.length ==0){
			List<String> players = new ArrayList<String>();
			for (Player p:Bukkit.getOnlinePlayers()){
				players.add(p.getName());
			}
			Utils.makeMessage(player, players.size() + " player(s) online: " + Joiner.on(", ").join(players) + ".");
		}
	}
	
}
