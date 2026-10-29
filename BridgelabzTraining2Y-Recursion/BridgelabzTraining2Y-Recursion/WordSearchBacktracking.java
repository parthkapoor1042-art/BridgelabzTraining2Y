import java.util.*;

/**
 * Assignment 2: Find a target word in a grid.
 * Moves can be horizontal, vertical, or diagonal. A cell cannot be reused
 * within the same word path.
 */
public class WordSearchBacktracking {
    private static final char[][] GRID = {
        {'C', 'A', 'T', 'S'},
        {'O', 'R', 'E', 'A'},
        {'D', 'E', 'A', 'M'},
        {'E', 'L', 'L', 'S'}
    };

    private static final int[] DR = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] DC = {-1, 0, 1, -1, 1, -1, 0, 1};

    static boolean exists(char[][] grid, String word) {
        if (word == null || word.isEmpty()) return true;
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (search(grid, word, 0, r, c, visited)) return true;
            }
        }
        return false;
    }

    private static boolean search(char[][] grid, String word, int index,
                                  int r, int c, boolean[][] visited) {
        if (index == word.length()) return true;
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length)
            return false;
        if (visited[r][c] || grid[r][c] != word.charAt(index))
            return false;

        visited[r][c] = true; // Make the choice

        for (int k = 0; k < 8; k++) {
            if (search(grid, word, index + 1, r + DR[k], c + DC[k], visited)) {
                visited[r][c] = false; // Restore state before returning
                return true;
            }
        }

        visited[r][c] = false; // Backtrack: undo the choice
        return false;
    }

    static void findAll(char[][] grid, String word, int r, int c, int index,
                        boolean[][] visited, List<String> path,
                        List<List<String>> solutions) {
        if (index == word.length()) {
            solutions.add(new ArrayList<>(path));
            return;
        }
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length
                || visited[r][c] || grid[r][c] != word.charAt(index)) {
            return;
        }

        visited[r][c] = true;
        path.add("(" + r + "," + c + ")=" + grid[r][c]);

        if (index == word.length() - 1) {
            solutions.add(new ArrayList<>(path));
        } else {
            for (int k = 0; k < 8; k++) {
                findAll(grid, word, r + DR[k], c + DC[k], index + 1,
                        visited, path, solutions);
            }
        }

        path.remove(path.size() - 1);
        visited[r][c] = false;
    }

    public static void main(String[] args) {
        String target = "DREAM";
        System.out.println("Grid:");
        for (char[] row : GRID) System.out.println(Arrays.toString(row));
        System.out.println("Can form \"" + target + "\"? " + exists(GRID, target));

        List<List<String>> solutions = new ArrayList<>();
        for (int r = 0; r < GRID.length; r++) {
            for (int c = 0; c < GRID[0].length; c++) {
                findAll(GRID, target, r, c, 0,
                        new boolean[GRID.length][GRID[0].length],
                        new ArrayList<>(), solutions);
            }
        }
        System.out.println("Number of paths found: " + solutions.size());
        for (List<String> path : solutions) System.out.println(path);
    }
}
