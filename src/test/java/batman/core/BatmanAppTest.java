package batman.core;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BatmanAppTest {

    @Test
    void run_acceptsCommands_andExitsOnBye() {
        // Fake user input: add a todo, then exit with bye
        String fakeInput = String.join(System.lineSeparator(),
                "todo buy milk",
                "bye"
        ) + System.lineSeparator();

        ByteArrayInputStream in = new ByteArrayInputStream(fakeInput.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            System.setIn(in);
            System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));

            Batman app = new Batman("./build/test-data", "tasks.csv");
            app.run();

            String output = out.toString(StandardCharsets.UTF_8);

            assertTrue(output.contains("Hello! I'm Batman"), "Should print welcome message");
            assertTrue(output.contains("buy milk"), "Should print the added todo task");
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    @Test
    void run_invalidCommand_showsErrorMessage() {
        // Fake user input: nonsense command then bye
        String fakeInput = String.join(System.lineSeparator(),
                "gibberish",
                "bye"                     
        ) + System.lineSeparator();

        ByteArrayInputStream in = new ByteArrayInputStream(fakeInput.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            System.setIn(in);
            System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));

            Batman app = new Batman("./build/test-data", "tasks.csv");
            app.run();

            String output = out.toString(StandardCharsets.UTF_8);

            assertTrue(output.toLowerCase().contains("error: invalid command."),
                    "Should print error message for invalid command");
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
