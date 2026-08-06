package trie;

/**
 * PROBLEM: Design a data structure supporting addWord and search, where search can
 * use '.' as a wildcard matching any single letter.
 * LINK: https://leetcode.com/problems/design-add-and-search-words-data-structure/
 *
 * APPROACH: Same Trie as Problem 1 for storage. Search uses DFS: a normal letter
 * follows one specific child path; '.' tries EVERY non-null child recursively,
 * succeeding if any single branch leads to a full match.
 * PATTERN: Trie + DFS (backtracking-style search)
 *
 * WHY IT WORKS: '.' isn't one path, it's "any of up to 26 paths." DFS explores each
 * possibility one at a time; if any recursive branch returns true, we don't need to
 * check the rest — we've already found a match.
 *
 * EXAMPLE: trie has "bad","dad","mad". search(".ad"): '.' tries children b,d,m.
 * Branch 'b' -> matches "ad" from there -> found "bad" -> returns true immediately.
 *
 * TIME: O(26^m) worst case (m = number of dots) | SPACE: O(L) for recursion depth
 */
public class WordDictionary {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord = false;
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int index, TrieNode node) {
        if (node == null) {
            return false; // path doesn't exist, dead end
        }
        if (index == word.length()) {
            return node.isEndOfWord; // walked the whole word, check if it actually ends here
        }

        char c = word.charAt(index);

        if (c == '.') {
            // wildcard: try EVERY possible child, succeed if ANY branch works
            for (TrieNode child : node.children) {
                if (dfs(word, index + 1, child)) {
                    return true;
                }
            }
            return false; // none of the 26 branches worked
        } else {
            // normal letter: follow exactly one specific path
            return dfs(word, index + 1, node.children[c - 'a']);
        }
    }

    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();
        wd.addWord("bad");
        wd.addWord("dad");
        wd.addWord("mad");

        System.out.println(wd.search("pad"));  // false
        System.out.println(wd.search("bad"));  // true
        System.out.println(wd.search(".ad"));  // true
        System.out.println(wd.search("b.."));  // true
    }
}