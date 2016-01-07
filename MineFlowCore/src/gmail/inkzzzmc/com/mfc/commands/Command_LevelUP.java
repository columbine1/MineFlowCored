package gmail.inkzzzmc.com.mfc.commands;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import gmail.inkzzzmc.com.mfc.Main;
import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.cooldowns.Cooldown;
import gmail.inkzzzmc.com.mfc.cooldowns.Cooldown.CooldownManager;
import gmail.inkzzzmc.com.mfc.cooldowns.Cooldown.CooldownTypes;
import gmail.inkzzzmc.com.mfc.events.LevelChangeEvent;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.levels.Level;
import gmail.inkzzzmc.com.mfc.levels.LevelManager;
import gmail.inkzzzmc.com.mfc.player.MineFlowPlayer;
import gmail.inkzzzmc.com.mfc.player.PlayerManager;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.math.BigDecimal;

public class Command_LevelUP extends CommandFactory {

	private final Main plugin;
	
	public Command_LevelUP(Main plugin) {
		super("levelup", false);
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		Player player = (Player) sender;
		MineFlowPlayer fplayer = PlayerManager.getPlayer(player);
		
		Level level = LevelManager.getInstance().getLevelAfter(fplayer.getLevel().getLevel());
		
		if(level == null || level.isLastLevel()) {
			player.sendMessage(Language.LAST_LEVEL.getMessage());
			return;
		}
		
		double cost = level.getCost();
		
		double global = 0;
		double personal = 0;
		
		if(Level.isGlobalDiscount()) {
			
			global = cost * Level.global_discount;
			
		} 

		if(fplayer.hasDiscount()) {
			
			personal = cost * fplayer.getLevelDiscount();
			
		}
		
		if(global > personal) {
			cost -= global;
		} else {
			cost -= personal;
		}

		try {
			if(Economy.getMoneyExact(player.getName()).doubleValue() < cost) {
                player.sendMessage(Language.NOT_ENOUGH_MONEY.getMessage());
                return;
            }
            else if(CooldownManager.isCooldown(fplayer, CooldownTypes.LEVELLING)) {

                int seconds = CooldownManager.getCooldown(fplayer, CooldownTypes.LEVELLING).getTime();
                player.sendMessage(Language.LEVEL_COOLDOWN.getMessage().replace("%time%", String.valueOf(seconds)));
                return;

            }
		} catch (UserDoesNotExistException e) {
			e.printStackTrace();
		}

		try {
			if(Economy.getMoneyExact(player.getName()).doubleValue() < cost) {

                Bukkit.getPluginManager().callEvent(new LevelChangeEvent(fplayer, level));
                fplayer.setLevel(level);
				Economy.substract(player.getName(), BigDecimal.valueOf(cost));
                player.sendMessage(Language.LEVELUP.getMessage().replace("%level%", String.valueOf(level.getLevel())));
                new Cooldown(fplayer, 5, CooldownTypes.LEVELLING, plugin);

            }
            else
            {

                player.sendMessage(Language.NOT_ENOUGH_MONEY.getMessage());

            }
		} catch (UserDoesNotExistException e) {
			e.printStackTrace();
		} catch (NoLoanPermittedException e) {
			e.printStackTrace();
		}

	}
	
}
