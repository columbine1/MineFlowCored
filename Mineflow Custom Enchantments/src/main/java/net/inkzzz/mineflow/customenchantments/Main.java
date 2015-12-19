package net.inkzzz.mineflow.customenchantments;

import net.inkzzz.mineflow.customenchantments.listeners.InventoryClickListener;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		
		registerListeners(new InventoryClickListener());
		
	}
	
	/*
	 * Registers the array of listeners.
	 */
	private void registerListeners(Listener... listeners) {
		for(Listener listener : listeners) {
			getServer().getPluginManager().registerEvents(listener, this);
		}
	}
	
}
