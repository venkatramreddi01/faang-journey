package backtracking;

import java.util.*;

/**
 * PROBLEM: Return all possible subsets (the power set) of a distinct-integer array.
 * LINK: https://leetcode.com/problems/subsets/
 *
 * APPROACH: At each index, branch into two choices: include nums[index], or skip it.
 * Record the subset ONLY at the base case (index == nums.length) — that's when a full
 * set of include/skip decisions has been made, corresponding to exactly one unique subset.
 * PATTERN: Backtracking (include/exclude)
 *
 * WHY IT WORKS: This binary tree has exactly 2^n leaves (one per possible combination
 * of decisions), and each leaf = one unique subset. Recording earlier than the leaf
 * would double-count subsets reached via different decision paths.
 *
 * EXAMPLE: nums=[1,2]. 4 leaves reached: [1,2], [1], [2], [] — exactly 2^2=4, no dupes.
 *
 * TIME: O(2^n) | SPACE: O(n) for recursion depth
 */
public class Subsets {

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<>(current)); // record ONLY here, at the base case
            return;
        }

        current.add(nums[index]);
        backtrack(nums, index + 1, current, result);
        current.remove(current.size() - 1);

        backtrack(nums, index + 1, current, result);
    }
}