package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;

import org.bukkit.command.CommandSender;

public class Command_Logout extends CommandFactory {

	public Command_Logout() {
		super("logout", false);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		MineFlowPlayer player = PlayerManager.getPlayer(sender);
		
		player.getPlayer().sendMessage(player.isInCombat() ? Language.IN_COMBAT.getMessage().replace("%time%", String.valueOf(player.getCombat())) : Language.NOT_IN_COMBAT.getMessage());
		
	}

}
