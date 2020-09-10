import java.util.HashMap;
import java.util.Map;

public enum CommandType {

    DATA("data"),
    ATTACH("attach"),
    DETACH("detach");

    private String command;

    private static class Holder {
        static Map<String, CommandType> MAP = new HashMap<>();
    }

    CommandType(String command) {

        this.command = command;
        Holder.MAP.put(command, this);
    }

    public String getCommand() {
        return command;
    }

    public static CommandType toCommandType(String command) {

        CommandType commandType = Holder.MAP.get(command);
        if (null == commandType) {
            throw new IllegalArgumentException(String.format("Unsupported %s code %s.", CommandType.class.getName(), command));
        }

        return commandType;
    }
}
