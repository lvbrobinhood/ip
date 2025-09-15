package batman.ui;

public enum CommandType {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    SNOOZE("snooze"),
    FORMATDATE("formatdate"),
    FIND("find"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public String getTaskType() {
        return this.command;
    }

    public static CommandType fromString(String input) {
        for (CommandType type : CommandType.values()) {
            if (input.contains(type.getTaskType())) {
                return type;
            }
        }
        return null; // else is invalid CommandType
    }
}
