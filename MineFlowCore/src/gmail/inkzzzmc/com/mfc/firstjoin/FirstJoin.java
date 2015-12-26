package gmail.inkzzzmc.com.mfc.firstjoin;

import gmail.inkzzzmc.com.mfc.Main;
import gmail.inkzzzmc.com.mfc.utils.FileUtil;
import gmail.inkzzzmc.com.mfc.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstJoin implements Listener {

    private Main plugin;

    public FirstJoin(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();

        if(!player.hasPlayedBefore()) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                FileConfiguration fjMsgs = FileUtil.getFile("firstjoinmessages.yml");
                @Override
                public void run() {
                    for(Player p : Bukkit.getOnlinePlayers()) {
                        //All staff
                        if(p.hasPermission("litebans.mute")) {
                            if(fjMsgs.get("messages." + p.getUniqueId()) != null) {
                                p.chat(Utils.color(fjMsgs.getString("messages." + p.getUniqueId()).replace("%newplayer%", player.getName())));
                            }
                        }
                    }
                }
            }, 40L);
        }
    }

}
