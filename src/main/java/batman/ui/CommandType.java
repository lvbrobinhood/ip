package batman.ui;

/**
 * Enum representing the different types of commands in the Batman task manager application.
 * <p>
 * Each {@code CommandType} corresponds to a specific task-related operation (e.g., adding a task, marking a task, etc.).
 * </p>
 */
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

    /**
     * Constructor for {@code CommandType} enum.
     *
     * @param command the string representation of the command type
     */
    CommandType(String command) {
        this.command = command;
    }

    /**
     * Returns the string representation of the command type.
     *
     * @return the command type as a string
     */
    public String getTaskType() {
        return this.command;
    }

    /**
     * Converts a string input into the corresponding {@code CommandType}.
     * <p>
     * This method checks if the given string contains any of the command types' names
     * and returns the corresponding {@code CommandType} if a match is found.
     * </p>
     *
     * @param input the input string to convert into a {@code CommandType}
     * @return the matching {@code CommandType}, or {@code null} if no match is found
     */
    public static CommandType fromString(String input) {
        for (CommandType type : CommandType.values()) {
            if (input.contains(type.getTaskType())) {
                return type;
            }
        }
        return null; // else is invalid CommandType
    }
}
