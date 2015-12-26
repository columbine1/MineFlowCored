package gmail.inkzzzmc.com.mfc.commands.api;

import gmail.inkzzzmc.com.mfc.Main;
import gmail.inkzzzmc.com.mfc.commands.*;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager {
	
	/**
	 * HashSet of commands.
	 */
	private static Set<CommandFactory> commands;
	
	/**
	 * 
	 * Creates commands HashSet.
	 * Registers commands along with their variables (name, arguments, permission, allowconsole)
	 * 
	 */
	public CommandManager(final Main plugin) {
		if(commands == null) {
			commands = new HashSet<>();
		}
		commands.add(new Command_Logout());
		commands.add(new Command_Withdraw(plugin));
		commands.add(new Command_WithdrawEXP());
		commands.add(new Command_Wild());
		commands.add(new Command_WithdrawMcMMO());
		commands.add(new Command_Feed(plugin));
		commands.add(new Command_JellyLegs());
		commands.add(new Command_Fix());
		commands.add(new Command_Lightning());
		commands.add(new Command_LevelUP(plugin));
		commands.add(new Command_Levels());
		commands.add(new Command_Discount());
		commands.add(new Command_BoosterGive());
		commands.add(new Command_GiveSpawner());
		commands.add(new Command_Giveccegg());
		commands.add(new Command_AddCredits());
		commands.add(new Command_RemoveCredits());
		commands.add(new Command_Credits());
		commands.add(new Command_Redeem());
		commands.add(new Command_FirstJoinMessage());
	}
	
	/**
	 * @param name - Name of command.
	 * @return the command factory class dependant on the @param name.
	 */
	public static CommandFactory getCommand(String name) {
		for(CommandFactory cmd : getCommands()) {
			if(cmd.getName().equalsIgnoreCase(name)) {
				return cmd;
			}
		}
		return null;
	}

	/**
	 * @return the hashset of commands (List).
	 */
	public static Set<CommandFactory> getCommands() {
		return commands;
	}
	
}
