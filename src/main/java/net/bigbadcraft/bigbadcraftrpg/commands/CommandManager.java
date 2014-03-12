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
		String cmdName = cmd.getName();
		if (cmdName.equalsIgnoreCase("viewgold")){
			if (Utils.checkPermission(player, Permission.VIEW_GOLD)){
				new Commandviewgold().execute(player, cmd, args);
			}
		}
		else if (cmdName.equalsIgnoreCase("spawn")){
			new Commandspawn(plugin).execute(player, cmd, args);
		}
		else if (cmdName.equalsIgnoreCase("clearinventory")){
			new Commandclearinventory().execute(player, cmd, args);
		}
		else if (cmdName.equalsIgnoreCase("rules")){
			if (Utils.checkPermission(player, Permission.RULES)){
				new Commandrules(plugin).execute(player, cmd, args);
			}
		}
		else if (cmdName.equalsIgnoreCase("bounty")){
			if (Utils.checkPermission(player, Permission.BOUNTY)){
				new Commandbounty().execute(player, cmd, args);
			}
		}
		else if (cmdName.equalsIgnoreCase("votetoken")){
			if (Utils.checkPermission(player, Permission.VOTE_TOKEN)){
				new Commandvotetoken(plugin).execute(player, cmd, args);
			}
		}
		else if (cmdName.equalsIgnoreCase("paygold")){
			if (Utils.checkPermission(player, Permission.PAY_GOLD)){
				new Commandpaygold(plugin).execute(player, cmd, args);
			}
		}
		else if (cmdName.equalsIgnoreCase("gamemode")){
			if (Utils.checkPermission(player, Permission.GAMEMODE)){
				new Commandgamemode().execute(player, cmd, args);
			}
		}
		else if (cmdName.equalsIgnoreCase("day")){
			if (Utils.checkPermission(player, Permission.DAY)){
				new Commandday().execute(player, cmd, args);
			}
		}
		else if (cmdName.equalsIgnoreCase("fly")){
			if (Utils.checkPermission(player, Permission.FLY)){
				new Commandfly().execute(player, cmd, args);
			}
		}
		else if (cmdName.equalsIgnoreCase("give")){
			if (Utils.checkPermission(player, Permission.GIVE)){
				new Commandgive().execute(player, cmd, args);
			}
		}
		else if (cmdName.equalsIgnoreCase("god")){
			if (Utils.checkPermission(player, Permission.GOD)){
				new Commandgod(plugin).execute(player, cmd, args);
			}
		}
		else if (cmdName.equalsIgnoreCase("killall")){
			if (Utils.checkPermission(player, Permission.KILL_ALL)){
				new Commandkillall().execute(player, cmd, args);
			}
		}
		else if (cmdName.equalsIgnoreCase("list")){
			if (Utils.checkPermission(player, Permission.LIST)){
				new Commandlist().execute(player, cmd, args);
			}
		}
		else if (cmdName.equalsIgnoreCase("night")){
			if (Utils.checkPermission(player, Permission.NIGHT)){
				new Commandnight().execute(player, cmd, args);
			}
		}
		else if (cmdName.equalsIgnoreCase("spawnmob")){
			if (Utils.checkPermission(player, Permission.SPAWN_MOB)){
				new Commandspawnmob(plugin).execute(player, cmd, args);
			}
		}
		return false;
	}
	
}
