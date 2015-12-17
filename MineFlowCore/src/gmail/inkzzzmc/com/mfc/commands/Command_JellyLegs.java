package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_JellyLegs extends CommandFactory {

	public Command_JellyLegs() {
		super("jellylegs", "mineflow.jellylegs", false);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		Player player = (Player) sender;
		MineFlowPlayer fplayer = PlayerManager.getPlayer(player);
		
		fplayer.setJellyLegs(!fplayer.hasJellyLegs());
		
		String toggle = fplayer.hasJellyLegs() ? "enabled" : "disabled";
		
		player.sendMessage(Language.JELLYLEGS.getMessage().replace("%toggled%", toggle));
		
	}

}
