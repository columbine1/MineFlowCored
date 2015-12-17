package gmail.inkzzzmc.com.mfc.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class CraftItem implements Listener {

	@EventHandler
	public void onCraft(CraftItemEvent e) {
		if(e.getWhoClicked() instanceof Player) {
			if(e.getInventory() instanceof CraftingInventory) {
				ItemStack is = e.getCurrentItem();
				if(is.getType() == Material.GOLDEN_APPLE) {
					if(is.getDurability() == 0) {
						e.setCancelled(true);
					}
				}
			}
		}
	}
	
}
