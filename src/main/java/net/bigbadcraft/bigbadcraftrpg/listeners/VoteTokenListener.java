package main.java.net.bigbadcraft.bigbadcraftrpg.listeners;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.vexsoftware.votifier.model.VotifierEvent;

public class VoteTokenListener implements Listener{

	private BigBadCraftRPG plugin;
	public VoteTokenListener(BigBadCraftRPG plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority=EventPriority.HIGH,ignoreCancelled=true)
	public void registerPlayer(PlayerJoinEvent event){
		String name = event.getPlayer().getName();
		if (!plugin.voteHandler.getConfiguration().contains(name)){
			plugin.voteHandler.reload();
			plugin.voteHandler.getConfiguration().set(name, 0);
			plugin.voteHandler.save();
		}
	}
	
	@EventHandler(priority=EventPriority.NORMAL,ignoreCancelled=true)
	public void onVote(VotifierEvent event){
		String voterName = event.getVote().getUsername();
		if (!plugin.voteHandler.getConfiguration().contains(voterName)){
			plugin.voteHandler.reload();
			plugin.voteHandler.getConfiguration().set(voterName, 0);
			plugin.voteHandler.save();
		}
		if (plugin.voteHandler.getConfiguration().contains(voterName)){
			plugin.voteHandler.reload();
			plugin.voteHandler.getConfiguration().set(voterName, plugin.voteTokenManager.getVoteTokens(voterName) + 1);
			plugin.voteHandler.save();
		}
	}
}
