package gmail.inkzzzmc.com.mfc.listeners;

import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class FlyListener implements Listener {

    @EventHandler
    public void onFly(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();
        MineFlowPlayer fPlayer = PlayerManager.getPlayer(player);

        if(fPlayer.isInCombat()) {
            e.setCancelled(true);
        }
    }

}
