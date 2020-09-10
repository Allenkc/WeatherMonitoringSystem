import java.util.HashMap;
import java.util.Map;

public enum DisplayType {

    CURRENT("Current"),
    STATISTICS("Statistics"),
    FORECAST("Forecast");

    private static class Holder {
        static Map<String, DisplayType> MAP = new HashMap<>();
    }

    private String displayTypeName;

    DisplayType(String displayTypeName) {

        this.displayTypeName = displayTypeName;
        Holder.MAP.put(displayTypeName , this);
    }

    public String getDisplayTypeName() {
        return displayTypeName;
    }

    public static DisplayType toDisplayType(String displayTypeName) {
        DisplayType displayType = Holder.MAP.get(displayTypeName);
        if (null == displayType) {
            throw new IllegalArgumentException(String.format("Unsupported %s code %s.", DisplayType.class.getName(), displayTypeName));
        }

        return displayType;
    }
}
