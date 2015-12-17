package gmail.inkzzzmc.com.mfc.threads;

import gmail.inkzzzmc.com.mfc.levels.Level;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DiscountTimer extends BukkitRunnable {

	public void run() {
		
		if(Level.isGlobalDiscount()) {
			if(System.currentTimeMillis() > Level.discount_timestamp) {
				
				Level.setGlobalDiscount(0, 0);
				
			}
		}
		
		for(Player players : Bukkit.getOnlinePlayers()) {
			
			final MineFlowPlayer player = PlayerManager.getPlayer(players);
			
			if(player == null) {
				continue;
			}
			
			if(player.hasDiscount()) {
				
				if(System.currentTimeMillis() > player.getLevelDiscountTimestamp()) {
					
					player.setLevelDiscount(0);
					player.setLevelDiscountTimeStamp(0);
					
				}
				
			}
			
		}
		
	}
	
}
