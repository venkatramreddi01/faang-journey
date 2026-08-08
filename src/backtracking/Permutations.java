package backtracking;

import java.util.*;

/**
 * PROBLEM: Return all possible permutations (orderings) of a distinct-integer array.
 * LINK: https://leetcode.com/problems/permutations/
 *
 * APPROACH: At each step, try placing EVERY number not yet used in the current path.
 * Track "used" numbers with a Set for O(1) lookup. Base case: current path length
 * equals nums.length — every number has been placed.
 * PATTERN: Backtracking (permutation-style, try-all-unused)
 *
 * WHY IT WORKS: Unlike Subsets (fixed order, binary include/skip), a permutation
 * needs every number exactly once in SOME order — so at each step we try all
 * remaining choices, not just one fixed "next" element.
 *
 * EXAMPLE: nums=[1,2,3]. Path [1,2] with used={1,2} -> only 3 is available -> [1,2,3]
 * recorded at base case (length 3 == nums.length).
 *
 * TIME: O(n! * n) | SPACE: O(n) for recursion depth + used set
 */
public class Permutations {

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), new HashSet<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, List<Integer> current, Set<Integer> used, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current)); // base case: path is a complete permutation
            return;
        }

        for (int num : nums) {
            if (used.contains(num)) {
                continue; // skip anything already placed in this path
            }

            current.add(num);
            used.add(num);

            backtrack(nums, current, used, result);

            current.remove(current.size() - 1); // backtrack: undo the placement
            used.remove(num);                     // backtrack: mark it available again
        }
    }
}