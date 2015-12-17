package gmail.inkzzzmc.com.mfc.listeners;

import gmail.inkzzzmc.com.mfc.cooldowns.Cooldown;
import gmail.inkzzzmc.com.mfc.cooldowns.Cooldown.CooldownTypes;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;
import gmail.inkzzzmc.com.mfc.utils.ItemUtil;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ConsumeListener implements Listener {

	private final JavaPlugin plugin;
	
	public ConsumeListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onConsume(PlayerItemConsumeEvent e) {
	
		final Player player = e.getPlayer();
		final MineFlowPlayer fplayer = PlayerManager.getPlayer(player);
		final ItemStack is = e.getItem();
		
		if(ItemUtil.isGapple(is)) {
			e.setCancelled(true);
			return;
		}
		
		if(ItemUtil.isGoldenApple(is)) {
			
			if(fplayer.getGapplesConsumed() == 0) {
				fplayer.setGapplesConsumed(1);
				new Cooldown(fplayer, 60, CooldownTypes.GAPPLE, plugin);
			}
			else 
			{
				
				fplayer.setGapplesConsumed(fplayer.getGapplesConsumed() + 1);
				
				final int consumed = fplayer.getGapplesConsumed();
				
				if(consumed == 2) {
					player.sendMessage(Language.GAPPLE_WARNING.getMessage());
					return;
				}
				
				if(consumed > 2) {
					
					player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20 * 10, 1));
					player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 10, 1));
					player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 10, 1));
					player.sendMessage(Language.GAPPLE_ILLNESS.getMessage());
					return;
					
				}
				
			}
			
		}
		
	}
	
}
