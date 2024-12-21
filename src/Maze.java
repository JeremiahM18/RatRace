/**
 * Legend:
 * 'R' - Starting point (Rat)
 * 'C' - Cheese (Goal)
 * '#' - Wall
 * '.' - Open Path
 * 'X' - Tried path
 * 'o' - Correct path(breadcrumb)
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {

    private static final char TRIED = 'X';
    private static final char PATH = 'o';

    private int rows, cols;  // Dimensions of the maze
    private int startRow, startCol;  //Coordinates of the starting position 'R'
    private char[][] maze;   // 2D Array representing the maze layout

    // Constructor for loading a maze from a file with characters R, C, #, ., o, and X
    public Maze(String filename) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(filename));

        // Initialize the maze array with proper dimensions
        maze = buildArray(sc);
        rows = maze.length;
        cols = maze[0].length;

        // Identify the starting position
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if (maze[i][j] == 'R') {
                    startRow = i;
                    startCol = j;
                }
            }
        }
        sc.close();
    }

    // Marks a position in the maze as tried
    public void tryPosition(int row, int col) {
        maze[row][col] = TRIED;
    }

    // Marks a position in the maze as part of the solution path
    public void markPath(int row, int col) {
        maze[row][col] = PATH;
    }

    // Check if the current cell contains cheese
    public boolean isCheese(int row, int col) {

        return maze[row][col] == 'C';
    }

    // Check if a move to a given position is valid
    public boolean isValidPosition(int row, int col) {
        boolean result = false;
        //Check if cell is in the bounds of the matrix
        if (row >= 0 && row < maze.length && col >= 0 && col < maze[0].length) {
            char cell = maze[row][col];
            if (cell == '.' || cell == 'C' || cell == 'R') {
                result = true;
            }
//            System.out.println("Checked position [" + row + ", " + col + "] : +"
//                    + cell + " - " + (result ? "Valid" : "Invalid"));
//        } else {
//            System.out.println("Invalid position");
        }
        return result;
    }

    // Prints the maze
    public void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            System.out.println(new String(maze[i]));
        }
    }

    // Return the maze as a 2D character array
    public char[][] getMaze() {
        return maze;
    }

    // Return a string representation of the maze for easy printing
    public String toString() {
        String result = "\n";

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                result += maze[i][j] + "";
            }
            result += "\n";
        }
        return result;
    }

    // Returns the staring row index of the maze
    public int getStartRow() {
        return startRow;
    }

    // Returns the starting column index of the maze
    public int getStartCol() {
        return startCol;
    }

    // Builds the maze array by determining its dimensions from the file.
    public char[][] buildArray(Scanner scan){
        StringBuilder mazeBuild = new StringBuilder();
        int rowNum = 0;
        int colNum = 0;

        // Read the entire file once
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            colNum = line.length();
            rowNum++;
            mazeBuild.append(line).append("\n");
        }

        // Initialize maze array
        char[][] mazeArray = new char[rowNum][colNum];
        String[] lines = mazeBuild.toString().split("\n");

        // Populate the maze array
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum; j++){
                mazeArray[i][j] = lines[i].charAt(j);
            }
        }
        return mazeArray;
    }

}