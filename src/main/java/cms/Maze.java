package cms;

public class Maze {

    public static final int MAX_SPACES = 2500; 

    private Space[] spaces;
    private int spaceCount;
    private Space startSpace;
    private Space endSpace;
    private int rows;
    private int cols;

    public Maze() {
        spaces = new Space[MAX_SPACES];
        spaceCount = 0;
        startSpace = null;
        endSpace = null;
    }


    public void addSpace(Space s) {
        if (spaceCount < MAX_SPACES) {
            spaces[spaceCount++] = s;
            if (s.isStart()) startSpace = s;
            if (s.isEnd())   endSpace   = s;
        }
    }

    public Space getStartSpace() { return startSpace; }
    public Space getEndSpace()   { return endSpace; }
    public Space[] getSpaces()   { return spaces; }
    public int getSpaceCount()   { return spaceCount; }


    public boolean solve() {
        if (startSpace == null || endSpace == null) {
            System.out.println("Error: maze has no start or end.");
            return false;
        }
        return solveFrom(startSpace);
    }


    private boolean solveFrom(Space current) {
        if (current.isEnd()) {
            current.setOnPath(true);
            return true;
        }

        if (current.isVisited()) {
            return false;
        }

        if (current.isBlocked()) {
            return false;
        }

        current.setVisited(true);
        current.setOnPath(true);

        for (int i = 0; i < current.getAdjacentCount(); i++) {
            Space neighbor = current.getAdjacent()[i];
            if (neighbor != null && solveFrom(neighbor)) {
                return true; 
            }
        }

        current.setOnPath(false);
        return false;
    }

    public void setDimensions(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }
    public int getRows() { return rows; }
    public int getCols() { return cols; }
}