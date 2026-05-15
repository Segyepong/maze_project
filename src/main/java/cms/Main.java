package cms;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Main <input.csv> <output.txt>");
            return;
        }

        String inputPath  = args[0];
        String outputPath = args[1];

        GridReader reader = new GridReader();
        Maze maze = reader.readMaze(inputPath);
        if (maze == null) {
            return; 
        }

        boolean solved = maze.solve();

        if (!solved) {
            System.out.println("No solution exists for this maze.");
            return; 
        }

        GridWriter writer = new GridWriter();
        writer.writeMaze(maze, outputPath);
    }
}
