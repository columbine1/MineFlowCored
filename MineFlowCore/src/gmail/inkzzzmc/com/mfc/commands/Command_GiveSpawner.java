package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.utils.ItemBuilder;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Command_GiveSpawner extends CommandFactory {

	public Command_GiveSpawner() {
		super("givespawner", "mineflow.admin", true);
	}

	/**
	 * givespawner <player> <type> <amount>
	 */
	
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {

		if(args.length == 3) {
			
			final Player target = Bukkit.getPlayer(args[0]);
			final EntityType entity = EntityType.fromName(args[1]);
			
			if(target == null || entity == null || !Utils.isNumber(args[2])) {
				sender.sendMessage(Language.USAGE.getMessage().replace("%command%", "givespawner <player> <type> <amount>"));
				return;
			}
			
			final int amount = Integer.valueOf(args[2]);
			final ItemStack spawner = new ItemBuilder(Material.MOB_SPAWNER, entity.getTypeId()).setAmount(amount).getStack();
			
			target.getInventory().addItem(spawner);
			sender.sendMessage(Language.SPAWNER_GIVE.getMessage().replace("%player%", target.getName()).replace("%type", entity.getName()));
			return;
			
		}
		
		sender.sendMessage(Language.USAGE.getMessage().replace("%command%", "givespawner <player> <type> <amount>"));
		return;
		
	}
}
