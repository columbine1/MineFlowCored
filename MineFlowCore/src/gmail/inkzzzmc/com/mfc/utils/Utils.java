package gmail.inkzzzmc.com.mfc.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import org.bukkit.ChatColor;

public class Utils {

	private static final Random random = new Random();

	/**
	 * @param input - String variable.
	 * @return @param inpout colorised.
	 */
	public static String color(String input) {
		return ChatColor.translateAlternateColorCodes('&', input);
	}

	/**
	 * @param input - String variable.
	 * @return Checks if @param input is a number.
	 */
	public static boolean isNumber(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * @param input - String variable.
	 * @return Checks if @param input is a double.
	 */
	public static boolean isDouble(String input) {
		try {
			Double.parseDouble(input);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * @param min - Mininum number.
	 * @param max - Maximum number.
	 * @return a number between @param min and @param max.
	 */
	public static int getRandom(int min, int max) {
		return random.nextInt(max - min) + min;
	}

	/**
	 * @return a random number between 0 and 100.
	 */
	static int getRandom() {
		return random.nextInt(100);
	}

	/**
	 * @param chance - Integer variable.
	 * @return Checks if a head can be dropped using @param chance.
	 */
	public static boolean canDropHead(int chance) {
		return getRandom() < chance;
	}

	/**
	 * @return the current date using geoIP location.
	 */
	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		return (dateFormat.format(cal.getTime()));
	}
	
	/**
	 * @param time - Long variable.
	 * @return a string value with the remaining time.
	 */
	public static String getTime(long time) {
		final long left = time - System.currentTimeMillis();
		int seconds = (int) (left / 1000) % 60;
		int minutes = (int) ((left / (1000 * 60)) % 60);
		int hours = (int) ((left / (1000 * 60 * 60)) % 24);
		int days = (int) (left / (1000 * 60 * 60 * 24));
		if(days > 1) {
			return days + "d " + hours + "h " + minutes + "m " + seconds + "s";
		} else if(hours > 1) {
			return hours + "h " + minutes + "m " + seconds + "s";
		} else if(minutes > 1) {
			return minutes + "m " + seconds + "s";
		} else {
			return seconds + "s";
		}
	}

}
