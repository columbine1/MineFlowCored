package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.core.locations.LocationUtil;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Wild extends CommandFactory {

	public Command_Wild() {
		super("wild", false);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		Player player = (Player) sender;
		MineFlowPlayer fplayer = PlayerManager.getPlayer(player);
		
		if(fplayer.canWild()) {
			fplayer.setWildTimeStamp(System.currentTimeMillis() + 300000);
			player.teleport(LocationUtil.getInstance().getRandomLocation(1000, 7000));
			player.sendMessage(Language.WILD.getMessage());
		} else {
			player.sendMessage(Language.WILD_COOLDOWN.getMessage().replace("%time%", Utils.getTime(fplayer.getWildTimeStamp())));
		}
		
	}

}
