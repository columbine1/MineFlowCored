package net.inkzzz.mineflow.customenchantments.commands;

import net.inkzzz.mineflow.customenchantments.managers.ItemManager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ProtectionRune_Command implements CommandExecutor {

	private final ItemManager ITEM_MANAGER;
	
	public ProtectionRune_Command() {
		this.ITEM_MANAGER = new ItemManager();
	}
	
	public boolean onCommand(CommandSender s, Command c, String string, String[] args) {
		
		if( args.length == 2 ) {
			
			if( args[0].equalsIgnoreCase("give") ) {
				
				final Player target = Bukkit.getPlayer(args[1]);
				
				if( target == null ) {
					return false;
				}
				
				target.getInventory().addItem(this.ITEM_MANAGER.getProtectionRune());
				return true;
				 
			}
			
		}
		return false;
	}
	
}
