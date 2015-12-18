package gmail.inkzzzmc.com.mfc.placeholder;

import java.util.HashMap;
import java.util.Map;

public class PlaceholderLayout {

    private static Map<String, PlaceholderLayout> layouts = new HashMap<String, PlaceholderLayout>();

    private String identifier;

    private String hasValue;

    private String noValue;

    public PlaceholderLayout(String identifier, String hasValue, String noValue) {
        this.identifier = identifier;
        this.hasValue = hasValue;
        this.noValue = noValue;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getHasValue() {
        return hasValue;
    }

    public String getNoValue() {
        return noValue;
    }

    public void load() {
        if (layouts == null) {
            layouts = new HashMap<String, PlaceholderLayout>();
        }

        layouts.put(identifier, this);
    }

    public static void load(PlaceholderLayout layout) {
        if (layouts == null) {
            layouts = new HashMap<String, PlaceholderLayout>();
        }

        layouts.put(layout.getIdentifier(), layout);
    }

    public boolean unload() {
        if (layouts == null || layouts.isEmpty()) {
            return false;
        }
        return layouts.remove(identifier) != null;
    }

    public static boolean unload(PlaceholderLayout layout) {
        if (layouts == null || layouts.isEmpty()) {
            return false;
        }
        return layouts.remove(layout.getIdentifier()) != null;
    }

    public static void unloadAll() {
        layouts = null;
    }

    public static PlaceholderLayout getLayout(String identifier) {
        if (layouts == null || layouts.isEmpty()) {
            return null;
        }
        if (layouts.containsKey(identifier) && layouts.get(identifier) != null) {
            return layouts.get(identifier);
        }
        return null;
    }

}