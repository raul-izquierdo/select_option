package es.uniovi.eii.ds.options;

import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp.Capability;
import static org.jline.utils.InfoCmp.Capability.*;

public class Cursor {

    // Clears the rest of the line from the cursor position
    public static void clearRestOfLine(Terminal terminal) {
        writeAndFlush(terminal, clr_eol);
    }

    public static void printNewLine(Terminal terminal) {
        terminal.writer().print(System.lineSeparator());
        terminal.flush();
    }

    // Moves the cursor to the first column of the current line
    public static void goToFirstColumn(Terminal terminal) {
        writeAndFlush(terminal, carriage_return);
    }

    // Moves the cursor to the indicated column (the first column is 1)
    public static void goToColumn(Terminal terminal, int col) {
        goToFirstColumn(terminal);
        terminal.puts(column_address, col - 1);
        terminal.flush();
    }

    // Moves the cursor to the previous line
    public static void moveUp(Terminal terminal) {
        writeAndFlush(terminal, cursor_up);
    }

    public static void moveUp(Terminal terminal, int linesCount) {
        for (int counter = 0; counter < linesCount; counter++)
            moveUp(terminal);
    }

    private static void writeAndFlush(Terminal terminal, Capability capability) {
        terminal.puts(capability);
        terminal.flush();
    }
}
