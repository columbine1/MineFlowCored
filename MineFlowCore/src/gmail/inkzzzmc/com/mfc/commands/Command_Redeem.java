package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.skills.SkillType;

public class Command_Redeem extends CommandFactory {

	public Command_Redeem() {
		super("redeem", false);
	}
	
	/**
	 * redeem <skill> <amount>
	 */

	@Override
	public void execute(CommandSender sender, String[] args) {

		final Player player = (Player) sender;
		
		if(args.length == 2) {
			
			final MineFlowPlayer fplayer = PlayerManager.getPlayer(player);
			
			final SkillType skill = SkillType.getSkill(args[0]);
			
			if(skill == null || !Utils.isDouble(args[1])) {
				player.sendMessage(Language.USAGE.getMessage().replace("%command%", "redeem <skill> <amount>"));
				return;
			}
			
			final int amount = Integer.valueOf(args[1]);
			
			if(fplayer.getCredits() < amount) {
				player.sendMessage(Language.PLAYER_NOT_ENOUGH_CREDITS.getMessage());
				return;
			}
			
			player.sendMessage(Language.CREDITS_REDEEM.getMessage().replace("%amount%", String.valueOf(amount)).replace("%skill%", skill.getName()));
			fplayer.setCredits(fplayer.getCredits() - amount);
			ExperienceAPI.addLevel(player, skill.getName(), amount);
			return;
		}
		
		player.sendMessage(Language.USAGE.getMessage().replace("%command%", "redeem <skill> <amount>"));
		return;
		
	}
	
}