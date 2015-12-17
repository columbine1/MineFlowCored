package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.levels.Level;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Discount extends CommandFactory {

	public Command_Discount() {
		super("discount", false);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		Player player = (Player) sender;
		MineFlowPlayer fplayer = PlayerManager.getPlayer(player);
		
		player.sendMessage(Language.DISCOUNT.getMessage().replace("%global%", String.valueOf(Level.isGlobalDiscount() ? ((Level.global_discount * 100) + "% - " + Utils.getTime(Level.discount_timestamp)) : "None")).replace("%amount%", String.valueOf(fplayer.hasDiscount() ? ((fplayer.getLevelDiscount() * 100) + "% - " + Utils.getTime(fplayer.getLevelDiscountTimestamp())) : "None")));		
		
	}

}
