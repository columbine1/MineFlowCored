package gmail.inkzzzmc.com.mfc;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.commands.api.CommandManager;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.levels.Level;
import gmail.inkzzzmc.com.mfc.levels.LevelManager;
import gmail.inkzzzmc.com.mfc.listeners.ConsumeListener;
import gmail.inkzzzmc.com.mfc.listeners.CraftItem;
import gmail.inkzzzmc.com.mfc.listeners.LevelChange;
import gmail.inkzzzmc.com.mfc.listeners.PlayerDeath;
import gmail.inkzzzmc.com.mfc.listeners.PlayerInteract;
import gmail.inkzzzmc.com.mfc.listeners.PlayerListener;
import gmail.inkzzzmc.com.mfc.listeners.ProjectileLaunch;
import gmail.inkzzzmc.com.mfc.listeners.SkillLevelChange;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;
import gmail.inkzzzmc.com.mfc.threads.DiscountTimer;
import gmail.inkzzzmc.com.mfc.threads.PlayerTimer;
import gmail.inkzzzmc.com.mfc.utils.FileUtil;
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
	
	public static Economy economy = null;
	public static Permission permission = null;
	
	@Override
	public void onEnable() {
		
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
		FileUtil.getFile("messages.yml");
		FileUtil.getFile("players.yml");
		FileUtil.getFile("levels.yml");
		
		LevelManager.getInstance().loadLevels();
		
		loadListeners(new PlayerListener(), new PlayerInteract(), new PlayerDeath(this), new SkillLevelChange(), new CraftItem(), new ProjectileLaunch(this), new LevelChange(), new ConsumeListener(this));
		new PlayerTimer().runTaskTimer(this, 20L, 20L);
		new DiscountTimer().runTaskTimer(this, 20L, 20L);
		new CommandManager(this);
		
		getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				
				setupEconomy();
				setupPermissions();
				
				if(Bukkit.getOnlinePlayers().size() > 0) {
					for(Player player : Bukkit.getOnlinePlayers()) {
						MineFlowPlayer fplayer = new MineFlowPlayer(player);
						PlayerManager.addPlayer(fplayer);
					}
				}
				
				
				
			}
		}, 50L);
		
//		hook();
		Language.loadMessages();
		
		Level.setGlobalDiscount(getConfig().getDouble("Global-Discount"), getConfig().getLong("Global-Discount-TimeStamp"));
		
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
		
//		PlaceholderHandler.unregisterPlaceholderHook(this);
		
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
	 
//	 private void hook() {
//		 
//			if(getServer().getPluginManager().isPluginEnabled("DeluxeChat")) {
//				
//				boolean hooked = PlaceholderHandler.registerPlaceholderHook(this,
//						new DeluxePlaceholderHook() {
//					@Override
//					public String onPlaceholderRequest(Player player, String arg) {
//						if(arg.equalsIgnoreCase("mineflowcore_level")) {
//							return String.valueOf(PlayerManager.getPlayer(player).getLevel().getLevel());
//						}
//						return null;
//					}
//				});
//				
//				if(hooked) {
//					getLogger().info("Player levels successfully hooked into deluxe chat.");
//				} else {
//					getLogger().warning("Player levels coudln't hook into DeluxeChat!");
//				}
//					
//			}
//		 
//	 }
	
}
