package gmail.inkzzzmc.com.mfc.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileUtil {

	private static String DIRECTORY = "plugins/MineflowCore/";

	public static FileConfiguration getFile(String fileName) {
		try {
		return YamlConfiguration.loadConfiguration(getFile(fileName, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static File getFile(String fileName, boolean createIfNotExist)
			throws IOException {
		File file = new File(DIRECTORY, fileName);
		if (createIfNotExist && !file.exists()) {
			boolean success = file.createNewFile();
			if (!success)
				throw new IOException("File couldn't be created");
		}
		return file;
	}

	public static List<File> getAllFiletype(String ending) {
		List<File> files = new ArrayList<File>();
		File dir = new File(DIRECTORY);
		try {
			for (File file : dir.listFiles())
				if (file.getName().endsWith(ending)) {
					files.add(file);
				}
		} catch (NullPointerException ex) {
			Bukkit.getPluginManager().disablePlugin(
					Bukkit.getPluginManager()
							.getPlugin(DIRECTORY.split("/")[1]));
			Bukkit.getLogger().severe("No files found. Now disabling.");
			ex.printStackTrace();
		}
		return files;
	}

	public static String getDirectory() {
		return DIRECTORY;
	}

	public static void saveFile(FileConfiguration file, String name) {
		try {
			file.save(FileUtil.getFile(name, false));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
