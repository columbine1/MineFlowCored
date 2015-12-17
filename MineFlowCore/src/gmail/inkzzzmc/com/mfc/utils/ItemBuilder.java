package gmail.inkzzzmc.com.mfc.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.MaterialData;

public class ItemBuilder {
	
	private final ItemStack item;
	
	public ItemBuilder(Material material, int data) {
		this.item = new ItemStack(material, 1, (byte) data);
	}
	
	/**
	 * @param amount - Amount to set the itemstack to.
	 * @return Sets the itemstack amount to @param amount.
	 */
	public ItemBuilder setAmount(int amount) {
		item.setAmount(amount);
		return this;
	}
	
	/**
	 * @param name - Name of itemstack.
	 * @return Sets the name of the itemstack to @param name.
	 */
	public ItemBuilder setName(String name) {
		final ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.color(name));
		item.setItemMeta(meta);
		return this;
	}
	
	/**
	 * @param lore - String array
	 * @return Sets the lore values to @param lore.
	 */
	public ItemBuilder setLore(final String... lore) {
		final ItemMeta meta = item.getItemMeta();
		List<String> lores = new ArrayList<String>();
		for(String s : lore) {
			lores.add(Utils.color(s));
		}
		meta.setLore(lores);
		item.setItemMeta(meta);
		return this;
	}
	
	/**
	 * @param ench - Enchantment to add.
	 * @param level - Level of enchant to add.
	 * @return Adds @param ench with @param level.
	 */
	public ItemBuilder addEnchantment(final Enchantment ench, final int level) {
		item.addUnsafeEnchantment(ench, level);
		return this;
	}
	
	/**
	 * 
	 * ONLY TO BE USED WITH LEATHER ARMOUR!
	 * 
	 * @param color - Color to set the itemstack to.
	 * @return Sets the color of the itemstack to @param color.
	 */
	public ItemBuilder setColor(final Color color) {
		if (item.equals(Material.LEATHER_BOOTS) || item.equals(Material.LEATHER_LEGGINGS) || item.equals(Material.LEATHER_CHESTPLATE) || 
				item.equals(Material.LEATHER_HELMET)) {
			final LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
			meta.setColor(color);
			item.setItemMeta(meta);
		}
		else {
			throw new IllegalArgumentException("setColor can only be used on leather armour!");
		}
		return this;
	}
	
	/**
	 * @param durability - Desired durability.
	 * @return Sets the itemstacks durability to @param durability.
	 */
	public ItemBuilder setDurability(final int durability) {
		if (durability >= Short.MIN_VALUE && durability <= Short.MAX_VALUE) {
			item.setDurability((short)durability);
		}
		else {
			throw new IllegalArgumentException("The durability must be a short!");
		}
		return this;
	}
	
	/**
	 * @param flag - ItemFlag value.
	 * @return Adds @param flag to the itemstack.
	 */
	public ItemBuilder addFlag(final ItemFlag flag) {
		final ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(flag);
		item.setItemMeta(meta);
		return this;
	}
	
	/**
	 * @param data - Material Data value.
	 * @return Sets the itemstack byte data to @param data.
	 */
	public ItemBuilder setData(MaterialData data) {
		final ItemMeta meta = item.getItemMeta();
		item.setData(data);
		item.setItemMeta(meta);
		return this;
	}

	/**
	 * @return the final itemstack.
	 */
	public final ItemStack getStack() {
		return this.item;
	}
	
}
