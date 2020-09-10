import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private String areaName;
    private List<Double> temperatureList;
    private List<Double> humidityList;
    private List<Double> pressureList;

    public DataBase() {
    }

    public DataBase(String areaName) {
        this.areaName = areaName;
        this.temperatureList = new ArrayList<>();
        this.humidityList = new ArrayList<>();
        this.pressureList = new ArrayList<>();
    }

    public void save(Double temp , Double humid, Double press){
        this.temperatureList.add(temp);
        this.humidityList.add(humid);
        this.pressureList.add(press);
    }

    public void save(WeatherData weatherData){
        this.temperatureList.add(weatherData.getTemperature());
        this.humidityList.add(weatherData.getHumidity());
        this.pressureList.add(weatherData.getPressure());
    }

    /**
     * 輸出資料
     * ex:
     * Temperature 21.0
     * Humidity 0.9
     * Pressure 1014.5
     * Temperature 21.0 19.5
     * Humidity 0.9 0.3
     * Pressure 1014.5 1015.0
     * Temperature 21.0 19.5 18.0 17.3
     * Humidity 0.9 0.3 0.1 0.6
     * Pressure 1014.5 1015.0 1014.5 1015.0
     */
    public void print(){
        System.out.print("Temperature ");
        for (double temp :this.temperatureList){
            System.out.print(temp+" ");
        }
        System.out.println();
        System.out.print("Humidity ");
        for (double temp :this.humidityList){
            System.out.print(temp+" ");
        }
        System.out.println();
        System.out.print("Pressure ");
        for (double temp :this.pressureList){
            System.out.print(temp+" ");
        }
        System.out.println();
    }

    public boolean isEmpty(){
        return temperatureList.isEmpty() && humidityList.isEmpty() && pressureList.isEmpty();
    }
}
