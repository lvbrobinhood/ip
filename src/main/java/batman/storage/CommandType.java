package batman.storage;

/**
 * Represents the different types of commands that can create tasks.
 * <p>
 * Each command type corresponds to a keyword used in user input
 * and maps to a specific task type.
 * </p>
 */
public enum CommandType {
    /** Represents a to-do command. */
    TODO("todo"),

    /** Represents a deadline command. */
    DEADLINE("deadline"),

    /** Represents an event command. */
    EVENT("event");

    /** The command keyword associated with this type. */
    private final String command;

    /**
     * Creates a {@code CommandType} with the given command keyword.
     *
     * @param command the keyword string for this command type
     */
    CommandType(String command) {
        this.command = command;
    }

    /**
     * Returns the command keyword associated with this type.
     *
     * @return the command keyword
     */
    public String getCommand() {
        return command;
    }

    /**
     * Converts a string input to its corresponding {@code CommandType}.
     * <p>
     * If the input starts with a matching command keyword, the corresponding
     * enum constant is returned. Otherwise, {@code null} is returned.
     * </p>
     *
     * @param input the input string to parse
     * @return the matching {@code CommandType}, or {@code null} if invalid
     */
    public static CommandType fromString(String input) {
        for (CommandType type : CommandType.values()) {
            if (input.startsWith(type.getCommand())) {
                return type;
            }
        }
        return null; // else is invalid command
    }
}
