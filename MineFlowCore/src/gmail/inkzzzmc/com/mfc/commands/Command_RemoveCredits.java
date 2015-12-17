package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_RemoveCredits extends CommandFactory {

	public Command_RemoveCredits() {
		super("removecredits", "mineflow.admin", true);
	}
	
	/**
	 * removecredits <player> <amount>
	 */

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(args.length == 2) {
			
			final Player target = Bukkit.getPlayer(args[0]);
			
			if(target == null || !Utils.isNumber(args[1])) {
				sender.sendMessage(Language.USAGE.getMessage().replace("%command%", "removecredits <player> <amount>"));
				return;
			}
			
			final int amount = Integer.valueOf(args[1]);
			final MineFlowPlayer fplayer = PlayerManager.getPlayer(target);
			
			if(fplayer.getCredits() >= amount) {
				fplayer.setCredits(fplayer.getCredits() - amount);
				sender.sendMessage(Language.REMOVE_CREDITS.getMessage().replace("%amount%", String.valueOf(amount)).replace("%player%", target.getName()));
				return;
			}
			
			sender.sendMessage(Language.NOT_ENOUGH_CREDITS.getMessage().replace("%player%", target.getName()));
			return;
			
		}
		
		sender.sendMessage(Language.USAGE.getMessage().replace("%command%", "removecredits <player> <amount>"));
		return;
		
	}
	
}
