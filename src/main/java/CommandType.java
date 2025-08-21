public enum CommandType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    // Convert string input to enum, returns null if invalid
    public static CommandType fromString(String input) {
        for (CommandType type : CommandType.values()) {
            if (input.startsWith(type.getCommand())) {
                return type;
            }
        }
        return null; // else is invalid command
    }
}
