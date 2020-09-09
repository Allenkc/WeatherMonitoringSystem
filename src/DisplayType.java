public enum DisplayType {

    CURRENT("Current"),
    STATISTICS("Statistics"),
    FORECAST("Forecast");

    private String displayTypeName;

    DisplayType(String displayTypeName) {
        this.displayTypeName = displayTypeName;
    }

    public String getDisplayTypeName() {
        return displayTypeName;
    }

    public static DisplayType toDisplayType(String displayTypeName) {
        for (DisplayType tmp : DisplayType.values()) {
            if (tmp.getDisplayTypeName().equalsIgnoreCase(displayTypeName)) {
                return tmp;
            }
        }
        return null;
    }
}
