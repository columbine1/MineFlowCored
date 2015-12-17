package gmail.inkzzzmc.com.mfc.handlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class BlockManager {
	
	public boolean isNearPlayer(final Block block, final double radius) {
		
		Location location = block.getLocation();
		
		List<Player> players = new ArrayList<Player>();
		
		for(Entity entity : location.getWorld().getNearbyEntities(location, radius, radius, radius)) {
			if(entity instanceof Player) {
				players.add( (Player) entity );
			}
		}
					
		return !players.isEmpty();
		
	}

}
