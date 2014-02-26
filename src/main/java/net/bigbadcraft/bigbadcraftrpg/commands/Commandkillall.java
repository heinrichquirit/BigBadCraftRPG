package main.java.net.bigbadcraft.bigbadcraftrpg.commands;

import java.util.EnumSet;
import java.util.List;

import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Commandkillall extends BaseCommand{
	
	EnumSet<EntityType> animals = EnumSet.of(EntityType.COW, EntityType.HORSE, EntityType.IRON_GOLEM, EntityType.MUSHROOM_COW,
			EntityType.OCELOT, EntityType.PIG, EntityType.SHEEP, EntityType.SNOWMAN, EntityType.SQUID, EntityType.VILLAGER);
	
	EnumSet<EntityType> monsters = EnumSet.of(EntityType.BAT, EntityType.BLAZE, EntityType.CAVE_SPIDER, EntityType.CREEPER,
			EntityType.ENDER_DRAGON, EntityType.ENDERMAN, EntityType.GHAST, EntityType.GIANT, EntityType.MAGMA_CUBE,
			EntityType.PIG_ZOMBIE, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SLIME, EntityType.SPIDER,
			EntityType.WITCH, EntityType.WITCH, EntityType.WOLF, EntityType.ZOMBIE);

	@Override
	public void execute(Player player, Command cmd, String[] args) {
		// TODO Auto-generated method stub
		String world = player.getWorld().getName();
		List<Entity> entityList = Bukkit.getWorld(world).getEntities();
		if (args.length == 0){
			Utils.makeMessage(player, "Incorrect syntax, usage: /killall animals or /killall monsters"); 
		}
		else if (args.length == 1){
			if (args[0].equalsIgnoreCase("animals")){
				int count = 0;
				for (Entity entities:entityList){
					if (animals.contains(entities.getType())){
						count++;
						entities.remove();
					}
				}
				Utils.makeMessage(player, "Eliminated " + count + " animals in " + world);
			}
			else if (args[0].equalsIgnoreCase("monsters")){
				int count = 0;
				for (Entity entities:entityList){
					if (monsters.contains(entities.getType())){
						count++;
						entities.remove();
					}
				}
				Utils.makeMessage(player, "Eliminated " + count + " monsters in " + world); 
			}
		}
	}

}
