/*
Maze Solver:
    . = Open Pathway
    * = Wall
    F = Finish
    C = Checkpoint
 */

import java.util.Arrays;

public class MazeSolver {
    private char[][] grid;
    private String pos;
    private int row;
    private int col;
    private boolean unsolvable;

    MazeSolver(char[][] mazeGrid, int xVal, int yVal, String currentPos, boolean noSolution) {
        grid = mazeGrid;
        row = xVal;
        col = yVal;
        pos = currentPos;
        unsolvable = noSolution;
    }

    public static void main(String[] args) {
        char[][] testMaze = {
                {'*', '*', '*', 'F', '*', '*', '*', '*', '*', '*'},
                {'*', '.', '*', 'C', '.', '.', '*', '.', '*', '*'},
                {'*', '.', '*', '.', '*', '.', '.', '.', '.', '*'},
                {'*', '.', '*', '.', '*', '.', '*', '.', '*', '*'},
                {'*', '.', '.', '*', '*', '*', '*', '.', '*', '*'},
                {'*', '.', '.', '.', '.', 'C', '.', '.', '.', '*'},
                {'*', '*', '*', '.', '*', '*', '*', '*', '.', '*'},
                {'*', '*', '.', '.', 'C', 'C', '.', '*', '*', '*'},
                {'*', '.', '.', '*', '*', '*', '.', '.', '.', '*'},
                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*'}};

        String startPath = "";
        int startRow = 8;
        int startColumn = 1;

        MazeSolver solver = new MazeSolver(testMaze, startRow, startColumn, startPath, false);

        solver.placeMarker();
        solver.solveMaze();

        if (solver.unsolvable) { System.out.println("Cannot Be Solved"); }
        else { System.out.println("Checkpoints Crossed: " + checkpointCount); }

        solver.printMaze();
    }


    boolean checkForSpace(int x, int y) { return grid[x][y] == '.'; }

    boolean checkForCheckpoint(int x, int y) { return grid[x][y] == 'C'; }

    void placeMarker() { grid[row][col] = 'X'; }

    void placeMinus() { grid[row][col] = '-'; }

    static int checkpointCount = 0;
    void solveMaze() {

        if (checkForCompletion()) { return; }

        // Checks 1 spot to the right
        if (col + 1 < grid[row].length && checkForSpace(row, col + 1)) {
            col++;
            placeMarker();
            pos += 'R'; // Right
            solveMaze();
        } else if (col + 1 < grid[row].length && checkForCheckpoint(row, col + 1)) {
            checkpointCount++;
            col++;
            placeMarker();
            pos += 'R';
            solveMaze();
        }

        // Checks 1 spot to the left
        else if (col - 1 >= 0 && checkForSpace(row, col - 1)) {
            col--;
            placeMarker();
            pos += 'L'; // Left
            solveMaze();
        } else if (col - 1 >= 0 && checkForCheckpoint(row, col - 1)) {
            checkpointCount++;
            col--;
            placeMarker();
            pos += 'L';
            solveMaze();
        }

        // Checks 1 spot below
        else if (row + 1 < grid.length && checkForSpace(row + 1, col)) {
            row++;
            placeMarker();
            pos += 'D'; // Down
            solveMaze();
        } else if (row + 1 < grid.length && checkForCheckpoint(row + 1, col)) {
            checkpointCount++;
            row++;
            placeMarker();
            pos += 'D';
            solveMaze();
        }

        // Checks 1 spot above
        else if (row - 1 >= 0 && checkForSpace(row - 1, col)) {
            row--;
            placeMarker();
            pos += 'U'; // Up
            solveMaze();
        } else if (row - 1 >= 0 && checkForCheckpoint(row - 1, col)) {
            checkpointCount++;
            row--;
            placeMarker();
            pos += 'U';
            solveMaze();
        }

        // Check unsolvable or backtrack
        else {
            if (pos.length() == 0) {
                unsolvable = true;
                return;
            } else {
                placeMinus();
                backTrack();
            }
        }
    }

    boolean checkForCompletion() {
        // Checks for finishing point
        return ((col + 1 < grid[row].length && grid[row][col + 1] == 'F') ||
                (col - 1 >= 0  && grid[row][col - 1] == 'F') ||
                (row + 1 < grid[row].length && grid[row + 1][col] == 'F') ||
                (row -1 >= 0 && grid[row -1][col] == 'F'));
    }

    void backTrack() {
        // Backtracks if location was already visited
        if (pos.length() > 0) {
            placeMinus();
            switch (pos.charAt(pos.length() - 1)) {
                case 'R': // Right
                    col--;
                    break;
                case 'L': // Left
                    col++;
                    break;
                case 'D': // Down
                    row--;
                    break;
                case 'U': // Up
                    row++;
                    break;
            }
            pos = pos.substring(0, pos.length()-1);
            solveMaze();
        }
    }

    void printMaze() {
        // Prints completed maze
        for (int i = 0; i < grid.length; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
    }
}
