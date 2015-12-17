package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.cooldowns.Cooldown;
import gmail.inkzzzmc.com.mfc.cooldowns.Cooldown.CooldownManager;
import gmail.inkzzzmc.com.mfc.cooldowns.Cooldown.CooldownTypes;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Command_Feed extends CommandFactory {

	private final JavaPlugin plugin;
	
	public Command_Feed(JavaPlugin plugin) {
		super("feed", "mineflow.feed", false);
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		Player player = (Player) sender;
		MineFlowPlayer fplayer = PlayerManager.getPlayer(player);
		
		Cooldown cooldown = null;
		
		if(CooldownManager.isCooldown(fplayer, CooldownTypes.FEED)) {
			cooldown = CooldownManager.getCooldown(fplayer, CooldownTypes.FEED);
		}
		
		if(cooldown == null) {
			
			player.setSaturation(6);
			player.setFoodLevel(20);
			
			new Cooldown(fplayer, 60, CooldownTypes.FEED, plugin);
			
			player.sendMessage(Language.FEED.getMessage());
			return;
			
		}
		
		player.sendMessage(Language.FEED_COOLDOWN.getMessage().replace("%time%", String.valueOf(cooldown.getTime() + " seconds")));
		
	}
	
}
