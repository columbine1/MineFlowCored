package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.events.SkillLevelChangeEvent;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.utils.ItemBuilder;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.skills.SkillType;

public class Command_WithdrawMcMMO extends CommandFactory {

	public Command_WithdrawMcMMO() {
		super("withdrawmcmmo", false);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		Player player = (Player) sender;
		
		if(args.length == 2) {
			if(Utils.isNumber(args[1])) {
				
				int amount = Integer.valueOf(args[1]);
				SkillType skill = SkillType.getSkill(args[0]);
				
				if(skill != null) {
					
					int level = ExperienceAPI.getLevel(player, skill.getName());
					
					if(level >= amount) {
						ExperienceAPI.setLevel(player, skill.getName(), Integer.valueOf(level - amount));
						player.getInventory().addItem(new ItemBuilder(Material.PAPER, 0).setName("&6&lMCMMO XP Voucher &8(&7Right Click&8)").setLore("&6Skill: &7" + skill.getName().toUpperCase(), "&6+" + amount + " &7XP", "&6Trainer: &7" + player.getName(), "&8> &7&oApplies McMMO experience on use.").getStack());
						Bukkit.getServer().getPluginManager().callEvent(new SkillLevelChangeEvent(player, amount, skill.getName(), false));
						return;
					} else {
						player.sendMessage(Language.NOT_ENOUGH_MCMMO.getMessage());
						return;
					}
				}
			} 
		}
		
		player.sendMessage(Language.USAGE.getMessage().replace("%command%", "withdrawmcmmo <skill> <amount>"));
		return;
		
	}

}
