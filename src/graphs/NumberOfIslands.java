package graphs;

/**
 * PROBLEM: Count the number of islands (connected groups of '1's) in a 2D grid.
 * LINK: https://leetcode.com/problems/number-of-islands/
 *
 * APPROACH: Scan every cell. On finding an unvisited '1', increment the island count
 * and DFS flood-fill outward, sinking each visited land cell to '0' as we go — reusing
 * the exact Flood Fill mechanism from Problem 1, just without needing a new color.
 * PATTERN: Graph DFS (grid traversal, connected components)
 *
 * WHY IT WORKS: Sinking visited land to '0' means the outer scan naturally skips
 * already-counted islands later — no separate visited[][] array needed.
 *
 * EXAMPLE: grid has 2 separate blocks of '1's. First '1' found -> count=1, DFS sinks
 * that whole connected block to '0'. Scan continues, finds next unvisited '1' -> count=2.
 *
 * TIME: O(rows*cols) — each cell visited at most once overall | SPACE: O(rows*cols) worst case
 */
public class NumberOfIslands {

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println(numIslands(grid)); // 3
    }

    public static int numIslands(char[][] grid) {
        int count = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    count++;           // found a fresh, unvisited island
                    sink(grid, r, c);  // sink this whole connected region so we never recount it
                }
            }
        }

        return count;
    }

    private static void sink(char[][] grid, int r, int c) {
        boolean outOfBounds = r < 0 || r >= grid.length || c < 0 || c >= grid[0].length;
        if (outOfBounds || grid[r][c] != '1') {
            return; // same two-part stopping condition as Flood Fill
        }

        grid[r][c] = '0'; // sink this cell — marks it visited by turning it to water

        sink(grid, r + 1, c);
        sink(grid, r - 1, c);
        sink(grid, r, c + 1);
        sink(grid, r, c - 1);
    }
}