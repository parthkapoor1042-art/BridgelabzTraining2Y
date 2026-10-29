import java.util.*;

/**
 * Assignment 3: Optimized N-Queens using a 1D board and diagonal arrays.
 * board[row] = column containing the queen in that row; -1 means unassigned.
 */
public class NQueensOptimized {
    private final int n;
    private final int[] board;
    private final boolean[] columns;
    private final boolean[] diagDown; // row - col + (n - 1)
    private final boolean[] diagUp;   // row + col
    private long exploredStates;
    private boolean firstOnly;

    public NQueensOptimized(int n) {
        if (n < 1) throw new IllegalArgumentException("N must be at least 1");
        this.n = n;
        board = new int[n];
        Arrays.fill(board, -1);
        columns = new boolean[n];
        diagDown = new boolean[2 * n - 1];
        diagUp = new boolean[2 * n - 1];
    }

    public List<int[]> solveAll() {
        firstOnly = false;
        List<int[]> solutions = new ArrayList<>();
        search(0, solutions, null);
        return solutions;
    }

    public int[] solveOne() {
        firstOnly = true;
        List<int[]> solutions = new ArrayList<>(1);
        search(0, solutions, null);
        return solutions.isEmpty() ? null : solutions.get(0);
    }

    public long getExploredStates() {
        return exploredStates;
    }

    private boolean search(int row, List<int[]> solutions, boolean[][] forbidden) {
        exploredStates++;
        if (row == n) {
            solutions.add(board.clone());
            return firstOnly;
        }

        for (int col = 0; col < n; col++) {
            if (forbidden != null && forbidden[row][col]) continue;

            int d1 = row - col + n - 1;
            int d2 = row + col;
            if (columns[col] || diagDown[d1] || diagUp[d2]) continue;

            // Make choice: place queen and mark its column/diagonals.
            board[row] = col;
            columns[col] = diagDown[d1] = diagUp[d2] = true;

            if (search(row + 1, solutions, forbidden)) return true;

            // Undo choice: restore the state before trying the next column.
            board[row] = -1;
            columns[col] = diagDown[d1] = diagUp[d2] = false;
        }
        return false;
    }

    public List<int[]> solveWithForbidden(boolean[][] forbidden) {
        if (forbidden.length != n) throw new IllegalArgumentException("Wrong board size");
        for (boolean[] row : forbidden) {
            if (row.length != n) throw new IllegalArgumentException("Wrong board size");
        }
        firstOnly = false;
        List<int[]> solutions = new ArrayList<>();
        search(0, solutions, forbidden);
        return solutions;
    }

    private static void printBoard(int[] solution) {
        if (solution == null) {
            System.out.println("No solution found.");
            return;
        }
        for (int row = 0; row < solution.length; row++) {
            for (int col = 0; col < solution.length; col++) {
                System.out.print(solution[row] == col ? "Q " : ". ");
            }
            System.out.println();
        }
        System.out.println("Column positions by row: " + Arrays.toString(solution));
    }

    public static void main(String[] args) {
        NQueensOptimized solver = new NQueensOptimized(4);
        int[] one = solver.solveOne();
        System.out.println("One solution for N=4:");
        printBoard(one);

        NQueensOptimized allSolver = new NQueensOptimized(4);
        List<int[]> all = allSolver.solveAll();
        System.out.println("Total solutions for N=4: " + all.size());
        for (int[] solution : all) printBoard(solution);

        NQueensOptimized eightSolver = new NQueensOptimized(8);
        System.out.println("Total solutions for N=8: " + eightSolver.solveAll().size());
    }
}
