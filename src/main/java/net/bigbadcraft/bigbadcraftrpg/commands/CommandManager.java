package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Permission;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor{

	private BigBadCraftRPG plugin;
	public CommandManager(BigBadCraftRPG plugin){
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if (!(sender instanceof Player)){
			return true;
		}
		Player player = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("viewgold")){
			new Commandviewgold().execute(player, cmd, args);
		}
		else if (cmd.getName().equalsIgnoreCase("bounty")){
			new Commandbounty().execute(player, cmd, args);
		}
		else if (cmd.getName().equalsIgnoreCase("day")){
			if (Utils.checkPermission(player, Permission.DAY)){
				new Commandday().execute(player, cmd, args);
			}
		}
		else if (cmd.getName().equalsIgnoreCase("fly")){
			if (Utils.checkPermission(player, Permission.FLY)){
				new Commandfly().execute(player, cmd, args);
			}
		}
		else if (cmd.getName().equalsIgnoreCase("give")){
			if (Utils.checkPermission(player, Permission.GIVE)){
				new Commandgive().execute(player, cmd, args);
			}
		}
		else if (cmd.getName().equalsIgnoreCase("god")){
			if (Utils.checkPermission(player, Permission.GOD)){
				new Commandgod(plugin).execute(player, cmd, args);
			}
		}
		else if (cmd.getName().equalsIgnoreCase("killall")){
			if (Utils.checkPermission(player, Permission.KILL_ALL)){
				new Commandkillall().execute(player, cmd, args);
			}
		}
		else if (cmd.getName().equalsIgnoreCase("list")){
			if (Utils.checkPermission(player, Permission.LIST)){
				new Commandlist().execute(player, cmd, args);
			}
		}
		else if (cmd.getName().equalsIgnoreCase("night")){
			if (Utils.checkPermission(player, Permission.NIGHT)){
				new Commandnight().execute(player, cmd, args);
			}
		}
		else if (cmd.getName().equalsIgnoreCase("spawnmob")){
			if (Utils.checkPermission(player, Permission.SPAWN_MOB)){
				new Commandspawnmob(plugin).execute(player, cmd, args);
			}
		}
		return false;
	}
	
}
