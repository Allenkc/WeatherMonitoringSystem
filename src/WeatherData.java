import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 用來及時update(監測)資料的物件
 *
 * <p>依據SPEC [Area] is limited to “US” and “Asia” and each type will only have one instance.
 *
 * <p>應該是"各"一個instance
 */
public class WeatherData {

    private Area area;
    /**
     * TODO 重要 DisplayType 是一個List 各種display type只會有一個，應該改用LinkedHashMap ?? 再考慮
     *
     * <p>讀到Data的當下 只會顯示該Area的資料，但是如果有多種Display Type就要通通顯示 然而如果Dispaly Type 是空就不顯示
     */
    private List<DisplayType> displayTypeList;

    private Double temperature;
    private Double humidity;
    private Double pressure;

    public WeatherData(Area area) {
        this.area = area;
        this.displayTypeList = new ArrayList<>();
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    //    public DisplayType getDisplayType() {
    //        return displayType;
    //    }
    //
    //    public void setDisplayType(DisplayType displayType) {
    //        this.displayType = displayType;
    //    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     * 新增displayType
     *
     * @param displayType
     */
    public void add(DisplayType displayType) {

        // 檢查有沒有存在display type了，如果不存在才去add
        Optional<DisplayType> result =
                this.displayTypeList.stream()
                        .filter(displayType1 -> displayType1.equals(displayType))
                        .findFirst();
        if (!result.isPresent()) {
            this.displayTypeList.add(displayType);
        }
    }

    public void detach(DisplayType displayType) {

        // 找到對應的displayType然後remove
        this.displayTypeList.removeIf(displayType::equals);
    }

    /** 確認當下 Area 跟 模式 然後列印 如果沒有被attach任何 displayType 就不列印 */
    public void print(DataBase dataBase) {

        // 確認有area 且 displayType list 不為空
        if (null != this.area && !this.displayTypeList.isEmpty()) {

            for (DisplayType displayType : this.displayTypeList) {

                if (null != displayType) {
                    switch (displayType) {
                        case CURRENT:
                            printCurrently();
                            break;
                        case STATISTICS:
                            if (!dataBase.isEmpty()) {
                                dataBase.print();
                            }
                            break;
                        case FORECAST:
                            printInForecastMode();
                            break;
                    }
                }
            }
        }
    }

    public void printCurrently() {
        System.out.println("Temperature " + this.temperature);
        System.out.println("Humidity " + this.humidity);
        System.out.println("Pressure " + this.pressure);
    }

    public void printInForecastMode() {
        if (this.humidity > 0.8) {
            System.out.println("Forecast rain.");
        } else if (this.humidity < 0.2) {
            System.out.println("Forecast sunny.");
        } else {
            System.out.println("Forecast cloudy.");
        }
    }
}
