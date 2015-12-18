package gmail.inkzzzmc.com.mfc.placeholder;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class PlaceholderHandler implements Listener {

    public static void unregisterInternalPlaceholderHooks() {
    }

    public static void unregisterInternalRecipientPlaceholderHooks() {
    }

    /**
     * obtain the names of every external hook that was not registered by DeluxeChat
     * @return Set of plugin names that DeluxeChat did not register placeholders for
     */
    public static Set<String> getExternalPlaceholderPlugins() {
        return null;
    }

    /**
     * obtain the names of every external hook that was not registered by DeluxeChat
     * @return Set of plugin names that DeluxeChat did not register placeholders for
     */
    public static Set<String> getExternalRecipientPlaceholderPlugins() {
        return null;
    }

    public static boolean registerPlaceholderHook(Plugin plugin, DeluxePlaceholderHook placeholderHook) {
        return true;
    }

    public static boolean registerPlaceholderHook(String plugin, DeluxePlaceholderHook placeholderHook) {
        return true;
    }

    public static boolean registerPlaceholderHook(Plugin plugin, DeluxePlaceholderHook placeholderHook, boolean internal) {
        return true;
    }

    public static boolean registerPlaceholderHook(String plugin, DeluxePlaceholderHook placeholderHook, boolean internal) {
        return true;
    }

    public static boolean registerRecipientPlaceholderHook(Plugin plugin, DeluxeRecipientPlaceholderHook placeholderHook) {
        return true;
    }

    public static boolean registerRecipientPlaceholderHook(String plugin, DeluxeRecipientPlaceholderHook placeholderHook) {
        return true;
    }

    public static boolean registerRecipientPlaceholderHook(Plugin plugin, DeluxeRecipientPlaceholderHook placeholderHook, boolean internal) {
        return true;
    }

    public static boolean registerRecipientPlaceholderHook(String plugin, DeluxeRecipientPlaceholderHook placeholderHook, boolean internal) {
        return true;
    }

    public static boolean unregisterPlaceholderHook(Plugin plugin) {
        return true;
    }

    public static boolean unregisterPlaceholderHook(String plugin) {
        return true;
    }

    public static boolean unregisterRecipientPlaceholderHook(Plugin plugin) {
        return true;
    }

    public static boolean unregisterRecipientPlaceholderHook(String plugin) {
        return true;
    }

    public static Set<String> getRegisteredPlaceholderPlugins() {
        return null;
    }

    public static Set<String> getRegisteredRecipientPlaceholderPlugins() {
        return null;
    }

    public static void unregisterAllPlaceholderHooks() {
    }

    public static Map<String, DeluxePlaceholderHook> getPlaceholders() {
        return null;
    }

    public static Map<String, DeluxeRecipientPlaceholderHook> getRecipientPlaceholders() {
        return null;
    }

    public static List<String> setPlaceholders(Player p, List<String> text) {
        return text;
    }

    public static String setPlaceholders(Player player, String text) {
        return text;
    }

    public static List<String> setRecipientPlaceholders(Player p, Player recipient, List<String> text) {
        return text;
    }

    public static String setRecipientPlaceholders(Player player, Player recipient, String text) {
        return text;
    }

    public static List<String> setPMRecipientPlaceholders(Player recipient, List<String> text) {
        return text;
    }

    public static String setPMRecipientPlaceholders(Player recipient, String text) {
        return text;
    }
}