package batman.command;

import batman.storage.Storage;
import batman.task.TaskList;

public abstract class Command {
    public abstract void execute(Storage storage, TaskList tasks);
}