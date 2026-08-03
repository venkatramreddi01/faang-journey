package trees;

/**
 * PROBLEM: Determine if a binary tree is a valid BST (left<node<right holds globally,
 * not just locally between parent and child).
 * LINK: https://leetcode.com/problems/validate-binary-search-tree/
 *
 * APPROACH: Pass down a valid (min, max) range as we recurse. Going left tightens the
 * upper bound to the current node's value; going right tightens the lower bound.
 * PATTERN: BST (range validation)
 *
 * WHY IT WORKS: Checking only against the immediate parent misses violations against
 * grandparents/further ancestors. Carrying a shrinking range forward encodes ALL
 * ancestor constraints, not just the nearest one.
 *
 * EXAMPLE: tree 5-(3,8-(4,9)). Node 4 inherits range (5,+inf) from being right of 5,
 * then range tightens to (5,8) going left of 8. 4 is NOT in (5,8) -> invalid.
 *
 * TIME: O(n) — visits every node once | SPACE: O(h) for the call stack
 */
public class ValidateBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        TreeNode valid = new TreeNode(5);
        valid.left = new TreeNode(3);
        valid.right = new TreeNode(8);
        valid.right.left = new TreeNode(6);
        valid.right.right = new TreeNode(9);
        System.out.println(isValidBST(valid)); // true

        TreeNode invalid = new TreeNode(5);
        invalid.left = new TreeNode(3);
        invalid.right = new TreeNode(8);
        invalid.right.left = new TreeNode(4); // violates: must be > 5
        invalid.right.right = new TreeNode(9);
        System.out.println(isValidBST(invalid)); // false
    }

    public static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, long min, long max) {
        if (node == null) {
            return true; // empty tree/subtree is trivially valid
        }

        if (node.val <= min || node.val >= max) {
            return false; // out of the allowed range
        }

        return validate(node.left, min, node.val) &&
                validate(node.right, node.val, max);
    }
}