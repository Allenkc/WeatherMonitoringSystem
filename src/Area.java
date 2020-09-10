import java.util.HashMap;
import java.util.Map;

public enum Area {

    US("US"),
    ASIA("Asia");

    private static class Holder {
        static Map<String, Area> MAP = new HashMap<>();
    }

    private String areaName;

    public String getAreaName() {
        return areaName;
    }

    Area(String areaName) {

        this.areaName = areaName;
        Holder.MAP.put(areaName, this);
    }

    public static Area toArea(String areaName) {
        Area area = Holder.MAP.get(areaName);
        if (null == area) {
            throw new IllegalArgumentException(String.format("Unsupported %s code %s.", Area.class.getName(), areaName));
        }

        return area;
    }
}
