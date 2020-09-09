public enum CommandType {

    DATA("data"),
    ATTACH("attach"),
    DETACH("detach");

    private String command;

    CommandType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static CommandType toCommandType(String command) {
        for (CommandType tmp : CommandType.values()) {
            if (tmp.getCommand().equalsIgnoreCase(command)) {
                return tmp;
            }
        }
        return null;
    }
}
