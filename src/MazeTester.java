/* Name: Jeremiah McDonald
 # Date: 14 November 2024
 # Class: CSC 1120
 # Pledge: I have neither given nor received unauthorized aid on this program.
 # Description: This is a maze-solving program that reads a maze from a file and uses a recursive
 backtracking algorithm to find the path from a starting point('R) to the goal ('C')
 */


import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class MazeTester {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        // Predefined maze file paths stored in a map for convenience
        HashMap<String, String> mazePaths = new HashMap<>();
        mazePaths.put("mazeFile1", "C:\\Users\\cover\\IdeaProjects\\ProgramIV_RatMaze\\src\\tMaze.txt");
        mazePaths.put("mazeFile", "C:\\Users\\cover\\IdeaProjects\\ProgramIV_RatMaze\\src\\ratMaze.txt");
        mazePaths.put("aMaze", "C:\\Users\\cover\\IdeaProjects\\ProgramIV_RatMaze\\src\\aMaze.txt");
        mazePaths.put("maze1", "C:\\Users\\cover\\IdeaProjects\\ProgramIV_RatMaze\\maze1.txt");
        mazePaths.put("maze2", "C:\\Users\\cover\\IdeaProjects\\ProgramIV_RatMaze\\maze2.txt");
        mazePaths.put("maze3", "C:\\Users\\cover\\IdeaProjects\\ProgramIV_RatMaze\\maze3.txt");
        mazePaths.put("maze4", "C:\\Users\\cover\\IdeaProjects\\ProgramIV_RatMaze\\maze4.txt");

        System.out.println("Enter the path for the maze for one of the keywords: ");
        System.out.println(mazePaths.keySet());
        String input = scan.nextLine();

        // Retrieve the maze path from the map or use the input directly if not a keyword
        String path = mazePaths.getOrDefault(input, input);

        try {
            Maze maze = new Maze(path);
            maze.printMaze();

            int[] callCount = {0};  //Initialize the call counter
            MazeSolver solver = new MazeSolver(maze);

            // Attempt to solve the maze
            if (solver.solve(maze.getStartRow(), maze.getStartCol(), callCount)) {
                System.out.println("Maze solved!");
                System.out.println("Total calls made to solve: " + callCount[0]);

                // Count breadcrumbs for the path length
                int breadPath = countBread(maze);
                System.out.println("There are " + breadPath + " breadcrumbs.");

                System.out.println("The solution to the maze:");
                maze.printMaze();
            } else {
                System.out.println("No solution found for the maze.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found." + e.getMessage());
        } finally {
            scan.close();
        }
    }

    // Method to count breadcrumbs ('o') in the maze
    private static int countBread(Maze maze) {
        int count = 0;
        for(char[] row : maze.getMaze()){ // Outer loop for rows
            for(char c : row) {           // Inner loop for columns in each row
                if (c == 'o') count++;    // Increment count for each breadcrumb
            }
        }
        return count;
    }
}