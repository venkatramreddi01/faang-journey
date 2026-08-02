package trees;

import java.util.*;

/**
 * PROBLEM: Return a binary tree's node values grouped level by level.
 * LINK: https://leetcode.com/problems/binary-tree-level-order-traversal/
 *
 * APPROACH: BFS using a Queue. At the start of each round, snapshot queue.size() —
 * that's exactly how many nodes belong to the current level, even as children get
 * added to the queue during processing.
 * PATTERN: Tree BFS (level order)
 *
 * WHY IT WORKS: Queue is FIFO, so nodes are processed in the exact order discovered
 * (left to right, level by level). Snapshotting size() before the round starts
 * separates "this level's nodes" from "next level's nodes" cleanly.
 *
 * EXAMPLE: tree [3,9,20,null,null,15,7] -> levels: [3] | [9,20] | [15,7]
 *
 * TIME: O(n) — every node visited once | SPACE: O(n) — queue + result storage
 */
public class BinaryTreeLevelOrderTraversal {

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

        System.out.println(levelOrder(root)); // [[3], [9, 20], [15, 7]]
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // snapshot BEFORE processing this level
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            result.add(currentLevel);
        }

        return result;
    }
}