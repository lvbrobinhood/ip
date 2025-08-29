package batman.ui;

import batman.command.Command;
import batman.command.ToDoCommand;
import batman.exception.InvalidCommandException;
import batman.exception.NoDeadlineException;
import batman.exception.NoDescriptionException;
import batman.exception.NoFromToException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    @Test
    void parse_validTodoCommand_returnsTodoCommand() throws NoDescriptionException, NoDeadlineException,
            NoFromToException, InvalidCommandException {
        Command c = Parser.parse("todo buy milk");
        assertInstanceOf(ToDoCommand.class, c);
    }

    @Test
    void parse_emptyInput_throwsNoDescriptionException() {
        assertThrows(NoDescriptionException.class, () -> Parser.parse("todo "));
    }
}
