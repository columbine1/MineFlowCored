package net.inkzzz.mineflow.customenchantments.enchantments;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class Protector extends Enchantment {

	public Protector(int id) {
		super(id);
	}

	@Override
	public boolean canEnchantItem(ItemStack itemstack) {
		return itemstack.getType() == Material.DIAMOND_HELMET
				|| itemstack.getType() == Material.DIAMOND_CHESTPLATE
				|| itemstack.getType() == Material.DIAMOND_LEGGINGS
				|| itemstack.getType() == Material.DIAMOND_BOOTS;
	}

	@Override
	public boolean conflictsWith(Enchantment arg0) {
		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.ARMOR;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public String getName() {
		return "Protector";
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

}