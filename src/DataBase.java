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
}
