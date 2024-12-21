public class MazeSolver {
    private Maze maze;
    private boolean debug;

    // Constructor for the MazeSolver Class
    public MazeSolver(Maze m) {
        if(m == null || m.getMaze().length == 0 || m.getMaze()[0].length == 0){
            throw new IllegalArgumentException("Maze is null or empty");
        }
        maze = m;
        debug = false; // Debug mode off by default
    }

    // Enables or disables debug output
    public void setDebug(boolean enable){
        debug = enable;
    }

    // Recursive method to solve the maze
    public boolean solve(int row, int col, int[] callCount) {
        callCount[0]++;   // Increment call counter

        // Check if the current position is invalid
        if(!maze.isValidPosition(row, col)) {
            if(debug) {
                System.out.println("The rat checked position [" + row + "], [" + col + "] - Invalid");
            }
            return false;
        }

        if(debug){
            System.out.println("The rat checked position [" + row + "], [" + col + "] - Valid");
        }

        //Base case: Check if the current position is the cheese location
        if (maze.isCheese(row, col)) {
            maze.markPath(row, col);
            System.out.println("The rat found the cheese at [" + row + "] [" + col + "] - Cheese found!");
            return true;
        }

        // Mark this cell as TRIED
        maze.tryPosition(row, col);

        // Explore all possible directions
        if (solve(row - 1, col, callCount) ||   // Move Up
                solve(row + 1, col, callCount) ||   // Move Down
                solve(row, col + 1, callCount) ||    // Move Right
                solve(row, col - 1, callCount)) {    // Move Left

            maze.markPath(row, col); //Mark part of the solution path
            System.out.println("The rats position in the solution step: [" + row + "], [" + col + "]");
            return true;
        }
        return false;
    }
}
