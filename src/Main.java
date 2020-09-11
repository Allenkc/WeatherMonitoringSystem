import java.io.BufferedReader;
import java.io.FileReader;

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

        // TODO try catch FileNotFoundEX IOEX 並且用finally close br
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String line = reader.readLine();
        while (line != null && !line.isEmpty()) {

            String[] row = line.split("\\s+");

            /**
             * 確認SPEC跟需求後，不再有切換attach 跟detach的問題 而是讀到Data的當下依據Display type來顯示資料 一個Weather Data
             * Object 可以被attach多種Display type 若有多種，則依據attach的順序顯示資料
             */
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
                // 隨後進行輸出，這邊檢查指令的Area 並且依據對應的weather data的去print
                if (Area.toArea(row[1]).equals(Area.US)) {
                    usWeatherData.print(usDB);
                } else {
                    asiaWeatherData.print(asiaDB);
                }
                break;
            case ATTACH:
                updateDisplayType(row);
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
            // 更新 us weather data
            usWeatherData.setTemperature(parseAndRoundDouble(data[2]));
            usWeatherData.setHumidity(parseAndRoundDouble(data[3]));
            usWeatherData.setPressure(parseAndRoundDouble(data[4]));
            // 寫 US DB
            usDB.save(usWeatherData);
        } else {
            // 更新 asia weather data
            asiaWeatherData.setTemperature(parseAndRoundDouble(data[2]));
            asiaWeatherData.setHumidity(parseAndRoundDouble(data[3]));
            asiaWeatherData.setPressure(parseAndRoundDouble(data[4]));
            // 寫 ASIA DB
            asiaDB.save(asiaWeatherData);
        }
    }

    /**
     * 幫WeatherData 更新Display Type
     *
     * @param row
     */
    private static void updateDisplayType(String[] row) {

        if (Area.toArea(row[1]).equals(Area.US)) {
            usWeatherData.add(DisplayType.toDisplayType(row[2]));
        } else {
            asiaWeatherData.add(DisplayType.toDisplayType(row[2]));
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
