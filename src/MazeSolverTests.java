import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MazeSolverTests {

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

    int row = 8;
    int col = 1;
    MazeSolver solver = new MazeSolver(testMaze, row, col, "", false);


    @Test
    void checkForSpace() {
        assertEquals(true, solver.checkForSpace(2, 1));
        assertEquals(false, solver.checkForSpace(2 , 2));
    }

    @Test
    void checkForCheckpoint() {
        assertEquals(true, solver.checkForCheckpoint(1, 3));
        assertEquals(false, solver.checkForCheckpoint(2, 2));
    }

    @Test
    void checkForCompletion() {
        assertEquals(false, solver.checkForCompletion());
    }

    @Test
    void solveMaze() {
        solver.solveMaze();
        assertEquals(true, solver.checkForCompletion());

    }

    @Test
    void placeMarker() {
        solver.placeMarker();
        assertEquals('X', testMaze[row][col]);
    }

}