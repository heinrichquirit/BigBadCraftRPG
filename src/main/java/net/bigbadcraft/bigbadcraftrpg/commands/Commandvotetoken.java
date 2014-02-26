package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Commandvotetoken extends BaseCommand{

	private BigBadCraftRPG plugin;
	public Commandvotetoken(BigBadCraftRPG plugin){
		this.plugin = plugin;
	}
	
	@Override
	public void execute(Player player, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if (args.length < 2){
			plugin.voteTokenManager.sendInformation(player);
		}
		else if (args.length == 2){
			if (args[0].equalsIgnoreCase("claim")){
				try{
					int index = Integer.parseInt(args[1]);
					if (index <= plugin.voteTokens.size()){
						String line = plugin.voteTokens.get(index - 1);
						String[] values = line.split(":");
						int tokensRequired = Integer.parseInt(values[0]);
						int goldReward = Integer.parseInt(values[1]);
						int xpReward = Integer.parseInt(values[2]);
						if (plugin.voteTokenManager.getVoteTokens(player.getName()) < tokensRequired){
							int remainder = tokensRequired - plugin.voteTokenManager.getVoteTokens(player.getName());
							Utils.makeMessage(player, "You need " + remainder + " more vote tokens to claim that prize.");
							return;
						}
						plugin.voteTokenManager.rewardPlayer(player, tokensRequired, goldReward, xpReward);
						Utils.makeMessage(player, "Successfully claimed reward " + index + ".");
					} else {
						Utils.makeMessage(player, "That is not a valid index value.");
					}
				}catch(NumberFormatException e){
					Utils.makeMessage(player, "You must use a number.");
				}
			}
		}
	}

}
