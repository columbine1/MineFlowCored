package net.inkzzz.mineflow.customenchantments.enchantments;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class Execute extends Enchantment {

	public Execute(int id) {
		super(id);
	}

	@Override
	public boolean canEnchantItem(ItemStack itemstack) {
		return itemstack.getType() == Material.DIAMOND_SWORD
				|| itemstack.getType() == Material.DIAMOND_AXE;
	}

	@Override
	public boolean conflictsWith(Enchantment arg0) {
		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public String getName() {
		return "Execute";
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

}