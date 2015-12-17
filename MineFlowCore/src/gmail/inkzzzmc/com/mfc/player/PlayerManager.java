package gmail.inkzzzmc.com.mfc.player;

import gmail.inkzzzmc.com.mfc.Main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.ImmutableList;

public class PlayerManager {
	
	/**
	 * HashMap that stores the username and mineflow player object for each user on the proxy.
	 */
	private static final Map<String, MineFlowPlayer> players = new HashMap<String, MineFlowPlayer>();

	/**
	 * @param player - Mineflow object to be registered.
	 * @return Registers a mineflow object.
	 */
	public static MineFlowPlayer addPlayer(MineFlowPlayer player) {
		return players.put(player.getName(), player);
	}
	
	/**
	 * @param player - Mineflow object to be deregistered.
	 * @return Deregisters a mineflow object.
	 */
	public static MineFlowPlayer removePlayer(MineFlowPlayer player) {
		return players.remove(player.getName());
	}
	
	/**
	 * @param player - Player object to check for MineFlowPlayer object.
	 * @return If the "param player object is a mineflow player object.
	 */
	public static boolean isMineFlowPlayer(Player player) {
		return players.containsKey(player.getName());
	}
	
	/**
	 * @param player - Player object.
	 * @return Mineflow player object from the @param player.
	 */
	public static MineFlowPlayer getPlayer(Player player) {
		return players.get(player.getName());
	}
	
	/**
	 * @param input - String variable.
	 * @return Mineflow player object from @param input.
	 */
	public static MineFlowPlayer getPlayer(String input) {
		return players.get(input);
	}
	
	/**
	 * @param uuid - UUID variable.
	 * @return Mineflow player object from @param uuid.
	 */
	public static MineFlowPlayer getPlayer(UUID uuid) {
		return players.get(Bukkit.getPlayer(uuid));
	}
	
	/**
	 * @param sender - CommandSender variable.
	 * @return Mineflow player object from @param sender.
	 */
	public static MineFlowPlayer getPlayer(CommandSender sender) {
		return (sender instanceof Player ? getPlayer((Player) sender) : null);
	}
	
	/*
	 * Returns a list of all mineflow player objects.
	 */
	public static List<MineFlowPlayer> getPlayers() {
		return ImmutableList.copyOf(players.values());
	}
	
	/**
	 * @param fplayer - Mineflow player object.
	 * @return Sets the XP multiplier of the @param fplayer.
	 */
	public static double setXPLevels(MineFlowPlayer fplayer) {
		
		Player player = fplayer.getPlayer();
		
		String rank = null;
		double xp = 0;
		
		for(String ranks : Main.ranks) {
			if(isRank(player, ranks)) {
				rank = ranks;
				break;
			}
		}
		
		if(rank == null) {
			return 1.40;
		}
		
		switch(rank.toLowerCase()) {
		
		case "epic":
			xp = 1.45;
			break;
		
		case "mythic":
			xp = 1.50;
			break;
		
		case "omega":
			xp = 1.55;
			break;
			
		case "titan":
			xp = 1.60;
			break;
		
		case "legend":
			xp = 1.65;
			break;
			
		default:
			break;
			
		}
		
		return xp;
		
	}
	
	/**
	 * @param player - Player object.
	 * @param rank - Rank name.
	 * @return Checks if a player object is a certain rank.
	 */
	public static boolean isRank(Player player, String rank) {
		return isGroups() && Main.permission.getPrimaryGroup(player).equalsIgnoreCase(rank);
	}
	
	/**
	 * @return Checks if any donation ranks exist.
	 */
	private static boolean isGroups() {
		for(String groups : Main.permission.getGroups()) {
			for(String ranks : Main.ranks) {
				if(groups.equalsIgnoreCase(ranks)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isDonator(Player player) {
		for(String ranks : Main.ranks) {
			for(String group : Main.permission.getPlayerGroups(player)) {
				if(ranks.equalsIgnoreCase(group)) {
					return true;
				}
			}
		}
		return false;
	}
	
}
