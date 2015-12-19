package net.inkzzz.mineflow.customenchantments.managers;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EnchantmentManager {
	
	public boolean hasEnchantment(final ItemStack itemstack, final Enchantment enchantment) {
		return itemstack.getEnchantments().containsKey(enchantment);
	}
	
	public int getEnchantmentLevel(final ItemStack itemstack, final Enchantment enchantment) {
		return itemstack.getEnchantmentLevel(enchantment);
	}
	
	public boolean hasEnchantment(final Player player, final Enchantment enchantment) {

		for(ItemStack itemstack : player.getInventory().getContents()) {
			
			if( itemstack == null ) {
				continue;
			}
			
			if( hasEnchantment(itemstack, enchantment) ) {
				return true;
			}
			
		}
		return false;		
	}
	
	public int getEnchantmenLevel(final Player player, final Enchantment enchantment) {

		int level = 0;
		
		for(ItemStack itemstack : player.getInventory().getArmorContents()) {
			
			if( itemstack == null ) {
				continue;
			}
			
			if( hasEnchantment(itemstack, enchantment) ) {
				
				if( getEnchantmentLevel(itemstack, enchantment) > level ) {
					level = getEnchantmentLevel(itemstack, enchantment);
				}
				
			}
			
		}
		return level;
	}

}
