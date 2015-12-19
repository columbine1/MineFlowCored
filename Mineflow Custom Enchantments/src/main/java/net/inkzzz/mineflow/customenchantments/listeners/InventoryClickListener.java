package net.inkzzz.mineflow.customenchantments.listeners;

import net.inkzzz.mineflow.customenchantments.managers.ItemManager;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public class InventoryClickListener implements Listener {
	
	private final ItemManager ITEM_MANAGER;
	
	public InventoryClickListener() {
		this.ITEM_MANAGER = new ItemManager();
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(final InventoryClickEvent event) {
		
		if( !(event.getWhoClicked() instanceof Player) ) {
			return;
		}
		
		final Player player = (Player) event.getWhoClicked();
		final ItemStack new_item = event.getCursor();
		final ItemStack old_item = event.getCurrentItem();
		
		if( new_item == null || old_item == null ) {
			return;
		}

		if(this.ITEM_MANAGER.isProtectedRune(new_item)) {
			this.ITEM_MANAGER.applyProtectionRune(old_item);
			event.setCurrentItem(old_item);
			event.setCursor(new ItemStack(Material.AIR));
			event.setResult(Result.DENY);
			player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 0);
			this.ITEM_MANAGER.spawnFirework(player);
			return;
		}
	}
	
}
