package gmail.inkzzzmc.com.mfc.threads;

import gmail.inkzzzmc.com.mfc.Main;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerTimer extends BukkitRunnable {

	private Main plugin;

	public PlayerTimer(Main plugin) {
		this.plugin = plugin;
	}

	public void run() {
		
		for(Player players : Bukkit.getOnlinePlayers()) {
		
			final MineFlowPlayer player = PlayerManager.getPlayer(players);
			
			if(player == null) {
				continue;
			}
			
			if(player.isInCombat()) {
				
				player.setCombat(player.getCombat() - 1);
			
				if(player.getCombat() == 0) {
					player.getPlayer().sendMessage(Language.OUT_OF_COMBAT.getMessage());
				} else {
					plugin.getActionBar().send(player.getPlayer(), Language.TAG_BAR_MESSAGE.getMessage().replace("%time%", "" + player.getCombat()));
				}
				
			}
			
			if(player.moneyHasChanged(Main.economy.getBalance(player.getPlayer()))) {
				
				int plus = (int) ((int) Main.economy.getBalance(player.getPlayer()) - player.getMoney());
				int minus = (int) ((int) player.getMoney() - Main.economy.getBalance(player.getPlayer()));
				
				player.getPlayer().sendMessage(player.getMoney() > Main.economy.getBalance(player.getPlayer()) ? Utils.color("&c&l- $" + minus) : Utils.color("&a&l+ $" + plus));
				player.setMoney(Main.economy.getBalance(player.getPlayer()));
				
			}
			
		}
		
	}
	
}
