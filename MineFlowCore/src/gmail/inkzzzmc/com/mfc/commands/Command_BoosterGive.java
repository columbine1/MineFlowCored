package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.utils.ItemBuilder;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Command_BoosterGive extends CommandFactory {

	public Command_BoosterGive() {
		super("boostergive", true);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(sender instanceof Player) return;
		
		if(args.length == 4) {
			
			Player target = Bukkit.getPlayer(args[0]);
			
			if(target == null) {
				return;
			}
			
			if(!Utils.isDouble(args[2]) || !Utils.isNumber(args[3])) {
				return;
			}
			
			double percent = Double.valueOf(args[2]);
			int minutes = Integer.valueOf(args[3]);
			int pd = (int) (percent * 10) * 10;
			String type = "";
			
			switch(args[1].toLowerCase()) {
			
			case "personal":
				type = "Personal";
				break;
				
			case "global":
				type = "Global";
				break;
				
			default:
				break;
			
			}
			
			if(type.equals("")) {
				return;
			}
			
			final ItemBuilder item = new ItemBuilder(Material.EMERALD, 0);
			item.setName("&a" + type + " Booster");
			item.setLore(
					"&8> &aLength: &7" + minutes + "m",
					"&8> &aPercent: &7" + pd,
					"&8> &7&oClick to active booster!"
					);
			
			final ItemStack stack = item.getStack();
			target.getInventory().addItem(stack);
			return;
			
		}
	}
	
	public static void useBooster(final Player player, final ItemStack itemstack) {
		
		if(itemstack == null || itemstack.getType() != Material.EMERALD) {
			return;
		}
		
		if(!itemstack.hasItemMeta()) {
			return;
		}
		
		final ItemMeta meta = itemstack.getItemMeta();
		String name = ChatColor.stripColor(meta.getDisplayName());
		
		if(!name.contains("Booster")) {
			return;
		}
		
		List<String> lore = meta.getLore();
		
		String length = lore.get(0);
		length = ChatColor.stripColor(length);
		length = length.split(":")[1].trim().replace("m", "");
		int minutes = Integer.valueOf(length);
		
		String percent = lore.get(1);
		percent = ChatColor.stripColor(percent);
		percent = percent.split(":")[1].trim();
		double percentage = Double.valueOf(Integer.valueOf(percent) / 10) / 10;
		
		String type = meta.getDisplayName();
		type = ChatColor.stripColor(type);
		type = type.split(" ")[0].trim();
		
		if(type.equalsIgnoreCase("personal")) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "levels discount " + percentage + " " + player.getName() + " " + minutes);
		}
		else if(type.equalsIgnoreCase("global")) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "levels discount " + percentage + " all " + minutes);
		}
		
		player.getInventory().remove(itemstack);
		player.updateInventory();
		
	}
	
}
