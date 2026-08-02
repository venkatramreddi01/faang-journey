package trees;

/**
 * PROBLEM: Search a BST for a node with a given value, return that subtree.
 * LINK: https://leetcode.com/problems/search-in-a-binary-search-tree/
 *
 * APPROACH: Use the BST property to eliminate half the remaining tree at each step —
 * go left if target is smaller, right if bigger, exactly like binary search on an array.
 * PATTERN: Binary Search Tree (BST search)
 *
 * WHY IT WORKS: Every node guarantees left subtree < node < right subtree. So comparing
 * target against the current node tells us definitively which side it must be on —
 * no need to ever check the other side.
 *
 * EXAMPLE: tree 4-(2-(1,3),7-(_,9)), search val=3.
 * 3<4 -> go left to 2. 3>2 -> go right to 3. 3==3 -> found, return that node.
 *
 * TIME: O(h) — h = tree height (O(log n) if balanced, O(n) if skewed) | SPACE: O(h) for call stack
 */
public class SearchInBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);

        TreeNode found = searchBST(root, 3);
        System.out.println(found != null ? found.val : "not found"); // 3

        TreeNode notFound = searchBST(root, 6);
        System.out.println(notFound != null ? notFound.val : "not found"); // not found
    }

    public static TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root; // base case: found it, or hit a dead end (null)
        }

        if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }
}

/*

Rule of BST(Binary Search Tree):-

At every node:

    target < current
      ↓
    Go Left

    -----------------

    target > current
      ↓
    Go Right

    -----------------

    target == current
      ↓
    Found

That's the whole algorithm.

 */