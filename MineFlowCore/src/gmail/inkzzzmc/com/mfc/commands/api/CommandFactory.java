package gmail.inkzzzmc.com.mfc.commands.api;

import org.bukkit.command.CommandSender;

public abstract class CommandFactory {
	
	private final String name;
	private String[] args;
	private String permission;
	private final boolean console;
	
	public CommandFactory(String name, String[] args, String permission, boolean console) {
		this.name = name;
		this.permission = permission;
		this.args = args;
		this.console = console;
	}
	
	public CommandFactory(String name, String permission, boolean console) {
		this.name = name;
		this.permission = permission;
		this.console = console;
		
		this.args = new String[]{""};
		
	}
	
	public CommandFactory(String name, boolean console) {
		this.name = name;
		this.console = console;
		
		this.permission = "";
		this.args = new String[]{""};
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the args
	 */
	public String[] getArgs() {
		return args;
	}

	/**
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}
	
	/**
	 * @return Checks if the command can be used via the console.
	 */
	public boolean AllowsConsole() {
		return this.console;
	}
	
	/**
	 * Method executed once command is executed.
	 * @param sender - Host of command sent.
	 * @param args - Arguements used in the command.
	 */
	public abstract void execute(CommandSender sender, String[] args);

}
