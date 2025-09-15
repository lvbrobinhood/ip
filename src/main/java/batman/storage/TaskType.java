package batman.storage;

/**
 * Represents the different types of commands that can create tasks.
 * <p>
 * Each taskType type corresponds to a keyword used in user input
 * and maps to a specific task type.
 * </p>
 */
public enum TaskType {
    /** Represents a to-do taskType. */
    TODO("todo"),

    /** Represents a deadline taskType. */
    DEADLINE("deadline"),

    /** Represents an event taskType. */
    EVENT("event");

    /** The taskType keyword associated with this type. */
    private final String taskType;

    /**
     * Creates a {@code TaskType} with the given taskType keyword.
     *
     * @param taskType the keyword string for this taskType type
     */
    TaskType(String taskType) {
        this.taskType = taskType;
    }

    /**
     * Returns the taskType keyword associated with this type.
     *
     * @return the taskType keyword
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * Converts a string input to its corresponding {@code TaskType}.
     * <p>
     * If the input starts with a matching taskType keyword, the corresponding
     * enum constant is returned. Otherwise, {@code null} is returned.
     * </p>
     *
     * @param input the input string to parse
     * @return the matching {@code TaskType}, or {@code null} if invalid
     */
    public static TaskType fromString(String input) {
        for (TaskType type : TaskType.values()) {
            if (input.startsWith(type.getTaskType())) {
                return type;
            }
        }
        return null; // else is invalid taskType
    }
}
