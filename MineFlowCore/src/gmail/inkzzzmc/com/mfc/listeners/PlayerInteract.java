package gmail.inkzzzmc.com.mfc.listeners;

import gmail.inkzzzmc.com.mfc.Main;
import gmail.inkzzzmc.com.mfc.commands.Command_BoosterGive;
import gmail.inkzzzmc.com.mfc.events.SkillLevelChangeEvent;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.utils.Utils;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.skills.SkillType;

public class PlayerInteract implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Action action = e.getAction();
		ItemStack is = e.getItem();
		
		if(action == null || is == null) { return; }
		
		Command_BoosterGive.useBooster(player, is);
		
		if(is.getType() == Material.PAPER && is.hasItemMeta()) {
			if(is.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.color("&b&lMineflow Note &8(&7Right Click&8)"))) {
				
				String value = (is.getItemMeta().getLore().get(0).split(":")[1].trim().replace("$", ""));
				int money = Integer.valueOf(ChatColor.stripColor(value));
				
				player.getInventory().remove(is);
				
				int size = is.getAmount();
				ItemStack itemstack = new ItemStack(is);
				size -= 1;
				itemstack.setAmount(size);
				
				if(size >= 1) {
					player.getInventory().addItem(itemstack);
				}
				
				Main.economy.depositPlayer(player, money);
				player.sendMessage(Language.NOTE_REDEEM.getMessage().replace("%amount%", String.valueOf(money)));
				return;
			}
			
			if(is.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.color("&b&lEXP Voucher &8(&7Right Click&8)"))) {
				
				String value = (is.getItemMeta().getLore().get(0).split(":")[1].trim().replace(".0", ""));
				int exp = Integer.valueOf(ChatColor.stripColor(value));

				player.getInventory().remove(is);

				int size = is.getAmount();
				ItemStack itemstack = new ItemStack(is);
				size -= 1;
				itemstack.setAmount(size);
				
				if(size >= 1) {
					player.getInventory().addItem(itemstack);
				}
				
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "exp give " + player.getName() + " " + exp);				
				player.sendMessage(Language.EXP_REDEEM.getMessage().replace("%amount%", String.valueOf(exp)));
				return;				
			}
			
			if(is.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.color("&6&lMCMMO XP Voucher &8(&7Right Click&8)"))) {
				
				String value = ChatColor.stripColor(is.getItemMeta().getLore().get(1).replace("+", "").replace("XP", ""));
				
				String skill = ChatColor.stripColor(is.getItemMeta().getLore().get(0).split(":")[1].trim());
				
				int xp = Integer.valueOf(value.trim());
				
				if(SkillType.getSkill(skill) != null) {
					
					ExperienceAPI.addLevel(player, skill, xp);
					
					player.getInventory().remove(is);
					
					int size = is.getAmount();
					ItemStack itemstack = new ItemStack(is);
					size -= 1;
					itemstack.setAmount(size);
					
					if(size >= 1) {
						player.getInventory().addItem(itemstack);
					}
					
					Bukkit.getServer().getPluginManager().callEvent(new SkillLevelChangeEvent(player, xp, skill, true));
					return;
				}
				
				player.sendMessage(Language.MCMMO_REDEEM.getMessage().replace("%value%", String.valueOf(xp)).replace("%skill%", skill.toLowerCase()));
				
			}
			
		}
		else if(is.getType() == Material.MONSTER_EGG && is.getDurability() == 50) {
			if(is.hasItemMeta()) {
				if(is.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.color("&aCharged Creeper Egg"))) {
					
					e.setCancelled(true);
					
					final Location spawn_location = player.getEyeLocation().getBlock().getLocation();
					
					int size = is.getAmount();
					
					if(size > 1) {
						is.setAmount(--size);
					} else {
						player.getInventory().remove(player.getItemInHand());
					}
					
					Creeper creeper = (Creeper) player.getWorld().spawnEntity(spawn_location, EntityType.CREEPER);
					creeper.setPowered(true);
					return;
				}
			}
		}
		
		
	}
	
}
