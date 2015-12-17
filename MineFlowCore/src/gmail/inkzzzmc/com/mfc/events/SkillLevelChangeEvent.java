package gmail.inkzzzmc.com.mfc.events;

import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * 
 * @author Luke Denham
 *
 * SkillLevelChangeEvent is called when a players McMMO levels alter.
 * For instance, when a player redeems a skill card (voucher).
 * 
 * It has the following variables:
 * 
 * - Player object - #getPlayer()
 * - Player's MineFlowPlayer object - #getFlowPlayer() 
 * - Level - #getLevel() 
 * - Skill Type - #getSkill()
 * - Boolean increase - #isIncrease()
 * - Boolean event cancelled = #isCancelled() 
 *
 */
public class SkillLevelChangeEvent extends Event implements Cancellable {

	private boolean cancelled;
	private final Player player;
	private final MineFlowPlayer flowplayer;
	private final int level;
	private final String skill;
	private final boolean increase;
		
	/**
	 * @param player - The player object.
	 * @param level - The level of the players mcmmo skill.
	 * @param skill - The skill type that has been altered.
	 * @param increase - A boolean value on whether the value increased or decreased.
	 */
	public SkillLevelChangeEvent(Player player, int level, String skill, boolean increase) {
		this.player = player;
		this.flowplayer = PlayerManager.getPlayer(player);
		this.level = level;
		this.skill = skill;
		this.increase = increase;
		this.cancelled = false;
	}
	
	/**
	 * @return the player object.
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * @return the players mineflow player object.
	 */
	public MineFlowPlayer getFlowPlayer() {
		return this.flowplayer;
	}
	
	/**
	 * @return the integer of the level.
	 */
	public int getLevel() {
		return this.level;
	}
	
	/**
	 * @return the mcmmo skill name.
	 */
	public String getSkill() {
		return this.skill;
	}
	
	/**
	 * @return a value whether the level was increased or decreased.
	 */
	public boolean isIncrease() {
		return this.increase;
	}
	
	/**
	 * Checks if the event is cancelled.
	 */
	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}
 
	/**
	 * Sets the event cancelled or resumes the event.
	 */
	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	/**
	 * Returns the hander list of the event.
	 */
	@Override
	public HandlerList getHandlers() {
		return HList.handlers;
	}

	/**
	 * Returns the hander list of the event.
	 */
	public static HandlerList getHandlerList() {
		return HList.handlers;
	}
	
}
