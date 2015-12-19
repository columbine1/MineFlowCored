package net.inkzzz.mineflow.customenchantments.managers;

import java.util.ArrayList;
import java.util.List;

import net.inkzzz.mineflow.customenchantments.utils.ItemBuilder;
import net.inkzzz.mineflow.customenchantments.utils.Utils;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {
	
	/*
	 * Checks if @param itemstack is a protected rune.
	 */
	public boolean isProtectedRune(final ItemStack itemstack) {
		if( itemstack != null ) {
			if( itemstack.getType() == Material.EMPTY_MAP ) {
				if( itemstack.hasItemMeta() ) {
					if( itemstack.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.toColor("&eProtection Rune")) ) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * @return an itemstack object of the Protection Rune.
	 */
	public ItemStack getProtectionRune() {
		final ItemBuilder builder = new ItemBuilder(Material.EMPTY_MAP, 0);
		builder.setAmount(1);
		builder.setName("&eProtection Rune");
		builder.setLore(
				"&7Prevents an item from being destroyed",
				"&7due to the failed enchantment rune.",
				"&ePlace of item to apply.");
		return builder.getStack();
	}
	
	/**
	 * 
	 * Applies the protection rune to @param itemstack.
	 * 
	 * @param itemstack - ItemStack to apply protection rune to.
	 * @return Itemstack object with the protection rune applied.
	 */
	public ItemStack applyProtectionRune(final ItemStack itemstack) {
		
		final ItemMeta meta = itemstack.getItemMeta();
		List<String> lore = null;
		
		if(itemstack.hasItemMeta()) {
			lore = new ArrayList<String>(meta.getLore());
		} else {
			lore = new ArrayList<String>();
		}
		
		lore.add(Utils.toColor("&f&lPROTECTION RUNE"));
		meta.setLore(lore);
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	/*
	 * Spawns a firework at @param player's location.
	 */
	public Firework spawnFirework(final Player player){
		Firework firework = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
		firework.setFireTicks(1);
		FireworkMeta meta = firework.getFireworkMeta();
		meta.addEffect(FireworkEffect.builder().withColor(Color.AQUA, Color.RED).with(Type.BURST).flicker(true).build());
		firework.setFireworkMeta(meta);
		return firework;
	}

}
