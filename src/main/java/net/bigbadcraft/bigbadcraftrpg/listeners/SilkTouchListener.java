package main.java.net.bigbadcraft.bigbadcraftrpg.listeners;

import java.util.EnumSet;

import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class SilkTouchListener implements Listener{
	
	EnumSet<Material> pstones = EnumSet.of(Material.COAL_ORE, Material.LAPIS_ORE);

	@EventHandler(priority=EventPriority.HIGH,ignoreCancelled=true)
	public void onBreak(BlockBreakEvent event){
		Player player = event.getPlayer();
		ItemStack hand = player.getItemInHand();
		if (pstones.contains(event.getBlock().getType())){
			if (hand.containsEnchantment(Enchantment.SILK_TOUCH)){
				event.setCancelled(true);
				Utils.makeMessage(player, "You are not allowed to do that.");
			}
		}
	}
	
}
