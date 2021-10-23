class TrieNode {
    public Character c;
    public TrieNode[] childrenNodes;
    public boolean isWord;
    
    TrieNode(char c) {
        this.c = c;
        this.childrenNodes = new TrieNode[26];
        this.isWord = false;
    }
}
class WordDictionary {

    private TrieNode root;
    
    public WordDictionary() {
        this.root = new TrieNode('*');    
    }
    
    public void addWord(String word) {
        TrieNode walker = this.root;
        for(int i = 0; i < word.length(); ++i) {
            if (walker.childrenNodes[word.charAt(i) - 'a'] == null) {
                walker.childrenNodes[word.charAt(i) - 'a'] = new TrieNode(word.charAt(i));
            }
            if (i == word.length() - 1) {
                walker.isWord = true;
            }
            walker = walker.childrenNodes[word.charAt(i) - 'a'];
        }
    }
    
    private boolean searchPartial(String word, int index, TrieNode root) {
        if (root == null) {
            return false;
        }
        
        if (index == word.length() - 1) {
            return (word.charAt(index) == '.' || root.childrenNodes[word.charAt(index) - 'a'] != null) && root.isWord;
        }
        if (word.charAt(index) == '.') {
            for (int i = 0; i < 26; ++i) {
                if (searchPartial(word, index + 1, root.childrenNodes[i])) {
                    return true;
                }
            }
        } else {
            if (root.childrenNodes[word.charAt(index) - 'a'] == null) {
                return false;
            } else {
                return searchPartial(word, index + 1, root.childrenNodes[word.charAt(index) - 'a']);
            }
        }
        return false;
        
    }
    
    public boolean search(String word) {
        TrieNode walker = this.root;
        return searchPartial(word, 0, walker);
    }
}

// ["WordDictionary","addWord","addWord","addWord","search","search","search", "search"]
// [[],["bad"],["dad"],["mad"],["pad"],["bad"],["mag"], ["mcg"]]
// ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
// [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
// ["WordDictionary","addWord","addWord","search","search","search","search","search","search"]
// [[],["a"],["a"],["."],["a"],["aa"],["a"],[".a"],["a."]]
// ["WordDictionary","addWord","addWord","search","search","search","search","search","search","search","search"]
// [[],["a"],["ab"],["a"],["a."],["ab"],[".a"],[".b"],["ab."],["."],[".."]]
// ["WordDictionary","search"]
// [[],["a"]]

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
