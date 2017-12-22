package Trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weijian1 Weijian Li
 * public method: search, insert, delete, startsWith
 */
public class Trie {
    public static class TrieNode {
        // fields
        /**
         * key is info that Trie stores, value is reference to children, if this Trie only stores a-z, we can use int[26]
         * to replace map here.
         */
        private Map<Character, TrieNode> children;
        /**
         * mark whether current TrieNode is the end of one word.
         */
        private boolean isWord;
        /**
         * this field means how many children below this TrieNode, it is only useful for delete method.
         */
        private int child_below;
        public TrieNode() {
            this.children = new HashMap<>();
        }
    }

    // fields
    private TrieNode root;
    public Trie() {
        this.root = new TrieNode();
    }
    // public API

    /**
     * search whether a word in this Trie or not, time is O(n), where n is length of word.
     * @param word input
     * @return true is word exists
     */
    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char cur = word.charAt(i);
            TrieNode next = curr.children.get(cur);
            if (next != null) {
                curr = next;
            } else {
                return false;
            }
        }
        return curr.isWord;
    }

    /**
     * insert a new word to Trie.
     * @param word input
     * @return true if insert success, or false, if this word already exists in dictionary
     */
    public boolean insert(String word) {
        if (search(word)) {
            return false;
        }
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char cur = word.charAt(i);
            TrieNode next = curr.children.get(cur);
            if (next == null) {
                curr.children.put(cur, new TrieNode());
            }
            next = curr.children.get(cur);
            next.child_below++;
            curr = next;
        }
        curr.isWord = true;
        return true;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     * @param prefix input
     * @return true if there is any word in the trie that starts with the given prefix.
     */
    public boolean startswith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char cur = prefix.charAt(i);
            TrieNode next = curr.children.get(cur);
            if (next != null) {
                curr = next;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean delete(String word) {
        if(!search(word)) {
            return false;
        }
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char cur = word.charAt(i);
            TrieNode next = curr.children.get(cur);
            if (next.child_below == 1) {
                curr.children.remove(cur);
                return true;
            }
            next.child_below--;
            curr = next;
        }
        curr.isWord = false; // important!!!
        return true;
    }
}
