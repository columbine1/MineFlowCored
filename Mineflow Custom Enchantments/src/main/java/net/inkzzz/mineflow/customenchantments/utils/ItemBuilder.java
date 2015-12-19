package net.inkzzz.mineflow.customenchantments.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

public class ItemBuilder {
	
	private final ItemStack item;
	
	public ItemBuilder(Material material, int data) {
		this.item = new ItemStack(material, 1, (byte) data);
	}
	
	public ItemBuilder setAmount(int amount) {
		item.setAmount(amount);
		return this;
	}
	
	public ItemBuilder setName(String name) {
		final ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.toColor(name));
		item.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder setLore(final String... lore) {
		final ItemMeta meta = item.getItemMeta();
		List<String> lores = new ArrayList<String>();
		for(String s : lore) {
			lores.add(Utils.toColor(s));
		}
		meta.setLore(lores);
		item.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder addEnchantment(final Enchantment ench, final int level) {
		item.addUnsafeEnchantment(ench, level);
		return this;
	}
	
	public ItemBuilder setColor(final Color color) {
		if (item.getType().equals(Material.LEATHER_BOOTS) || item.getType().equals(Material.LEATHER_LEGGINGS) || item.getType().equals(Material.LEATHER_CHESTPLATE) || 
				item.getType().equals(Material.LEATHER_HELMET)) {
			final LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
			meta.setColor(color);
			item.setItemMeta(meta);
		}
		else {
			throw new IllegalArgumentException("setColor can only be used on leather armour!");
		}
		return this;
	}
	
	public ItemBuilder setDurability(final int durability) {
		if (durability >= Short.MIN_VALUE && durability <= Short.MAX_VALUE) {
			item.setDurability((short)durability);
		}
		else {
			throw new IllegalArgumentException("The durability must be a short!");
		}
		return this;
	}
	
	public ItemBuilder setData(MaterialData data) {
		final ItemMeta meta = item.getItemMeta();
		item.setData(data);
		item.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder setSkullMeta(String name) {
		
		SkullMeta sk = (SkullMeta) item.getItemMeta();
		sk.setOwner(name);
		item.setItemMeta(sk);
		return this;
		
	}

	public ItemStack getStack() {
		return this.item;
	}
	
}
