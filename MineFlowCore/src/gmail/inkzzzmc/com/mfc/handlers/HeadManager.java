package gmail.inkzzzmc.com.mfc.handlers;

import gmail.inkzzzmc.com.mfc.Main;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;
import gmail.inkzzzmc.com.mfc.utils.ItemBuilder;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadManager {

	private static HeadManager instance;
	
	/**
	 * @return the instance of the class.
	 */
	public static HeadManager getInstance() {
		if(instance == null) {
			instance = new HeadManager();
		}
		return instance;
	}
	
	/**
	 * @param player - Killer player object.
	 * @param dead - Dead player object.
	 */
	public void dropHeads(Player player, Player dead) {
		
		String rank = null;
		int chance = 0;
		
		for(String ranks : Main.ranks) {
			if(PlayerManager.isRank(player, ranks)) {
				rank = ranks;
				break;
			}
		}
		
		if(rank == null) {
			chance = 5;
		}
		
		switch(rank.toLowerCase()) {
		
		case "epic":
			chance = 10;
			break;
			
		case "mythic":
			chance = 20;
			break;
			
		case "omega":
			chance = 30;
			break;
			
		case "titan":
			chance = 40;
			break;
			
		case "legend":
			chance = 50;
			break;
		
		default:
			break;
			
		}
		
		if(Utils.canDropHead(chance)) {
			dead.getWorld().dropItemNaturally(dead.getLocation(), getHead(player, dead));
		}
		
	}
	
	/**
	 * @param killer - Killer player object.
	 * @param dead - Dead player object.
	 * @return a head itemstack with information of the players name, killer and location of death.
	 */
	public ItemStack getHead(Player killer, Player dead) {
		
		Location loc = dead.getLocation();
		
		ItemBuilder item = new ItemBuilder(Material.SKULL_ITEM, 3);
		item.setName("&fSkull of &e&o" + dead.getName());
		item.setLore("&7Defeated by &f"+killer.getName() + " &7on", "&f"+Utils.getDate()+" &7at", "&f" + ((int)loc.getX()) +", "+((int)loc.getY())+", "+((int)loc.getZ()) + " &7with a", "&f"+(killer.getItemInHand().hasItemMeta() ? killer.getItemInHand().getItemMeta().getDisplayName() : killer.getItemInHand().getType().toString()),"");
		
		ItemStack stack = item.getStack();
		
		SkullMeta sk = (SkullMeta) stack.getItemMeta();
		sk.setOwner(dead.getName());
		stack.setItemMeta(sk);
		
		return stack;
	}
	
}
