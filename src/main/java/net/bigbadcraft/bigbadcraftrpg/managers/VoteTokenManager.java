package main.java.net.bigbadcraft.bigbadcraftrpg.managers;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.entity.Player;

public class VoteTokenManager {
	
	private BigBadCraftRPG plugin;
	public VoteTokenManager(BigBadCraftRPG plugin){
		this.plugin = plugin;
	}
	
	public int getVoteTokens(String name){
		return plugin.voteHandler.getConfiguration().getInt(name);
	}
	
	public void sendInformation(Player player){
		if (plugin.voteTokens.size() > 0){
			int index = 1;
			Utils.makeMessage(player, "----------(Vote Tokens)----------");
			Utils.makeMessage(player, "Vote to receive rewards: http://www.bigbadcraft.net/");
			Utils.makeMessage(player, "To claim your rewards, use /votetoken claim <index>");
			for (String string:plugin.voteTokens){
				String[] values = string.split(":");
				Utils.makeMessage(player, (index++)+". "+values[0]+" tokens >>> Receive "+values[1]+" gold & "+values[2]+"xp");
			}
			Utils.makeMessage(player, "You currently have " + getVoteTokens(player.getName()) + " tokens.");  
			Utils.makeMessage(player, "---------------------------------");
		}
	}
	
	public void rewardPlayer(Player player, int voteTokens, int goldReward, int xpReward){
		plugin.voteHandler.reload();
		plugin.voteHandler.getConfiguration().set(player.getName(), getVoteTokens(player.getName()) - voteTokens);
		plugin.voteHandler.save();
		plugin.goldHandler.reload();
		plugin.goldHandler.getConfiguration().set(player.getName(), plugin.goldHandler.getConfiguration().getInt(player.getName()) + goldReward);
		plugin.goldHandler.save();
		player.setExp(player.getExp() + xpReward);
	}
	
}
