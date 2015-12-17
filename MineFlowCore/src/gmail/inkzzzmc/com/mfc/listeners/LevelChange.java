package gmail.inkzzzmc.com.mfc.listeners;

import gmail.inkzzzmc.com.mfc.Main;
import gmail.inkzzzmc.com.mfc.events.LevelChangeEvent;
import gmail.inkzzzmc.com.mfc.levels.Level;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LevelChange implements Listener {

	@EventHandler
	public void onLevelChange(LevelChangeEvent e) {
		
		MineFlowPlayer player = e.getPlayer();
		Level level = e.getLevel();
		
		for(String permission : level.getPermissions()) {
			Main.permission.playerAdd(player.getPlayer(), permission);
		}
		
	}
 	
}
