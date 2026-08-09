package graphs;

/**
 * PROBLEM: Flood fill from a starting pixel, recoloring all connected pixels that
 * share the starting pixel's original color.
 * LINK: https://leetcode.com/problems/flood-fill/
 *
 * APPROACH: DFS from the starting pixel. Recolor the current pixel, then recurse
 * into all 4 neighbors — but only if each neighbor is in-bounds AND still holds
 * the ORIGINAL color (not already recolored, not a different color entirely).
 * PATTERN: Graph DFS (grid traversal)
 *
 * WHY IT WORKS: Checking "still original color" before recursing prevents both
 * infinite loops (re-entering already-recolored cells) and incorrectly spreading
 * into cells that were never part of the connected same-colored region.
 *
 * EXAMPLE: image[1][1]=1 (orig color), flood from (1,1) with newColor=2.
 * Recolor (1,1)->2. Check (1,0): still 1 -> recolor, recurse. Check (0,1): still 1
 * -> recolor, recurse. Eventually a neighbor is 0 (different color) -> stop there.
 *
 * TIME: O(rows*cols) — each cell visited at most once | SPACE: O(rows*cols) worst case (call stack)
 */
public class FloodFill {

    public static void main(String[] args) {
        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        int[][] result = floodFill(image, 1, 1, 2);
        for (int[] row : result) {
            System.out.println(java.util.Arrays.toString(row));
        }
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];
        if (originalColor != newColor) { // avoids infinite recursion if newColor == originalColor
            dfs(image, sr, sc, originalColor, newColor);
        }
        return image;
    }

    private static void dfs(int[][] image, int r, int c, int originalColor, int newColor) {
        boolean outOfBounds = r < 0 || r >= image.length || c < 0 || c >= image[0].length;
        if (outOfBounds || image[r][c] != originalColor) {
            return; // your exact condition: out of bounds OR color doesn't match original
        }

        image[r][c] = newColor; // recolor this pixel

        dfs(image, r + 1, c, originalColor, newColor); // down
        dfs(image, r - 1, c, originalColor, newColor); // up
        dfs(image, r, c + 1, originalColor, newColor); // right
        dfs(image, r, c - 1, originalColor, newColor); // left
    }
}