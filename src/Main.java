import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Optional;

public class Main {

    // Only One instance in whole life cycle
    private static WeatherData weatherData = new WeatherData();
    private static DataBase asiaDB = new DataBase(Area.ASIA.getAreaName());
    private static DataBase usDB = new DataBase(Area.US.getAreaName());

    public static void main(String[] args) throws Exception {

        if (null == args[0]) {
            throw new IllegalArgumentException();
        }

        String fileName = args[0];
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String line = reader.readLine();
        while (line != null && !line.isEmpty()) {

            String[] row = line.split("\\s+");
            //拿row[0]來判斷指令類型
            checkCmdType(row);
            line = reader.readLine();
        }

    }

    /**
     * 檢查command Type 並依據指令去做對應的事情
     *
     * @param row
     * @return
     */
    private static void checkCmdType(String[] row) {

        CommandType cmdType = CommandType.toCommandType(row[0]);

        switch (cmdType) {
            case DATA:
                //依據內容儲存資料
                updateWeatherData(row);
                //隨後進行輸出
                output();
                break;
            case ATTACH:
                weatherData.setArea(Area.toArea(row[1]));
                weatherData.setDisplayType(DisplayType.toDisplayType(row[2]));
                break;
            case DETACH:
                Area area = Area.toArea(row[1]);
                if (null != weatherData.getArea() && area.equals(weatherData.getArea())) {

                    DisplayType displayType =
                            DisplayType.toDisplayType(row[2]);
                    if (null != weatherData.getDisplayType() &&
                            displayType.equals(weatherData.getDisplayType())) {
                        weatherData.setArea(null);
                        weatherData.setDisplayType(null);
                    }

                }

                break;

        }

    }

    /**
     * 及時更新當下的 weather data obj
     *
     * @param data
     */
    private static void updateWeatherData(String[] data) {

        Area area = Area.toArea(data[1]);

        weatherData.setTemperature(parseAndRoundDouble(data[2]));
        weatherData.setHumidity(parseAndRoundDouble(data[3]));
        weatherData.setPressure(parseAndRoundDouble(data[4]));

        if (area.equals(Area.US)) {
            // 寫 US DB
            usDB.save(weatherData);
        } else {
            // 寫 ASIA DB
            asiaDB.save(weatherData);
        }

    }

    /**
     * 收到DATA指令的當下要去進行最終輸出
     */
    private static void output() {
        if (null != weatherData.getArea()) {
            if (Area.US.equals(weatherData.getArea())) {
                weatherData.print(usDB);
            } else {
                weatherData.print(asiaDB);
            }
        }
    }

    /**
     * parse str to double
     *
     * @param str
     * @return
     */
    private static double parseAndRoundDouble(String str) {
        return round(Double.parseDouble(str), 1);
    }

    /**
     * round to one decimal
     *
     * @param value
     * @param precision
     * @return
     */
    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
