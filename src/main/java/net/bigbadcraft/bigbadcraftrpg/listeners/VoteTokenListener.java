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
		if (!plugin.voteTokenConf.contains(name)){
			plugin.confHandler.reloadVoteTokenConf();
			plugin.voteTokenConf.set(name, 0);
			plugin.confHandler.saveVoteTokenConf();
		}
	}
	
	@EventHandler(priority=EventPriority.NORMAL,ignoreCancelled=true)
	public void onVote(VotifierEvent event){
		String voterName = event.getVote().getUsername();
		if (!plugin.voteTokenConf.contains(voterName)){
			plugin.confHandler.reloadVoteTokenConf();
			plugin.voteTokenConf.set(voterName, 0);
			plugin.confHandler.saveVoteTokenConf();
		}
		if (plugin.voteTokenConf.contains(voterName)){
			plugin.confHandler.reloadVoteTokenConf();
			plugin.voteTokenConf.set(voterName, plugin.voteTokenManager.getVoteTokens(voterName) + 1);
			plugin.confHandler.saveVoteTokenConf();
		}
	}
}
