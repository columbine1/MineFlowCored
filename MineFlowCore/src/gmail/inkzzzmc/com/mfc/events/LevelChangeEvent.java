package gmail.inkzzzmc.com.mfc.events;

import gmail.inkzzzmc.com.mfc.levels.Level;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * 
 * @author Luke Denham
 *
 * LevelChangeEvent is called when a players level changes.
 * It has the following variables:
 * 
 * - Mineflow players object - #getPlayer()
 * - Players level - #getLevel()
 *
 */

public class LevelChangeEvent extends Event implements Cancellable {

	private final MineFlowPlayer player;
	private final Level level;
	private boolean cancelled;
	
	/**
	 * This constructor calls the event.
	 * @param player - The mineflow players object that has changed level.
	 * @param level - The level the @param player has changed to.
	 */
	public LevelChangeEvent(MineFlowPlayer player, Level level) {
		this.player = player;
		this.level = level;
		this.cancelled = false;
	}
	
	/**
	 * @return the players mineflow object.
	 */
	public MineFlowPlayer getPlayer() {
		return this.player;
	}
	
	/**
	 * @return the players level.
	 */
	public Level getLevel() {
		return this.level;
	}
	
	/**
	 * Checks if the event is cancelled.
	 */
	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	/**
	 * Cancels or resumes an event.
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
