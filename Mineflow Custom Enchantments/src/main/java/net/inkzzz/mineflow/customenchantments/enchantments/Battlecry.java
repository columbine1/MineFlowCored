package net.inkzzz.mineflow.customenchantments.enchantments;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class Battlecry extends Enchantment {

	public Battlecry(int id) {
		super(id);
	}

	@Override
	public boolean canEnchantItem(ItemStack itemstack) {
		return itemstack.getType() == Material.DIAMOND_AXE;
	}

	@Override
	public boolean conflictsWith(Enchantment arg0) {
		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.TOOL;
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public String getName() {
		return "Battlecry";
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

}
