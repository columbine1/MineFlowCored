package gmail.inkzzzmc.com.mfc.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemUtil {
	
	/**
	 * @param itemstack - ItemStack to check.
	 * @return Checks if @param itemstack is a GOD APPLE.
	 */
	public static boolean isGapple(ItemStack itemstack) {
		return itemstack.getType() == Material.GOLDEN_APPLE && itemstack.getDurability() == 1;
	}
	
	/**
	 * @param itemstack - ItemStack to check.
	 * @return Checks if @param itemstack isn't a GOD APPLE or POTION.
	 */
	public static boolean cannotFix(ItemStack itemstack) {
		return isGapple(itemstack) || itemstack.getType() == Material.POTION;
	}
	
	/**
	 * @param itemstack - ItemStack to check
	 * @return Checks if @param itemstack is a golden apple.
	 */
	public static boolean isGoldenApple(ItemStack itemstack) {
		return itemstack.getType() == Material.GOLDEN_APPLE && itemstack.getDurability() == 0;
	}

}
