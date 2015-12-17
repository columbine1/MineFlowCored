package gmail.inkzzzmc.com.mfc.core.locations;

import gmail.inkzzzmc.com.mfc.utils.Utils;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class LocationUtil {
	
	private static LocationUtil INSTANCE;
	
	/**
	 * @return the instance of the class.
	 */
	public static LocationUtil getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new LocationUtil();
		}
		return INSTANCE;
	}

	private final Random random;
	
	private LocationUtil() {
		this.random = new Random();
	}
	
	/**
	 * @param min - Minimum number.
	 * @param max - Maximum number.
	 * @return a random number between @param min and @param max.
	 */
	public Location getRandomLocation(int min, int max) {
		
		double x = 0, z = 0;
		
		do {
			
			x = Utils.getRandom(min, max);
			
		} while (x == 0 || x > max || x < (-max));
		
		do {
			
			z = Utils.getRandom(min, max);
			
		} while (z == 0 || z > max || z < (-max));
		
		if(random.nextInt(10) > 5) {
			z = z - z * 2;
		}
		
		if(random.nextInt(10) > 5) {
			x = x - x * 2;
		}
				
		Location location = new Location(Bukkit.getWorld("FactionsWorld"), x, 60, z);
		
		Block high = location.getWorld().getHighestBlockAt(location);
		
		location.setY(high.getY());
		
		return location;
		
	}
	
}
