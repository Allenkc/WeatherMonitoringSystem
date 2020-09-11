import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Optional;

public class Main {

    // TODO 改成 US 跟 Aisa 各一個 weather data obj
    //    private static WeatherData weatherData = new WeatherData();

    // WIP
    private static WeatherData usWeatherData = new WeatherData(Area.US);
    private static WeatherData asiaWeatherData = new WeatherData(Area.ASIA);

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
            // 拿row[0]來判斷指令類型
            // TODO 沒有 attach跟detach切換的問題
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
                // 依據內容儲存資料
                updateWeatherData(row);
                // 隨後進行輸出，這邊檢查指令的Area有沒有跟當下weather data的area一樣
                if (Area.toArea(row[1]).equals(Area.US)) {
                    usWeatherData.print(usDB);
                } else {
                    asiaWeatherData.print(asiaDB);
                }
                break;
            case ATTACH:
                updataDisplayType(row);
                break;
            case DETACH:
                Area area = Area.toArea(row[1]);
                if (area.equals(Area.US)) {
                    // 按照格式display type在row[2]
                    usWeatherData.detach(DisplayType.toDisplayType(row[2]));
                } else {
                    asiaWeatherData.detach(DisplayType.toDisplayType(row[2]));
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
        if (area.equals(Area.US)) {
            // TODO 更新 us weather data
            usWeatherData.setTemperature(parseAndRoundDouble(data[2]));
            usWeatherData.setHumidity(parseAndRoundDouble(data[3]));
            usWeatherData.setPressure(parseAndRoundDouble(data[4]));
            // 寫 US DB
            usDB.save(usWeatherData);
        } else {
            // TODO 更新 asia weather data
            asiaWeatherData.setTemperature(parseAndRoundDouble(data[2]));
            asiaWeatherData.setHumidity(parseAndRoundDouble(data[3]));
            asiaWeatherData.setPressure(parseAndRoundDouble(data[4]));
            // 寫 ASIA DB
            asiaDB.save(asiaWeatherData);
        }
    }

    /** TODO 抽 更新display type的方法 */
    private static void updataDisplayType(String[] row) {

        if (Area.toArea(row[1]).equals(Area.US)) {
            usWeatherData.add(DisplayType.toDisplayType(row[2]));
        } else {
            asiaWeatherData.add(DisplayType.toDisplayType(row[2]));
        }
    }
    //
    //    /** 收到DATA指令的當下要去進行最終輸出 */
    //    private static void output() {
    //        if (null != weatherData.getArea()) {
    //            if (Area.US.equals(weatherData.getArea())) {
    //                weatherData.print(usDB);
    //            } else {
    //                weatherData.print(asiaDB);
    //            }
    //        }
    //    }

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
