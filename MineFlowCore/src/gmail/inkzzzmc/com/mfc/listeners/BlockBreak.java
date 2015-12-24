package gmail.inkzzzmc.com.mfc.listeners;

import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreak implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Block block = e.getBlock();
		Player player = e.getPlayer();

		if (block.getType() == Material.MOB_SPAWNER) {
			
			if(!PlayerManager.isDonator(player)) {
				e.setCancelled(true);
				player.sendMessage(Language.SPAWNER_DENY.getMessage());
				return;
			}
			
			ItemStack use = player.getItemInHand();
			
			if(use.getType() == Material.DIAMOND_PICKAXE) {
				if(!use.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
					e.setCancelled(true);
					player.sendMessage(Language.NEED_SILKTOUCH.getMessage());
					return;
				}
			}

			CreatureSpawner spawn = (CreatureSpawner) block.getState();
			int id = spawn.getTypeId();
			
//			if(player.hasPermission("spawner.bypass")) {
//				player.getWorld().dropItem(block.getLocation(),
//						new ItemStack(Material.MOB_SPAWNER, 1, (short) id));
//				return; 
//			}
//
//			if (Main.economy.withdrawPlayer(player, 100000).transactionSuccess()) {
				player.getWorld().dropItem(block.getLocation(),
						new ItemStack(Material.MOB_SPAWNER, 1, (short) id));
//			} else {
//				e.setCancelled(true);
//				player.sendMessage(language.NOT_ENOUGH_MONEY.getMessage());
//			}
		}
	}

}
