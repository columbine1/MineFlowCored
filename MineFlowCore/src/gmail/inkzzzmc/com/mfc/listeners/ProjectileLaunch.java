package gmail.inkzzzmc.com.mfc.listeners;

import gmail.inkzzzmc.com.mfc.cooldowns.Cooldown;
import gmail.inkzzzmc.com.mfc.cooldowns.Cooldown.CooldownManager;
import gmail.inkzzzmc.com.mfc.cooldowns.Cooldown.CooldownTypes;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ProjectileLaunch implements Listener {

	private final JavaPlugin plugin;
	
	public ProjectileLaunch(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent e) {
		
		if(!(e.getEntity().getShooter() instanceof Player)) return;
		if(e.getEntityType() != EntityType.ENDER_PEARL) return;
		
		Player player = (Player) e.getEntity().getShooter();
		MineFlowPlayer fplayer = PlayerManager.getPlayer(player);
		
		CooldownTypes type = CooldownTypes.ENDERPEARL;
		
		if(CooldownManager.isCooldown(fplayer, type)) {
			e.setCancelled(true);
			
			Cooldown cooldown = CooldownManager.getCooldown(fplayer, type);
			player.sendMessage(Language.ENDERPEARL_COOLDOWN.getMessage().replace("%time%", String.valueOf(cooldown.getTime() + " seconds")));
			return;
		}
		
		new Cooldown(fplayer, 15, type, plugin);
		player.sendMessage(Language.ENDERPEARL_COOLDOWN_RECEIVE.getMessage());
		return;
		
	}
	
}
