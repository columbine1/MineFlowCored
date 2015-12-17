package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Lightning extends CommandFactory {

	public Command_Lightning() {
		super("lightning", false);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		Player player = (Player) sender;
		MineFlowPlayer fplayer = PlayerManager.getPlayer(player);
		
		if(!fplayer.canUseLightning()) {
			player.sendMessage(Language.LIGHTNING_COOLDOWN.getMessage().replace("%time%", Utils.getTime(fplayer.getLightningTimestamp())));
			return;
		}
		
		Set<Material> transparent = new HashSet<Material>();
		transparent.add(Material.AIR);
		Location location = player.getTargetBlock(transparent, 50).getLocation();
		
		fplayer.setLightningTimestamp(System.currentTimeMillis() + 3600000);
		player.getWorld().strikeLightning(location);
		player.sendMessage(Language.LIGHTNING.getMessage());
		return;
		
	}
	
}
