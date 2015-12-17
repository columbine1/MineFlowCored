package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.utils.ItemBuilder;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Command_WithdrawEXP extends CommandFactory {

	public Command_WithdrawEXP() {
		super("withdrawexp", false);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		Player player = (Player) sender;
		
		if(args.length == 1) {
			if(Utils.isNumber(args[0])) {
				
				float amount = Float.valueOf(args[0]);
				
				if(player.getExp() >= amount || amount > 0) {
					
					ItemStack paper = new ItemBuilder(Material.PAPER, 0).setName("&b&lEXP Voucher &8(&7Right Click&8)").setLore("&bValue&8: &7" + amount, "&bSigner&8: &7" + player.getName()).getStack();
					player.getInventory().addItem(paper);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "exp set " + player.getName() + " " + Integer.valueOf((int) (player.getExpToLevel() - amount) * 10));

					return;
					
				} else {
					
					player.sendMessage(Language.NOT_ENOUGH_XP.getMessage());
					return;
					
				}
				
			} else {
				player.sendMessage(Language.USAGE.getMessage().replace("%command%", "withdrawexp <amount>"));
			}
		} else {
			player.sendMessage(Language.USAGE.getMessage().replace("%command%", "withdrawexp <amount>"));
		}
		
	}

}
