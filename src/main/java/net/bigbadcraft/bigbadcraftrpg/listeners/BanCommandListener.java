package main.java.net.bigbadcraft.bigbadcraftrpg.listeners;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BanCommandListener implements Listener{
	
	private BigBadCraftRPG p;
	public BanCommandListener(BigBadCraftRPG p){
		this.p = p;
	}
	
	@EventHandler(priority=EventPriority.HIGH,ignoreCancelled=true)
	public void onCommandPreProcess(PlayerCommandPreprocessEvent event){
		Player player = event.getPlayer();
		String message = event.getMessage();
		if (containsBannedSyntax(message)){
			event.setCancelled(true);
			Utils.makeMessage(player, "You're not allowed to use the following command syntaxes:");
			int count = 1;
			for (String cmdSyntax:p.bannedCommands){
				Utils.makeMessage(player, (count++) + ". " + cmdSyntax);
			}
		}
	}
	
	private boolean containsBannedSyntax(String message){
		for (String s:p.bannedCommands){
			if (message.contains(s)){
				return true;
			}
		}
		return false;
	}
}
