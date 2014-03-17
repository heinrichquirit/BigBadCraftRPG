package main.java.net.bigbadcraft.bigbadcraftrpg.shop;

import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Permission;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class ShopCreateListener implements Listener{
	
	private final ChatColor GREEN = ChatColor.GREEN;
	private final ChatColor WHITE = ChatColor.WHITE;
	private final ChatColor GOLD = ChatColor.GOLD;
	
	@EventHandler(priority=EventPriority.HIGH,ignoreCancelled=true)
	public void onShopCreate(SignChangeEvent event){
		Player player = event.getPlayer();
		boolean isBuySign = event.getLine(0).equalsIgnoreCase("(buy)")
				|| event.getLine(0).equalsIgnoreCase(WHITE+"("+GREEN+"buy"+WHITE+")");
		if (isBuySign){
			if (!Utils.checkPermission(player, Permission.SHOP_CREATE)){
				event.setCancelled(true);
				return;
			}
			String id = event.getLine(1);
			if (!Utils.isNumeric(id)){
				Utils.makeMessage(player, "You must use an item id for the 2nd line.");
				return;
			}
			int itemId = Utils.parseInt(id);
			if (!((itemId >= 0 && itemId <= 422) || (itemId >= 2256 && itemId <= 2267))){
				Utils.makeMessage(player, "Invalid item id for the 2nd line.");
				return;
			}
			String quantity = event.getLine(2);
			if (!Utils.isNumeric(quantity)){
				Utils.makeMessage(player, "You must use numbers for the 3rd line.");
				return;
			}
			int amount = Utils.parseInt(quantity);
			if (!(amount > 0 && amount <= 64)){
				Utils.makeMessage(player, "Quantity must range between 1-64");
				return;
			}
			String price = event.getLine(3);
			if (!Utils.isNumeric(price)){
				Utils.makeMessage(player, "You must use numbers for the 4th line.");
				return;
			}
			event.setLine(0, WHITE + "(" + GREEN + "Buy" + WHITE + ")");
			event.setLine(1, WHITE + id);
			event.setLine(2, WHITE + quantity + " amount");
			event.setLine(3, WHITE + price + " " + GOLD + "gold");
			Utils.makeMessage(player, "Successfully created a shop sign.");
		}
	}
}
