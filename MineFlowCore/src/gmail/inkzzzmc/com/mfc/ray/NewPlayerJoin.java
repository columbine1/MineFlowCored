package gmail.inkzzzmc.com.mfc.ray;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class NewPlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if(!player.hasPlayedBefore()) {
            if(Bukkit.getPlayer("Raymonds") != null) {
                Player raymonds = Bukkit.getPlayer("Raymonds");
                raymonds.chat("WHALECUM TO MINEFLOW MMO RPG FACTIONS PEEVEEPEE " + player.getName() + "!!!!!!");
            }
        }
    }

}
