package main.java.net.bigbadcraft.bigbadcraftrpg.listeners;

import java.util.Random;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EntityDropIngotListener implements Listener {
	
	private BigBadCraftRPG plugin;
	public EntityDropIngotListener(BigBadCraftRPG plugin) {
		this.plugin = plugin;
	}
	
	/* We drop random amount of ingots for all monsters */
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onDeath(EntityDeathEvent event) {
		if (event.getEntity() instanceof Monster) {
			if (event.getEntity().getKiller() instanceof Player) {
				Entity entity = event.getEntity();
				Random random = new Random();
				entity.getWorld().dropItem(entity.getLocation(), ingot(random.nextInt(plugin.ingotMaxLimit)));
			}
		}
	}
	
	/*
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onItemRename(InventoryClickEvent event) {
		if (event.getView().getType() == InventoryType.ANVIL) {
			if (event.getRawSlot() == 2) {
				if (event.getView().getItem(0).getType() == Material.GOLD_INGOT && isCurrencyIngot(event.getView().getItem(2))) {
					event.setCancelled(true);
				}
			}
		}
		else if (event.getView().getType() == InventoryType.WORKBENCH) {
			if (event.getSlotType() == SlotType.CRAFTING) {
				if (event.g)
			}
		}
	}
	*/
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onJoin(PlayerJoinEvent event) {
		String name = event.getPlayer().getName();
		if (!plugin.goldConf.contains(name)) {
			plugin.confHandler.reloadGoldConf();
			plugin.goldConf.set(name, 0);
			plugin.confHandler.saveGoldConf();
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onConvert(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
			ItemStack itemHand = player.getItemInHand();
			if (itemHand.getType() == Material.GOLD_INGOT) {
				if (itemHand.hasItemMeta() && isCurrencyIngot(itemHand)) {
					if (itemHand.getAmount() != 1) {
						player.getInventory().setItemInHand(ingot(itemHand.getAmount() - 1));
						plugin.confHandler.reloadGoldConf();
						plugin.goldConf.set(player.getName(), plugin.goldConf.getInt(player.getName()) + 1);
						plugin.confHandler.saveGoldConf();
						player.sendMessage(ChatColor.DARK_AQUA + "Successfully converted to spendable credit.");
					} else {
						player.getInventory().setItemInHand(null);
						plugin.confHandler.reloadGoldConf();
						plugin.goldConf.set(player.getName(), plugin.goldConf.getInt(player.getName()) + 1);
						plugin.confHandler.saveGoldConf();
						player.sendMessage(ChatColor.DARK_AQUA + "Successfully converted to spendable credit.");
					}
				}
			}
		}
	}
	
	private ItemStack ingot(int amount) {
		ItemStack item = new ItemStack(Material.GOLD_INGOT, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Gold_Currency (Right-click to convert)");
		item.setItemMeta(meta);
		return item;
	}
	
	private boolean isCurrencyIngot(ItemStack item) {
		if (item != null) {
			ItemMeta meta = item.getItemMeta();
			if (meta != null) {
				String name = meta.getDisplayName();
				return name != null && name.equals(ChatColor.GOLD + "Gold_Currency (Right-click to convert)");
			}
		}
		return false;
	}
}