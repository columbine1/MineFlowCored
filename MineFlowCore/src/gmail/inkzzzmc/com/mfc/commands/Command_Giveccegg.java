package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.utils.ItemBuilder;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Giveccegg extends CommandFactory {

	public Command_Giveccegg() {
		super("giveccegg", "mineflow.admin", true);
	}

	/**
	 * giveccegg <player> <amount>
	 */
	
	@Override
	public void execute(CommandSender sender, String[] args) {

		if(args.length == 2) {
			
			final Player target = Bukkit.getPlayer(args[0]);
			
			if(target == null || !Utils.isNumber(args[1])) {
				sender.sendMessage(Language.USAGE.getMessage().replace("%command%", "giveccegg <player> <amount>"));
				return;
			}
			
			final int amount = Integer.valueOf(args[1]);
			
			final ItemBuilder item = new ItemBuilder(Material.MONSTER_EGG, 50);
			item.setName("&aCharged Creeper Egg");
			item.setAmount(amount);
			
			target.getInventory().addItem(item.getStack());
			sender.sendMessage(Language.CHARGED_CEGG_GIVE.getMessage().replace("%amount%", String.valueOf(amount)).replace("%player%", target.getName()));
			return;
			
		}
	
		sender.sendMessage(Language.USAGE.getMessage().replace("%command%", "giveccegg <player> <amount>"));
		return;
		
	}
	
}
