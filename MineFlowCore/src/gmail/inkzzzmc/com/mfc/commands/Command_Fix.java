package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;
import gmail.inkzzzmc.com.mfc.utils.ItemUtil;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Command_Fix extends CommandFactory {

	public Command_Fix() {
		super("fix", "mineflow.fix", false);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		Player player = (Player) sender;
		MineFlowPlayer fplayer = PlayerManager.getPlayer(player);
		
		if(!fplayer.canFix()) {
			player.sendMessage(Language.REPAIR_COOLDOWN.getMessage().replace("%time%", Utils.getTime(fplayer.getFixTimestamp())));
			return;
		}
		
		ItemStack itemstack = player.getItemInHand();		
		
		if(itemstack == null || itemstack.getType() == Material.AIR || ItemUtil.cannotFix(itemstack) || itemstack.getDurability() == 0) {
			player.sendMessage(Language.REPAIR_FAIL.getMessage());
			return;
		}
		
		itemstack.setDurability((short) 0);
		fplayer.setFixTimestamp(System.currentTimeMillis() + 1800000);
		player.sendMessage(Language.REPAIR.getMessage());
		return;
		
	}

}
