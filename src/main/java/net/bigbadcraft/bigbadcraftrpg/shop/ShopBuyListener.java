package main.java.net.bigbadcraft.bigbadcraftrpg.shop;

import main.java.net.bigbadcraft.bigbadcraftrpg.BigBadCraftRPG;
import main.java.net.bigbadcraft.bigbadcraftrpg.utils.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ShopBuyListener implements Listener{
	
	private final ChatColor GREEN = ChatColor.GREEN;
	private final ChatColor WHITE = ChatColor.WHITE;
	
	private BigBadCraftRPG p;
	public ShopBuyListener(BigBadCraftRPG p){
		this.p = p;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority=EventPriority.HIGH,ignoreCancelled=true)
	public void onShopBuy(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
			if (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN){
				Sign sign = (Sign) block.getState();
				boolean isBuySign = sign.getLine(0).equalsIgnoreCase("(buy)")
						|| sign.getLine(0).equalsIgnoreCase(WHITE+"("+GREEN+"buy"+WHITE+")");
				if (isBuySign){
					String[] lines = sign.getLines();
					int itemId = Utils.parseInt(ChatColor.stripColor(lines[1]).trim());
					int itemQty = Utils.parseInt(ChatColor.stripColor(lines[2]).split(" ")[0]);
					int itemPrice = Utils.parseInt(ChatColor.stripColor(lines[3]).split(" ")[0]);
					if (player.getInventory().firstEmpty() == -1){
						Utils.makeMessage(player, "Empty a spot in your inventory before purchasing.");
						return;
					}
					int playerGold = p.goldHandler.getConfiguration().getInt(player.getName());
					if (itemPrice > playerGold){
						Utils.makeMessage(player, "You need " + (itemPrice - playerGold) + " more gold to complete your purchase.");
						return;
					}
					String itemName = Material.getMaterial(itemId).name().toLowerCase();
					player.getInventory().addItem(new ItemStack(Material.getMaterial(itemId), itemQty));
					player.updateInventory();
					p.goldHandler.reload();
					p.goldHandler.getConfiguration().set(player.getName(), playerGold - itemPrice);
					p.goldHandler.save();
					Utils.makeMessage(player, "Successfully made a purchase of " + itemQty + " " + itemName + " for " + itemPrice + " gold.");
				}
			}
		}
	}
}