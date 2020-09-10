/**
 * 用來及時update(監測)資料的物件
 */
public class WeatherData {

    private Area area;
    private DisplayType displayType;
    private Double temperature;
    private Double humidity;
    private Double pressure;

    public WeatherData() {
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public DisplayType getDisplayType() {
        return displayType;
    }

    public void setDisplayType(DisplayType displayType) {
        this.displayType = displayType;
    }

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
     * 確認當下 Area 跟 模式 然後列印
     */
    public void print(DataBase dataBase) {

        if (null != this.area) {
            // 確認有Area
            if (null != this.displayType) {
                switch (this.displayType) {
                    case CURRENT:
                        printCurrently();
                        break;
                    case STATISTICS:
                        if (!dataBase.isEmpty()) {
                            dataBase.print();
                        }
                        break;
                    case FORECAST:
                        break;
                }
            }
        }

    }

    public void printCurrently() {
        System.out.println("Temperature " + this.temperature);
        System.out.println("Humidity " + this.humidity);
        System.out.println("Pressure " + this.pressure);

    }
}
