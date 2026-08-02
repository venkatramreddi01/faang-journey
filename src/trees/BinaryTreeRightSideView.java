package trees;

import java.util.*;

/**
 * PROBLEM: Return the values visible when viewing a binary tree from the right side,
 * top to bottom.
 * LINK: https://leetcode.com/problems/binary-tree-right-side-view/
 *
 * APPROACH: BFS level order, same as Level Order Traversal. But instead of collecting
 * every node in a level, only keep the LAST node processed in each round — since
 * children are added left-to-right, the last node processed in a level is always
 * the rightmost one.
 * PATTERN: Tree BFS (level order, keep last per level)
 *
 * WHY IT WORKS: Because we always offer left child before right child, the queue
 * (and thus our processing order) stays strictly left-to-right within a level.
 * The node at loop index (levelSize - 1) is therefore always the rightmost.
 *
 * EXAMPLE: tree 1-(2-(null,5), 3-(null,4)). Level0:[1]->1. Level1:[2,3]->3. Level2:[5,4]->4.
 * Result: [1,3,4]
 *
 * TIME: O(n) | SPACE: O(n)
 */
public class BinaryTreeRightSideView {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        System.out.println(rightSideView(root)); // [1, 3, 4]
    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                if (i == levelSize - 1) {
                    result.add(node.val); // last node processed this round = rightmost
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return result;
    }
}