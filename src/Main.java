import java.io.BufferedReader;
import java.io.FileReader;
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
        while (line != null) {
            String[] row = line.split("\\s+");
            //TODO 拿 row[0]來判斷指令類型
            System.out.println(line);
            line = reader.readLine();
        }

    }

    /**
     * 檢查command Type 並依據指令去做對應的事情
     * @param row
     * @return
     */
    private Optional<CommandType> checkCmdType(String[] row) {

        CommandType cmdType = CommandType.values()[CommandType.toCommandType(row[0]).ordinal()];

        switch (cmdType) {
            case DATA:
                //TODO 依據內容儲存資料
                break;
            case ATTACH:
                break;
            case DETACH:
                break;
            default:
                return null;
        }

        return null;
    }
}
