package cms;

import java.io.File;
import java.io.PrintWriter;


public class GridWriter {


    public void writeMaze(Maze maze, String outputPath) {
        int rows = maze.getRows();
        int cols = maze.getCols();

        char[][] display = new char[rows][cols];

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                display[r][c] = '?';

        Space[] spaces = maze.getSpaces();
        int count = maze.getSpaceCount();

        for (int i = 0; i < count; i++) {
            Space s = spaces[i];
            int r = s.getRow();
            int c = s.getCol();

            if (s.isBlocked()) {
                display[r][c] = 'X';
            } else if (s.isStart()) {
                display[r][c] = 'S';
            } else if (s.isEnd()) {
                display[r][c] = 'E';
            } else if (s.isOnPath()) {
                display[r][c] = '*';
            } else {
                display[r][c] = 'O';
            }
        }

        printGrid(display, rows, cols, System.out);

        try {
            PrintWriter writer = new PrintWriter(new File(outputPath));
            printGrid(display, rows, cols, writer);
            writer.close();
            System.out.println("\nSolution written to: " + outputPath);
        } catch (Exception e) {
            System.out.println("Error writing output file: " + e.getMessage());
        }
    }

    private void printGrid(char[][] display, int rows, int cols, java.io.PrintStream out) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                out.print(display[r][c]);
            }
            out.println();
        }
    }

    private void printGrid(char[][] display, int rows, int cols, PrintWriter out) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                out.print(display[r][c]);
            }
            out.println();
        }
    }
}
