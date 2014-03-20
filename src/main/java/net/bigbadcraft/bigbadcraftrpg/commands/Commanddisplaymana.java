package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;
import me.confuser.barapi.BarAPI;
import net.lordsofcode.zephyrus.Zephyrus;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Commanddisplaymana extends BaseCommand{
	
	private BigBadCraftRPG p;
	public Commanddisplaymana(BigBadCraftRPG p){
		this.p = p;
	}
	
	@Override
	public void execute(Player player, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 0){
			if (!BarAPI.hasBar(player)){
				final int manaCap = Zephyrus.getUser(player).getLevel() * 100;
				final int mana = Zephyrus.getUser(player).getMana();
				Utils.makeMessage(player, "Toggled mana bar on.");
				Bukkit.getScheduler().scheduleSyncRepeatingTask(p, new BukkitRunnable(){
					public void run(){
						BarAPI.setMessage("---= Mana =---", (mana / manaCap));
					}
				}, 20, 20 * 1);
			} else {
				Utils.makeMessage(player, "Toggled mana bar off.");
				BarAPI.removeBar(player);
			}
		}
	}

}
