public enum Area {

    US("US"),
    ASIA("Asia");

    private String areaName;

    public String getAreaName() {
        return areaName;
    }

    Area(String areaName) {
        this.areaName = areaName;
    }

    public static Area toArea(String areaName) {
        for (Area tmp : Area.values()) {
            if (tmp.getAreaName().equalsIgnoreCase(areaName)) {
                return tmp;
            }
        }
        return null;
    }
}
