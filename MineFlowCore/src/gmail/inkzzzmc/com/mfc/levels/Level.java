package gmail.inkzzzmc.com.mfc.levels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

public class Level {
	
	public static double global_discount = 0;
	public static long discount_timestamp = 0;
	
	public static void setGlobalDiscount(double global_discount, long discount_timestamp) {
		Level.global_discount = global_discount;
		Level.discount_timestamp = discount_timestamp;
	}
	
	public static boolean isGlobalDiscount() {
		return global_discount != 0;
	}
	
	/**
	 * HashMap that stores all the possible levels.
	 */
	private static final Map<Integer, Level> levels = new HashMap<Integer, Level>();
	
	private final double multiplyer = 1.05;
	private final int level;
	private double cost;
	private final List<String> permissions;
	
	public Level(int level, double cost, List<String> permissions) {
		this.level = level;
		this.cost = cost;
		this.permissions = permissions;	
	}
	
	/**
	 * @return the level value.
	 */
	public int getLevel(){ 
		return this.level;
	}
	
	/**
	 * @return the cost to rank up to the level.
	 */
	public double getCost() {
		return this.cost;
	}
	
	/**
	 * Sets the cost of the level to @param cost.
	 * @param cost - Integer value.
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	/**
	 * @return a list of permissions available for the level.
	 */
	public List<String> getPermissions() {
		return this.permissions;
	}
	
	/**
	 * @return Checks if the rank is the first rank.
	 */
	public boolean isFirstRank(){ 
		return getLevel() == 0;
	}
	
	/**
	 * @return Checks if the rank is the final level.
	 */
	public boolean isLastLevel(){ 
		return getLevel() == LevelManager.getInstance().getHighestLevel();
	}
	
	/**
	 * @return the multiplier of the level.
	 */
	public final double getMultiplyer() {
		return this.multiplyer;
	}
	
	/**
	 * Automatically sets the cost of the level.
	 */
	public void setCost() {
		
		if(getLevel() == 0) return;
		
		double cost = LevelManager.getInstance().getLevelBefore(getLevel()).getCost() * 1.05; 
		setCost(cost);
		
	}
	
	/**
	 * @return a list of all the registered levels.
	 */
	public static List<Level> getLevels(){ 
		return ImmutableList.copyOf(levels.values());
	}
	
	/**
	 * @param id - Level id.
	 * @return The level data of @param id.
	 */
	public static Level getLevel(int id) {
		return levels.get(id);
	}
	
	/**
	 * @param level - Level to register.
	 * @return Registers @param level.
	 */
	public static Level registerLevel(Level level) {
		return levels.put(level.getLevel(), level);
	}
	
	/**
	 * @param level - Level to deregister.
	 * @return Deregisters @param level.
	 */
	public static Level removeLevel(Level level) {
		return levels.remove(level.getLevel());
	}
	
	/**
	 * @param id - Level to check.
	 * @return Checks if the level id exists.
	 */
	public static boolean isLevel(int id) {
		return levels.containsKey(id);
	}
	
}
