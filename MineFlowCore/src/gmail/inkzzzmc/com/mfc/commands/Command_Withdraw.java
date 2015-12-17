package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.Main;
import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.utils.ItemBuilder;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Command_Withdraw extends CommandFactory {

	public Command_Withdraw() {
		super("withdraw", false);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		Player player = (Player) sender;
		
		if(args.length == 1) {
			
			if(Utils.isNumber(args[0])) {
				
				int amount = Integer.valueOf(args[0]);
				
				if(Main.economy.getBalance(player) >= amount) {
					
					ItemStack paper = new ItemBuilder(Material.PAPER, 0).setName("&b&lMineflow Note &8(&7Right Click&8)").setLore("&bValue&8: &7$" + args[0], "&bSigner&8: &7" + player.getName()).getStack();
					player.getInventory().addItem(paper);
					Main.economy.withdrawPlayer(player, amount);
					return;
					
				}
				else
				{
					
					player.sendMessage(Language.NOT_ENOUGH_MONEY.getMessage());
					return;
					
				}
				
			} else {
				player.sendMessage(Language.USAGE.getMessage().replace("%command%", "withdraw <amount>"));
			}
			
		} else {
			player.sendMessage(Language.USAGE.getMessage().replace("%command%", "withdraw <amount>"));
		}
		
	}

}
