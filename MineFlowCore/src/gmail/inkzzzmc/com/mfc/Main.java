package gmail.inkzzzmc.com.mfc;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.commands.api.CommandManager;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.levels.Level;
import gmail.inkzzzmc.com.mfc.levels.LevelManager;
import gmail.inkzzzmc.com.mfc.listeners.*;
import gmail.inkzzzmc.com.mfc.nms.ActionBar;
import gmail.inkzzzmc.com.mfc.nms.ActionBar_v1_8_R3;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;
import gmail.inkzzzmc.com.mfc.threads.DiscountTimer;
import gmail.inkzzzmc.com.mfc.threads.PlayerTimer;
import gmail.inkzzzmc.com.mfc.utils.FileUtil;
import me.clip.deluxechat.placeholders.DeluxePlaceholderHook;
import me.clip.deluxechat.placeholders.PlaceholderHandler;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	/**
	 * MineFlow faction ranks.
	 */
	public static final String[] ranks = {"epic", "mythic", "omega", "titan", "legend"};
	
	public Economy economy = null;
	public static Permission permission = null;

	private WorldGuardPlugin wg;
	private ActionBar actionBar;
	
	@Override
	public void onEnable() {
		
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
		FileUtil.getFile("messages.yml");
		FileUtil.getFile("players.yml");
		FileUtil.getFile("levels.yml");
		
		LevelManager.getInstance().loadLevels();
		
		loadListeners(new PlayerListener(this), new PlayerInteract(this), new PlayerDeath(this), new SkillLevelChange(), new CraftItem(), new ProjectileLaunch(this), new LevelChange(), new ConsumeListener(this), new FlyListener());
		new PlayerTimer(this).runTaskTimer(this, 20L, 20L);
		new DiscountTimer().runTaskTimer(this, 20L, 20L);
		new CommandManager(this);

		if(setupEconomy()) {
			getLogger().info("Successfully setup Economy!");
		} else {
			getLogger().info("Unable to setup economy");
		}

		if(setupPermissions()) {
			getLogger().info("Successfully setup Permissions");
		} else {
			getLogger().info("Unable to setup permissions");
		}

		if(Bukkit.getOnlinePlayers().size() > 0) {
			for(Player player : Bukkit.getOnlinePlayers()) {
				MineFlowPlayer fplayer = new MineFlowPlayer(player, this);
				PlayerManager.addPlayer(fplayer);
			}
		}
		
		chatHook();
		Language.loadMessages();
		
		Level.setGlobalDiscount(getConfig().getDouble("Global-Discount"), getConfig().getLong("Global-Discount-TimeStamp"));

		wg = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");

		setupActionBar();

	}
	
	@Override
	public void onDisable() {
		
		final FileConfiguration file = FileUtil.getFile("players.yml");
		
		for(MineFlowPlayer fplayer : PlayerManager.getPlayers()) {
			file.set(fplayer.getUUID() + ".Level", fplayer.getLevel().getLevel());
			file.set(fplayer.getUUID() + ".Discount-Timestamp", fplayer.getLevelDiscountTimestamp());
			file.set(fplayer.getUUID() + ".Discount", fplayer.getLevelDiscount());
			file.set(fplayer.getUUID() + ".Credits", fplayer.getCredits());
		}

		FileUtil.saveFile(file, "players.yml");
		
		getConfig().set("Global-Discount", Level.global_discount);
		getConfig().set("Global-Discount-TimeStamp", Level.discount_timestamp);
		saveConfig();

		PlaceholderHandler.unregisterPlaceholderHook(this);
		
	}
	
	/**
	 * Registers listeners that are in the @param listener array.
	 * @param listener - Listener array of listeners.
	 */
	private void loadListeners(Listener... listener) {
		for(Listener list : listener) {
			getServer().getPluginManager().registerEvents(list, this);
		}
	}
	
	/**
	 * Directs commands.
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		
		CommandFactory command = CommandManager.getCommand(cmd.getName());
		
		if(command == null) return false;
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if(command.getPermission().isEmpty() || player.hasPermission(command.getPermission())) {
				command.execute(sender, args);
			}
			else
			{
				player.sendMessage(Language.NO_PERM.getMessage());
			}
			return true;
		}
		else
		{
			if(command.AllowsConsole()) {
				command.execute(sender, args);
			}
		}
		return true;	
	}
	
	 /**
	 * Sets up vault economy.
	 */
	 private boolean setupEconomy() {
	     RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
	     if (economyProvider != null) {
	        economy = economyProvider.getProvider();
	     }
	    return (economy != null);
	 }
	 
	 /**
	  * Sets up vault permissions.
	  */
	 private boolean setupPermissions() {
	     RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
	     if (permissionProvider != null) {
	        permission = permissionProvider.getProvider();
	    }
	    return (permission != null);
	 }
	 
	 private void chatHook() {
		 
			if(getServer().getPluginManager().isPluginEnabled("DeluxeChat")) {
				
				boolean hooked = PlaceholderHandler.registerPlaceholderHook(this,
						new DeluxePlaceholderHook() {
					@Override
					public String onPlaceholderRequest(Player player, String arg) {
						if(arg.equalsIgnoreCase("level")) {
							return String.valueOf(PlayerManager.getPlayer(player).getLevel().getLevel());
						}
						return null;
					}
				});
				
				if(hooked) {
					getLogger().info("Player levels successfully hooked into deluxe chat.");
				} else {
					getLogger().warning("Player levels coudln't hook into DeluxeChat!");
				}
					
			}
		 
	 }

	private boolean setupActionBar() {
		String version;

		try {
			version = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3];
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}

		if(version.equals("v1_8_R3")) {
			actionBar = new ActionBar_v1_8_R3();
		}

		return actionBar != null;
	}

	public WorldGuardPlugin getWorldGuard() {
		return wg;
	}

	public ActionBar getActionBar() {
		return actionBar;
	}



}
