package gmail.inkzzzmc.com.mfc.player;

import gmail.inkzzzmc.com.mfc.Main;
import gmail.inkzzzmc.com.mfc.levels.Level;
import gmail.inkzzzmc.com.mfc.utils.FileUtil;

import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class MineFlowPlayer {
	
	private final FileConfiguration file = FileUtil.getFile("players.yml");
	
	private final UUID uuid;
	private Player player;
	private Level level;
	private int combat;
	private double xp;
	private double money;
	private long wildTimestamp;
	private boolean jellylegs;
	private long fix_timestamp;
	private long lightning_timestamp;
	
	private int credits;
	
	private double discount;
	private long discount_timestamp;
	
	private int gapples;
	
	public MineFlowPlayer(Player player) {
		this.player = player;
		this.uuid = player.getUniqueId();
		this.combat = 0;
		this.xp = PlayerManager.setXPLevels(this);
		this.money = Main.economy.getBalance(player);
		this.wildTimestamp = 0;
		this.jellylegs = (player.hasPermission("mineflow.jellylegs") ? true : false);	
		this.fix_timestamp = 0;
		this.lightning_timestamp = 0;
		this.gapples = 0;
		
		if(file.isConfigurationSection(player.getUniqueId().toString())) {
			
			final String path = player.getUniqueId() + ".";
			
			this.level = Level.getLevel(file.getInt(path + "Level"));
			this.discount_timestamp = file.getLong(path + "Discount-Timestamp");
			this.discount = file.getDouble(path + "Discount");
			this.credits = file.getInt(path + "Credits");
			
		} else {
		
			this.level = Level.getLevel(0);
			this.discount_timestamp = 0;
			this.discount = 0;
			this.credits = 0;
			
		}
		
	}
	
	/**
	 * @return the unique id of the player object.
	 */
	public final UUID getUUID() {
		return this.uuid;
	}
	
	/**
	 * @return the player object from the unique id.
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * Sets the users player object to @param player.
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * @return the player objects username.
	 */
	public String getName() {
		return getPlayer().getName();
	}

	/**
	 * @return the time left in combat.
	 */
	public int getCombat() {
		return this.combat;
	}
	
	/**
	 * @return the level of the user.
	 */
	public Level getLevel() {
		return this.level;
	}
	
	/**
	 * Sets the user to @param level.
	 * @param level - Level to set the user to.
	 */
	public void setLevel(Level level) {
		this.level = level;
	}
	
	/**
	 * Sets the time left in combat of the user.
	 * @param combat - Sets the combat.
	 */
	public void setCombat(int combat) {
		this.combat = combat;
	}
	
	/**
	 * @return Checks if the player is in combat.
	 */
	public boolean isInCombat() {
		return combat > 0;
	}
	
	/**
	 * @return the xp boost of the player.
	 */
	public double getXPBoost() {
		return this.xp;
	}
	
	/**
	 * @return the amouny of money the user has.
	 */
	public double getMoney() {
		return this.money;
	}
	
	/**
	 * Sets the users money to @param money.
	 * @param money - Amount of money to set to the user.
	 */
	public void setMoney(double money) {
		this.money = money;
	}
	
	/**
	 * @param amount - The amount of money the player has.
	 * @return Checks if the players money has altered.
	 */
	public boolean moneyHasChanged(double amount) {
		return getMoney() < amount || getMoney() > amount;
	}
	
	/**
	 * @return the time stamp that the user used the wild function.
	 */
	public long getWildTimeStamp() {
		return this.wildTimestamp;
	}
	
	/**
	 * Sets the users wild time stamp.
	 * @param wildTimestamp - Current time stamp plus cooldown in milliseconds.
	 */
	public void setWildTimeStamp(long wildTimestamp) {
		this.wildTimestamp = wildTimestamp;
	}
	
	/**
	 * @return Checks if the user can use the wild function.
	 */
	public boolean canWild() {
		return System.currentTimeMillis() > getWildTimeStamp();
	}
	
	/**
	 * @return Checks if the player has jelly legs.
	 */
	public boolean hasJellyLegs() {
		return this.jellylegs;
	}
	
	/**
	 * Toggles jelly legs for the user.
	 * @param jellylegs - Value to set jelly legs to.
	 */
	public void setJellyLegs(boolean jellylegs) {
		this.jellylegs = jellylegs;
	}
	
	/**
	 * @return the timestamp when the player used fix command plus the 30 minute cooldown.
	 */
	public long getFixTimestamp() {
		return this.fix_timestamp;
	}
	
	
	/**
	 * Sets the users fix time stamp.
	 * @param fix_timestamp - Timestamp to set.
	 */
	public void setFixTimestamp(long fix_timestamp) {
		this.fix_timestamp = fix_timestamp;
	}
	
	/**
	 * @return Checks if the user can use the fix function.
	 */
	public boolean canFix() {
		return System.currentTimeMillis() > getFixTimestamp();
	}
	
	/**
	 * @return the time stamp that the user used the lightning function.
	 */
	public long getLightningTimestamp() {
		return this.lightning_timestamp;
	}
	
	/**
	 * Sets the users lightning timestamp.
	 * @param lightning_timestamp - Timestamp to set.
	 */
	public void setLightningTimestamp(long lightning_timestamp) {
		this.lightning_timestamp = lightning_timestamp;
	}
	
	/**
	 * @return Checks if the user can use the lightning function.
	 */
	public boolean canUseLightning() {
		return System.currentTimeMillis() > getLightningTimestamp();
	}
	
	/**
	 * @return the discount amount.
	 */
	public double getLevelDiscount() {
		return this.discount;
	}
	
	/**
	 * Sets the discount value to @param discount.
	 * @param discount - Amount of discount.
	 */
	public void setLevelDiscount(double discount) {
		this.discount = discount;
	}
	
	/**
	 * @return the time stamp of the discount timer.
	 */
	public long getLevelDiscountTimestamp() {
		return this.discount_timestamp;
	}
	
	/**
	 * Sets the discount time stamp.
	 */
	public void setLevelDiscountTimeStamp(long discount_timestamp) {
		this.discount_timestamp = discount_timestamp;
	}
	
	/**
	 * @return Checks if the user has any discount.
	 */
	public boolean hasDiscount() {
		return discount > 0 && discount < 1;
	}
	
	/**
	 * @return the amount of gapples consumed.
	 */
	public int getGapplesConsumed() {
		return this.gapples;
	}
	
	/**
	 * Sets the amount of consumed gapples to @param gapples.
	 */
	public void setGapplesConsumed(int gapples) {
		this.gapples = gapples;
	}
	
	/**
	 * @return the amount of credits the user has.
	 */
	public int getCredits() {
		return this.credits;
	}
	
	/**
	 * Sets the users credits to @param credits.
	 */
	public void setCredits(final int credits) {
		this.credits = credits;
	}
	
	/**
	 * Saves the users data.
	 */
	public final synchronized void saveData(final boolean save) {
		
		file.set(getUUID() + ".Level", getLevel().getLevel());
		file.set(getUUID() + ".Discount-Timestamp", getLevelDiscountTimestamp());
		file.set(getUUID() + ".Discount", getLevelDiscount());
		file.set(getUUID() + ".Credits", getCredits());
		
		if(save) {
			FileUtil.saveFile(file, "players.yml");
		}
		
	}
	
}
