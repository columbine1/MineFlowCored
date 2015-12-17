package gmail.inkzzzmc.com.mfc.utils;

import java.util.Collection;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionUtil {
	
	/**
	 * @return Checks if the @param player has @param type potion affect, 
	 * with an amplifier equal to or more than @param amplifier.
	 */
	public static boolean hasPotion(final Player player, final PotionEffectType type, final int amplifier) {
		
		if(player.hasPotionEffect(type)) {
			
			final Collection<PotionEffect> potions = player.getActivePotionEffects();
			
			for(PotionEffect potion : potions) {
				if(potion.getType().equals(type)) {
					if(potion.getAmplifier() >= amplifier) {
						return true;
					}
				}
			}
			
		}
		return false;
	}

}
