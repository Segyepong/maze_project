package cms;

public class Space {

    public static final int MAX_ADJACENT = 4; 

    private char type;         
    private boolean onPath;    
    private boolean visited;   


    private int row;
    private int col;


    private Space[] adjacent;
    private int adjacentCount;

    public Space(char type, int row, int col) {
        this.type = type;
        this.row = row;
        this.col = col;
        this.onPath = false;
        this.visited = false;
        this.adjacent = new Space[MAX_ADJACENT];
        this.adjacentCount = 0;
    }


    public void addAdjacent(Space neighbor) {
        if (adjacentCount < MAX_ADJACENT) {
            adjacent[adjacentCount++] = neighbor;
        }
    }


    public Space[] getAdjacent() {
        return adjacent;
    }

    public int getAdjacentCount() {
        return adjacentCount;
    }

    public char getType()       { return type; }
    public boolean isOnPath()   { return onPath; }
    public void setOnPath(boolean b) { onPath = b; }
    public boolean isVisited()  { return visited; }
    public void setVisited(boolean b) { visited = b; }
    public int getRow()         { return row; }
    public int getCol()         { return col; }

    public boolean isBlocked()  { return type == 'X'; }
    public boolean isStart()    { return type == 'S'; }
    public boolean isEnd()      { return type == 'E'; }
}
