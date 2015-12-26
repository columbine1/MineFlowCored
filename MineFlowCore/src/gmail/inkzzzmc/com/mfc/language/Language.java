package gmail.inkzzzmc.com.mfc.language;

import gmail.inkzzzmc.com.mfc.utils.FileUtil;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import org.bukkit.configuration.file.FileConfiguration;

public enum Language {
	
	/**
	 * All the messages sent to players are located within this enum class.
	 */
	
	PREFIX("PREFIX", "&e(&e&l*&e)"),
	TAG_BAR_MESSAGE("TAG_BAR_MESSAGE", "%prefix% &eCombat Tag: &7&o%time%s"),
	NOT_IN_COMBAT("NOT_IN_COMBAT", "%prefix% &eYou're not in combat."),
	IN_COMBAT("IN_COMBAT", "%prefix% &eYou can logout in &7&o%time% &eseconds!"),
	OUT_OF_COMBAT("OUT_OF_COMBAT", "%prefix% &eYou're now out of combat."),
	ATTACKER_TAG("ATTACKER_TAG", "%prefix% &eYou have combat tagged &7&o%player%&e!"),
	PLAYER_TAG("PLAYER_TAG", "%prefix% &eYou have been combat tagged by &7&o%player%&e!"),
	NOTE_REDEEM("NOTE_REDEEM", "%prefix% &eYou have redeemed a &7&omineflow note &ewith the value of &7&o$%amount%&e!"),
	NOT_ENOUGH_MONEY("NOT_ENOUGH_MONEY", "%prefix% &eYou don't have enough money!"),
	NEED_SILKTOUCH("NEED_SILKTOUCH", "%prefix% &eYou must use a silk touch pickaxe to mine this spawner!"),
	NOT_ENOUGH_XP("NOT_ENOUGH_XP","%prefix% &eYou don't have enough EXP levels!"),
	EXP_REDEEM("EXP_REDEEM", "%prefix% &eYou have redeemed a &7&oEXP voucher &ewith the value of &7&o%amount%&e!"),
	USAGE("USAGE", "%prefix% &e/%command%"),
	WILD_COOLDOWN("WILD_COOLDOWN", "%prefix% &eYou must wait &7&o%time%&e!"),
	WILD("WILD", "%prefix% &eYou have been randomly teleported!"),
	NOT_ENOUGH_MCMMO("NOT_ENOUGH_MCMMO", "%prefix% &eYou don't have enough mcmmo."),
	MCMMO_REDEEM("MCMMO_REDEEM", "%prefix% &eYou have redeemed &7&o%value% XP &efor the skill &7&o%skill%&e."),
	LEVELUP("LEVELUP", "%prefix% &eYou have leveled up to &7&o%level%&e."),
	LAST_LEVEL("LAST_LEVEL", "%prefix% &eYou're already the final level!"),
	ENDERPEARL_COOLDOWN("ENDERPEARL_COOLDOWN", "%prefix% &eYou can use enderpearls in &7%time%&e."),
	ENDERPEARL_COOLDOWN_RECEIVE("ENDERPEARL_COOLDOWN_RECEIVE", "%prefix% &eYou have received an enderpearl cooldown of &715 seconds&7!"),
	FEED_COOLDOWN("FEED_COOLDOWN", "%prefix% &eYou can use the feed function in &7%time%&e."),
	FEED("FEED", "%prefix% &eYou have been fed."),
	JELLYLEGS("JELLYLEGS", "%prefix% &eYou have &7%toggled% &ejelly legs!"),
	REPAIR_COOLDOWN("REPAIR_COOLDOWN", "%prefix% &eYou can use the fix function in &7%time%&e!"),
	REPAIR_FAIL("REPAIR_FAIL", "%prefix% &eYou cannot repair this item!"), 
	REPAIR("REPAIR", "%prefix% &eYour item has been repaired! You have received a &730 minute &ecooldown!"),
	LIGHTNING_COOLDOWN("LIGHTNING_COOLDOWN", "%prefix% &eYou can use the lightning function in &7%time%&e."),
	LIGHTNING("LIGHTNING", "%prefix% &eYou have cursed the gods!"),
	LEVELS_DISCOUNT_ALL("LEVELS_DISCOUNT_ALL", "%prefix% &eA global level-up discount of &7%amount% &ehas been enabled for &7%time%&e."),
	LEVELS_DISCOUNT("LEVELS_DISCOUNT", "%prefix% &eYou have received a level-up discount of &7%amount% &efor &7%time%&e."),
	PLAYER_OFFLINE("PLAYER_OFFLINE", "%prefix% &7%player% &eis offline!"),
	DISCOUNT("DISCOUNT", "&6&l&m========================================\n \n&8> &e&lGlobal Discount&7: %global%\n \n&8> &e&lPersonal Discount&7: %amount%\n \n&6&l&m========================================"),
	NO_PERM("NO_PERM", "%prefix% &eYou don't have permission."),
	GAPPLE_WARNING("GAPPLE_WARNING", "%prefix% &7If you eat another golden apple you'll receive an illness."),
	GAPPLE_ILLNESS("GAPPLE_ILLNESS", "%prefix% &7You have consumed too many golden apples and became ill."),
	NO_TP_COMBAT("NO_TP_COMBAT", "%prefix% &cYou cannot teleport in combat!"),
	NO_FLY_COMBAT("NO_FLY_COMBAT", "%prefix% &cYour flying ability has been disabled as you're in combat."),
	ADD_CREDITS("ADD_CREDITS", "%prefix% &eYou have added &7%amount% &ecredits to &7%player%'s &ecredit balance!"),
	REMOVE_CREDITS("REMOVE_CREDITS", "%prefix% &eYou have removed &7%amount% &ecredits from &7%player%'s &ecredit bank!"),
	NOT_ENOUGH_CREDITS("NOT_ENOUGH_CREDITS", "%prefix% &7%player% &edoesn't have enough credits!"),
	CREDITS("CREDITS", "%prefix% &eYou have &7%amount% &ecredits!"),
	CREDITS_REDEEM("CREDITS_REDEEM", "%prefix% &eYou have redeemed &7%amount% &ecredits to &7%skill% &eskill!"),
	PLAYER_NOT_ENOUGH_CREDITS("PLAYER_NOT_ENOUGH_CREDITS", "%prefix% &eYou don't have enough credits."),
	CHARGED_CEGG_GIVE("CHARGED_CEGG_GIVE", "%prefix% &eYou have given &7%amount% &echarged creeper eggs to &7%player%&e."),
	SPAWNER_GIVE("SPAWNER_GIVE", "%prefix% &7You have given &e%player% &7a &e%type% &7spawner!"),
	SPAWNER_DENY("SPAWNER_DENY", "%prefix% &cYou must be a donator to mine spawners!"),
	LEVEL_COOLDOWN("LEVEL COOLDOWN", "%prefix% &eYou can level-up again in &7%time% &eseconds!"),
	STRENGTH_DISABLE("STRENGTH_DISABLE", "%prefix% &eStrength II potions are currently disabled."),
	FIRST_JOIN_MESSAGE("FIRST_JOIN_MESSAGE", "%prefix% &eYou have set your first join message to:\n%message%"),
	FIRST_JOIN_MESSAGE_OFF("FIRST_JOIN_MESSAGE_OFF", "%prefix% &eYou have toggled off your first whalecum message."),
	FIRST_JOIN_MESSAGE_ON("FIRST_JOIN_MESSAGE_ON", "%prefix% &eUse /setfjmessage <message>, to toggle on your welcome message.");
	
	private final String path;
	private String message;
	
	Language(String path, String message) {
		this.path = path;
		this.message = message;
	}
	
	/**
	 * @return the path of the enum.
	 */
	public String getPath() {
		return this.path;
	}
	
	/**
	 * @return the decolorised version of the enum.
	 */
	public String getRawMessage() {
		return this.message;
	}
	
	/**
	 * @return the colorised version of the enum along with the prefix.
	 */
	public String getMessage() {
		return Utils.color(getRawMessage().replace("%prefix%", Language.PREFIX.getRawMessage()));
	}
	
	/**
	 * @param message - Message to set (String variable)
	 * Sets the enum message.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Loads all the messages from the messages.yml file.
	 */
	public static void loadMessages() {
		
		final FileConfiguration file = FileUtil.getFile("messages.yml");
		
		for(Language l : values()) {
			
			if(file.getString(l.getPath()) == null) {
				
				file.set(l.getPath(), l.getRawMessage());
			
			} else {
				
				l.setMessage(file.getString(l.getPath()));
				
			}
			
		}
		
		FileUtil.saveFile(file, "messages.yml");
		
	}

}
