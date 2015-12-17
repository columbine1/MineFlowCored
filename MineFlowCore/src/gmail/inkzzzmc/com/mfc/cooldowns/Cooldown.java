package gmail.inkzzzmc.com.mfc.cooldowns;

import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Cooldown extends BukkitRunnable {
	
	private final MineFlowPlayer player;
	private final int cooldown;
	private final CooldownTypes type;
	private int time;
	
	public Cooldown(MineFlowPlayer player, int cooldown, CooldownTypes type, JavaPlugin plugin) {
		this.player = player;
		this.cooldown = cooldown;
		this.time = cooldown;
		this.type = type;
		
		CooldownManager.register(this);
		this.runTaskTimer(plugin, 20L, 20L);
	}
	
	/**
	 * @return the mineflow player object.
	 */
	public final MineFlowPlayer getPlayer() {
		return this.player;
	}
	
	/**
	 * @return the integer of the cooldown.
	 */
	public final int getCooldown() {
		return this.cooldown;
	}
	
	/**
	 * @return the current time left of the cooldown.
	 */
	public int getTime() {
		return this.time;
	}
	
	/**
	 * @return the cooldown type.
	 */
	public final CooldownTypes getType() {
		return this.type;
	}
	
	/**
	 * Task of the cooldown.
	 */
	public void run() {
		
		this.time -= 1;
		
		if(getTime() <= 0) {
			
			if(this.getType() == CooldownTypes.GAPPLE) {
				this.getPlayer().setGapplesConsumed(0);
			}
			
			CooldownManager.deregister(this);
			this.cancel();
			
		}
		
	}
	
	public enum CooldownTypes {
		
		FEED, ENDERPEARL, GAPPLE, LEVELLING;
		
	}
	
	public static class CooldownManager {
		
		/**
		 * List where all the cooldowns are stored.
		 */
		private static final List<Cooldown> cooldowns = new ArrayList<Cooldown>();
		
		/**
		 * @return the list of cooldowns.
		 */
		public static List<Cooldown> getCooldowns() {
			return cooldowns;
		}
		
		/**
		 * @param cooldown - Cooldown data to register.
		 * @return Registers a players cooldown.
		 */
		public static void register(Cooldown cooldown) {
			cooldowns.add(cooldown);
		}
		
		/**
		 * @param cooldown - Cooldown to deregister.
		 * @return Deregisters a players cooldown.
		 */
		public static void deregister(Cooldown cooldown) {
			cooldowns.remove(cooldown);
		}
		
		/**
		 * @param player - Player to check.
		 * @return Checks if a player has a cooldown.
		 */
		public static boolean isCooldown(MineFlowPlayer player, CooldownTypes type) {
			for(Cooldown cd : getCooldowns()) {
				if(cd.getPlayer().getName().equalsIgnoreCase(player.getName())) {
					if(cd.getType().equals(type)) {
						return true;
					}
				}
			}
			return false;
		}
		
		/**
		 * @param player - The player you want to get the cooldown of.
		 * @return The @param players cooldown.
		 */
		public static Cooldown getCooldown(MineFlowPlayer player, CooldownTypes type) {
			if(isCooldown(player, type)) {
				for(Cooldown cooldown : getCooldowns()) {
					if(cooldown.getPlayer().getName().equalsIgnoreCase(player.getName())) {
						if(cooldown.getType().equals(type)) {
							return cooldown;
						}
					}
 				}
			}
			return null;
		} 
		
	}

}
