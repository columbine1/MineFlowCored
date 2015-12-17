package gmail.inkzzzmc.com.mfc.listeners;

import gmail.inkzzzmc.com.mfc.events.SkillLevelChangeEvent;
import gmail.inkzzzmc.com.mfc.utils.Utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SkillLevelChange implements Listener {

	@EventHandler
	public void onSkillLevelChange(SkillLevelChangeEvent e) {
		
		Player player = e.getPlayer();
		String type = e.getSkill();
		int amount = e.getLevel();
		
		player.sendMessage(Utils.color(e.isIncrease() ? "&a&l+" + amount + " " + type : "&c&l-" + amount + " " + type.toUpperCase()));
		
	}
	
}
