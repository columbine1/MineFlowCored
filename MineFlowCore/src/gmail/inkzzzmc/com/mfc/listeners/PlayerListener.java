package gmail.inkzzzmc.com.mfc.listeners;

import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;
import gmail.inkzzzmc.com.mfc.utils.PotionUtil;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffectType;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

public class PlayerListener implements Listener {

	@EventHandler
	public void onJoin(final PlayerJoinEvent e) {

		Player player = e.getPlayer();
		
		if(!PlayerManager.isMineFlowPlayer(player)) {
			MineFlowPlayer fplayer = new MineFlowPlayer(player);
			PlayerManager.addPlayer(fplayer);
		} else {
			PlayerManager.getPlayer(player).setPlayer(player);
		}
		
	}
	
	@EventHandler
	public void onLeave(final PlayerQuitEvent e) {
		
		MineFlowPlayer player = PlayerManager.getPlayer(e.getPlayer());
		
		if(player.isInCombat()) {
			player.getPlayer().setHealth(0);
		}
		
		player.saveData(true);
		
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		MineFlowPlayer player = PlayerManager.getPlayer(e.getPlayer());
		if(player.isInCombat()) { player.setCombat(0); }
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			
			Player player = (Player) e.getEntity();
			Player damager = (Player) e.getDamager();
			MineFlowPlayer fplayer = PlayerManager.getPlayer(player);
			MineFlowPlayer fdamager = PlayerManager.getPlayer(damager);

			if(!fplayer.isInCombat()) {	player.sendMessage(Language.PLAYER_TAG.getMessage().replace("%player%", damager.getName())); }
			if(!fdamager.isInCombat()) { damager.sendMessage(Language.ATTACKER_TAG.getMessage().replace("%player%", player.getName())); }
			
			FPlayer fp = FPlayers.getInstance().getByPlayer(player);
			FPlayer dp = FPlayers.getInstance().getByPlayer(damager);
			
			if(!fp.hasFaction() || !dp.hasFaction()) {
				fplayer.setCombat(15);
				fdamager.setCombat(15);
				return;
			}
			
			if(fp.getFactionId().equalsIgnoreCase(dp.getFactionId()) ) {
				return;
			}
			
			fplayer.setCombat(15);
			fdamager.setCombat(15);
			
		}
	}
	
	@EventHandler
	public void onXPReceive(PlayerExpChangeEvent e) {
		int xp = Integer.valueOf(e.getAmount() * (int) PlayerManager.getPlayer(e.getPlayer()).getXPBoost()); 
		e.setAmount(xp);
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
	
		if(!(e.getEntity() instanceof Player)) return;
		if(e.getCause() != DamageCause.FALL) return;
		
		Player player = (Player) e.getEntity();
		MineFlowPlayer fplayer = PlayerManager.getPlayer(player);
		
		if(fplayer.hasJellyLegs()) {
			e.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
		Player player = e.getPlayer();
		MineFlowPlayer fplayer = PlayerManager.getPlayer(player);
		
		if(fplayer==null || !fplayer.getPlayer().isOnline()) {
			return;
		}
		
		if(PotionUtil.hasPotion(player, PotionEffectType.INCREASE_DAMAGE, 1)) {
			player.sendMessage(Language.STRENGTH_DISABLE.getMessage());
			player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
		}
		
		if(player.isOp()) {
			return;
		}
		
		if(fplayer.isInCombat()) {
			if(player.isFlying()) {
				player.setFlying(false);
				player.setAllowFlight(false);
				player.sendMessage(Language.NO_FLY_COMBAT.getMessage());
			}
		}
		
	}
	
	@EventHandler
	public void onTeleport(PlayerTeleportEvent e) {
		
		Player player = e.getPlayer();
		MineFlowPlayer fplayer = PlayerManager.getPlayer(player);
		
		if(player.isOp()) return;
		
		if(fplayer.isInCombat()) {
			e.setCancelled(true);
			player.sendMessage(Language.NO_TP_COMBAT.getMessage());
		}
		
	}
	
}
