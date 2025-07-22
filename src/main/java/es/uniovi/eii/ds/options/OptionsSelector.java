package es.uniovi.eii.ds.options;

import static es.uniovi.eii.ds.options.Colours.*;

import java.io.IOException;
import java.util.*;

import org.jline.terminal.*;

public class OptionsSelector {
    private static final String PROMPT = "(type to filter or use arrows ↑/↓): ";

    private static final int KEY_ENTER = 10;
    private static final int KEY_RETURN = 13;
    private static final int KEY_ESCAPE = 27;
    private static final int KEY_BACKSPACE = 8;
    private static final int ARROW_UP = 'A';
    private static final int ARROW_DOWN = 'B';

    /**
     * Displays a list of options in the terminal, allowing the user to filter and select one.
     * <p>
     * The user can type to filter the options or use the arrow keys to navigate. The method returns
     * the index of the selected option.
     *
     * @param options the list of options to display and select from
     * @return the index of the selected option
     * @throws IOException if an I/O error occurs with the terminal
     */
    public static int showOptions(List<String> options) throws IOException {
        if (options == null || options.isEmpty())
            throw new IllegalArgumentException("Options list cannot be null or empty.");
        if (options.stream().anyMatch(Objects::isNull))
            throw new IllegalArgumentException("Options list cannot contain null values.");

        try (Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .streams(System.in, System.out)
                .build()) {

            OptionsArea optionsArea = new OptionsArea(terminal, options);
            terminal.enterRawMode();
            terminal.writer().flush();

            handleKeys(terminal, optionsArea);

            // Final draw to show selection
            finalDraw(terminal, optionsArea);

            return optionsArea.getSelectedIndex();

        } catch (IOException e) {
            System.err.println("Error with JLine terminal: " + e.getMessage());
            throw e;
        }
    }

    private static void handleKeys(Terminal terminal, OptionsArea optionsArea) throws IOException {

        while (true) {
            // Use draw to print prompt and options, and leave cursor at end of filter
            draw(terminal, optionsArea);

            int ch = terminal.reader().read();

            if (ch == KEY_ENTER || ch == KEY_RETURN) {
                if (optionsArea.hasFilteredOptions())
                    return;

            } else if (ch == KEY_ESCAPE) {
                terminal.reader().read(); // The second read is required to consume an unused character
                int arrow = terminal.reader().read(); // The third read is for the arrow key
                if (arrow == ARROW_UP)
                    optionsArea.decreaseSelectedIndex();
                else if (arrow == ARROW_DOWN)
                    optionsArea.increaseSelectedIndex();

            } else if (ch == KEY_BACKSPACE)
                optionsArea.removeLastCharFromFilter();

            else if (Character.isLetterOrDigit(ch) || Character.isWhitespace(ch) || ch >= 128)
                optionsArea.addToFilter((char) ch);

        }
    }

    // Expects the cursor to be at the prompt line and leaves it there
    private static void draw(Terminal terminal, OptionsArea optionsArea) {

        printPrompt(terminal, optionsArea.getFilter());

        // Print options. Print the first option in the current line.
        // At exit, the cursor will be in the same position as it was before the call (it is restored)
        optionsArea.draw();

        Cursor.moveUp(terminal); // Go back to the prompt line
        int column = PROMPT.length() + optionsArea.getFilter().length() + 1; // 1-based column
        Cursor.goToColumn(terminal, column); // Move cursor to the end of the filter input
    }

    // Expects the cursor to be at the prompt line and leaves it at the next line (the line for the first option).
    private static void printPrompt(Terminal terminal, String filter) {
        Cursor.goToFirstColumn(terminal);
        terminal.writer().print(PROMPT + addColor(filter, Colours.COLOR_HIGHLIGHT));
        Cursor.clearRestOfLine(terminal);
        Cursor.printNewLine(terminal);
    }

    // private static void finalDraw(Terminal terminal, OptionsArea optionsArea) {
    //     printPrompt(terminal, optionsArea.getFilter());
    //     optionsArea.clearAllOptions();
    //     terminal.writer().println("> " + addColor(optionsArea.getSelectedOption(), OptionsArea.COLOR_HIGHLIGHT));
    // }
    private static void finalDraw(Terminal terminal, OptionsArea optionsArea) {
        Cursor.goToFirstColumn(terminal); // Move cursor to the start of the prompt line
        Cursor.clearRestOfLine(terminal); // Delete the prompt line

        Cursor.printNewLine(terminal); // Go to the first option line
        optionsArea.clearAllOptions(); // Delete the options

        Cursor.moveUp(terminal); // Move cursor back to the prompt line
        terminal.writer().println("> " + addColor(optionsArea.getSelectedOption(), Colours.COLOR_HIGHLIGHT));
    }

}
