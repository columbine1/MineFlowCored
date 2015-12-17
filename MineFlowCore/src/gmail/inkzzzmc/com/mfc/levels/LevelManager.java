package gmail.inkzzzmc.com.mfc.levels;

import gmail.inkzzzmc.com.mfc.utils.FileUtil;

import java.util.Arrays;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class LevelManager {

	private static LevelManager INSTANCE;
	
	/**
	 * @return the instance of the class.
	 */
	public static LevelManager getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new LevelManager();
		}
		return INSTANCE;
	}
	
	/**
	 * @return the highest level.
	 */
	public int getHighestLevel() {
		
		int id = 0;
		
		for(Level level : Level.getLevels()) {
			if(level.getLevel() > id) {
				id = level.getLevel();
			}
		}
		return id;
	}
	
	/**
	 * @param id - Level id.
	 * @return the level before @param id.
	 */
	public Level getLevelBefore(int id) {
		
		int before = id - 1;
		
		for(Level level : Level.getLevels()) {
			if(level.getLevel() == before) {
				return level;
			}
		}
		return null;
	}
	
	/**
	 * @param id - Level id.
	 * @return the level after @param id.
	 */
	public Level getLevelAfter(int id) {
		
		int after = id + 1;
		
		for(Level level : Level.getLevels()) {
			if(level.getLevel() == after) {
				return level;
			}
		}
		return null;
		
	}
	
	/**
	 * Loads and registers all levels from the file, levels.yml
	 */
	public void loadLevels() {
		
		final FileConfiguration file = FileUtil.getFile("levels.yml");
		
		ConfigurationSection section = file.getConfigurationSection("Levels");
		
		if(section == null) {
			
			String path = "Levels.0.";
			
			file.set(path + "Cost", 0);
			file.set(path + ".Permissions", Arrays.asList("").toArray());
			FileUtil.saveFile(file, "levels.yml");
			return;
			
		}
		
		for(String string : section.getKeys(false)) {
			
			String path = "Levels." + string + ".";
			
			int level = Integer.valueOf(string);
			int cost = file.getInt(path + "Cost");
			List<String> permissions = file.getStringList(path + ".Permissions");
			
			Level l = new Level(level, cost, permissions);
			Level.registerLevel(l);
			
		}
 		
	}
	
}
