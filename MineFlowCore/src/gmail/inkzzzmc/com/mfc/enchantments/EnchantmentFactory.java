package gmail.inkzzzmc.com.mfc.enchantments;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum EnchantmentFactory {

	LIFESTEAL("Lifesteal", 3, EnchantmentFactory.getSwords()),
	BLOCK("Block", 3, EnchantmentFactory.getSwords()),
	ASSASSIN("Assassin", 3, EnchantmentFactory.getSwords()),
	KILLCONFIRM("Kill Confirm", 3, EnchantmentFactory.getSwords()),
	HEX("Hex", 3, EnchantmentFactory.getSwords()),
	JUMP("Jump", 3, EnchantmentFactory.getBoots()),
	SPEED("Speed", 3, EnchantmentFactory.getBoots()),
	SOULSHOT("Soulshot", 5, Arrays.asList(Material.BOW)),
	ZEUS("Zeus", 3, Arrays.asList(Material.BOW));
	
	private final String name;
	private final int max;
	private final List<Material> materials;
	
	EnchantmentFactory(String name, int max, List<Material> materials) {
		this.name = name;
		this.max = max;
		this.materials = materials;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getMaxLevel() {
		return this.max;
	}
	
	public List<Material> getMaterials() {
		return this.materials;
	}
	
	public boolean canEnchant(ItemStack stack) {
		for(Material material : getMaterials()) {
			if(stack.getType() == material) {
				return true;
			}
		}
		return false;
	}
	
	public static List<Material> getSwords() {
		return Arrays.asList(Material.DIAMOND_SWORD, Material.IRON_SWORD, Material.STONE_SWORD, Material.WOOD_SWORD);
	}
	
	public static List<Material> getBoots() {
		return Arrays.asList(Material.DIAMOND_BOOTS, Material.IRON_BOOTS, Material.GOLD_BOOTS, Material.LEATHER_BOOTS);
	}
	
}
