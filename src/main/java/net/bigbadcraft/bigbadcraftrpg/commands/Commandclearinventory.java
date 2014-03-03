package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Permission;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Commandclearinventory extends BaseCommand {

	@Override
	public void execute(Player player, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 0){
			if (Utils.checkPermission(player, Permission.CLEAR_INVENTORY)){
				player.getInventory().clear();
				Utils.makeMessage(player, "Successfully cleared your inventory.");
			}
		}
		else if (args.length == 1){
			if (Utils.checkPermission(player, Permission.CLEAR_INVENTORY_OTHERS)){
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null){
					Utils.makeMessage(player, "Cannot clear offline player's inventory.");
					return;
				}
				target.getInventory().clear();
				Utils.makeMessage(player, "Successfully cleared " + target.getName() + "'s inventory.");
				Utils.makeMessage(target, player.getName() + " has cleared your inventory.");
			}
		}
	}

}
