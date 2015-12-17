package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_AddCredits extends CommandFactory {

	public Command_AddCredits() {
		super("addcredits", "mineflow.admin", true);
	}

	/**
	 * addcredits <player> <amount>
	 */
	
	@Override
	public void execute(CommandSender sender, String[] args) {

		if(args.length == 2) {
			
			final Player target = Bukkit.getPlayer(args[0]);
			
			if(target == null || !Utils.isNumber(args[1])) {
				sender.sendMessage(Language.USAGE.getMessage().replace("%command%", "addcredits <player> <amount>"));
				return;
			}
			
			final int amount = Integer.valueOf(args[1]);
			final MineFlowPlayer fplayer = PlayerManager.getPlayer(target);
			fplayer.setCredits(fplayer.getCredits() + amount);
			sender.sendMessage(Language.ADD_CREDITS.getMessage().replace("%amount%", String.valueOf(amount)).replace("%player%", target.getName()));
			return;
			
		}
		
		sender.sendMessage(Language.USAGE.getMessage().replace("%command%", "addcredits <player> <amount>"));
		return;
		
	}
	
}
