package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.levels.Level;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Levels extends CommandFactory {

	public Command_Levels() {
		super("levels", true);
	}
	
	/**
	 * 
	 * levels discount <amount> <player|all> <time>
	 * 
	 */

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(sender instanceof Player) return;
		
		if(args.length < 4) {
			return;
		}
		
		if(args[0].equalsIgnoreCase("discount")) {
			
			if(Utils.isDouble(args[1]) && Utils.isNumber(args[3])) {
				
				double amount = Double.valueOf(args[1]);
				int time = Integer.valueOf(args[3]);
				
				if(args[2].equalsIgnoreCase("all")) {
					
					if(Level.isGlobalDiscount()) {
						
						if(Level.global_discount > amount) {
							Level.setGlobalDiscount(Level.global_discount, Level.discount_timestamp + TimeUnit.MINUTES.toMillis(time));
						} else {
							Level.setGlobalDiscount(amount, Level.discount_timestamp + TimeUnit.MINUTES.toMillis(time));
						}
						
					} else {
						Level.setGlobalDiscount(amount, System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(time));
					}
					
					Bukkit.broadcastMessage(Language.LEVELS_DISCOUNT_ALL.getMessage().replace("%amount%", String.valueOf((amount * 100) + "%")).replace("%time%", String.valueOf(time + " minutes")));
					return;
				}
				
				Player target = Bukkit.getPlayer(args[2]);
				
				if(target == null) {
					sender.sendMessage(Language.PLAYER_OFFLINE.getMessage().replace("%player%", args[2]));
					return;
				}
				
				MineFlowPlayer fplayer = PlayerManager.getPlayer(target);
				
				fplayer.setLevelDiscount(amount);
				fplayer.setLevelDiscountTimeStamp(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(time));
				fplayer.getPlayer().sendMessage(Language.LEVELS_DISCOUNT.getMessage().replace("%amount%", String.valueOf((amount * 100) + "%")).replace("%time%", String.valueOf(time + " minutes")));
				return;
				
			}
			
		}
		
	}

}
