package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Credits extends CommandFactory {

	public Command_Credits() {
		super("credits", false);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		final Player player = (Player) sender;
		final MineFlowPlayer fplayer = PlayerManager.getPlayer(player);
		player.sendMessage(Language.CREDITS.getMessage().replace("%amount%", String.valueOf(fplayer.getCredits())));
		return;
		
	}
	
}
