package trie;

/**
 * PROBLEM: Implement a Trie (prefix tree) supporting insert, exact search, and
 * prefix search.
 * LINK: https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 * APPROACH: Each node holds an array of 26 children (one per lowercase letter) and
 * a boolean flag marking "a word ends here." Insert/search walk letter by letter,
 * following or creating child nodes as needed.
 * PATTERN: Trie (Prefix Tree)
 *
 * WHY IT WORKS: Words sharing a prefix share the same path down the tree. Checking
 * "does this prefix exist" just means walking the path — no need to scan every word,
 * unlike a HashSet of strings.
 *
 * EXAMPLE: insert("cat") builds path c->a->t, marks t as end-of-word.
 * search("cat")=true (full path + end flag). search("ca")=false (path exists, no end flag).
 * startsWith("ca")=true (path exists, end flag doesn't matter).
 *
 * TIME: O(L) per operation, L = word/prefix length | SPACE: O(total characters stored)
 */
public class Trie {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord = false;
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
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
        TrieNode node = findNode(word);
        return node != null && node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }

    private TrieNode findNode(String s) {
        TrieNode current = root;
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                return null; // path breaks — string doesn't exist in the trie
            }
            current = current.children[index];
        }
        return current; // walked the whole string successfully
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // true
        System.out.println(trie.search("app"));     // false (not marked as end-of-word)
        System.out.println(trie.startsWith("app")); // true (path exists)
        trie.insert("app");
        System.out.println(trie.search("app"));     // true (now it's been inserted too)
    }
}

/*

What each part is doing:
------------------------
* children[c - 'a'] — same index trick from Valid Anagram, mapping any lowercase letter directly to an array slot.

* insert: walks the word letter by letter, creating a new node whenever a needed path doesn't exist yet, then marks the final node's isEndOfWord = true.

* findNode — a shared helper used by both search and startsWith, since they both start the same way: walk the string's path. It returns null the moment any letter's path is missing (meaning the string was never inserted, not even as a prefix of something else), or returns the final node if the whole walk succeeds.

* The key difference between search and startsWith: search requires the walk to succeed and isEndOfWord to be true — since "app" being a prefix of "apple" doesn't mean "app" was ever inserted as its own word. startsWith only cares that the walk succeeded at all — it doesn't matter if that exact string was ever a complete inserted word.

 */