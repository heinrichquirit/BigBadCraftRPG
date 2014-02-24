package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public abstract class BaseCommand {
	
	public abstract void execute(Player player, Command cmd, String[] args);
	
}
