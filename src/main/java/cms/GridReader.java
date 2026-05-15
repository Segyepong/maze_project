package cms;

import java.io.File;
import java.util.Scanner;


public class GridReader {

    private static final int MAX_ROWS = 50;
    private static final int MAX_COLS = 50;


    public Maze readMaze(String filePath) {
        String[] lines = new String[MAX_ROWS];
        int rowCount = 0;

        try {
            File inputFile = new File(filePath);
            Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine() && rowCount < MAX_ROWS) {
                lines[rowCount++] = scanner.nextLine().trim();
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }

        if (rowCount == 0) {
            System.out.println("Error: file is empty.");
            return null;
        }

        Space[][] grid = new Space[MAX_ROWS][MAX_COLS];
        int colCount = -1;
        int startCount = 0;
        int endCount = 0;

        for (int r = 0; r < rowCount; r++) {
            String[] tokens = lines[r].split(",");

            if (colCount == -1) {
                colCount = tokens.length;
            } else if (tokens.length != colCount) {
                System.out.println("Error: rows have different widths.");
                return null;
            }

            for (int c = 0; c < colCount; c++) {
                char type = tokens[c].trim().charAt(0);

                if (type != 'X' && type != 'O' && type != 'S' && type != 'E') {
                    System.out.println("Error: unknown character '" + type
                            + "' at row " + r + ", col " + c);
                    return null;
                }

                if (type == 'S') startCount++;
                if (type == 'E') endCount++;

                grid[r][c] = new Space(type, r, c);
            }
        }

        if (startCount != 1) {
            System.out.println("Error: maze must have exactly one S (found " + startCount + ").");
            return null;
        }
        if (endCount != 1) {
            System.out.println("Error: maze must have exactly one E (found " + endCount + ").");
            return null;
        }

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                Space s = grid[r][c];
                if (s.isBlocked()) continue; 

                if (r > 0           && !grid[r-1][c].isBlocked()) s.addAdjacent(grid[r-1][c]); 
                if (r < rowCount-1  && !grid[r+1][c].isBlocked()) s.addAdjacent(grid[r+1][c]); 
                if (c > 0           && !grid[r][c-1].isBlocked()) s.addAdjacent(grid[r][c-1]); 
                if (c < colCount-1  && !grid[r][c+1].isBlocked()) s.addAdjacent(grid[r][c+1]); 
            }
        }

        Maze maze = new Maze();
        maze.setDimensions(rowCount, colCount);
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                maze.addSpace(grid[r][c]);
            }
        }

        return maze;
    }
}