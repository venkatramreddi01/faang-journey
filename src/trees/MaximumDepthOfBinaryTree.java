package trees;

/**
 * PROBLEM: Return the maximum depth (longest root-to-leaf path, counting nodes) of a binary tree.
 * LINK: https://leetcode.com/problems/maximum-depth-of-binary-tree/
 *
 * APPROACH: Recursively ask the same question of the left and right subtrees, take
 * the deeper one, add 1 for the current node itself.
 * PATTERN: Tree DFS (recursive)
 *
 * WHY IT WORKS: A node's depth = 1 (itself) + the deeper of its two children's depths.
 * Base case: an empty tree (null) has depth 0 — nothing to count.
 *
 * EXAMPLE: root=3, left=9, right=20(left=15,right=7)
 *          maxDepth(9)=1, maxDepth(20)=1+max(1,1)=2, maxDepth(3)=1+max(1,2)=3
 *
 * TIME: O(n) — visits every node once | SPACE: O(h) — h = tree height, for the call stack
 */
public class MaximumDepthOfBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(maxDepth(root)); // 3
        System.out.println(maxDepth(null)); // 0
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0; // base case: empty tree has no depth
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }
}