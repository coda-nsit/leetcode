"""
Algo to check how many valid puzzles for word[i].

1. remove duplicates and sort puzzles and words.
2. create Trie out of the puzzles.
3. every node in the trie contains 
    1. how many puzzles exist below it.
    2. a dictionary {a: how many puzzles below start with a, b: how many puzzles below start with b,}
4. for every word, traverse the trie. Suppose we end the word at trie node n, for c in a-z check if c is present in word, then that word is valid for the puzzle.

But what we need is algo to check how many valid words for puzzle[i].
1. remove duplicates and sort puzzles and words.
2. create trie of words.
3. for every puzzle p, traverse the trie. While traversing also keep track if p[0] has been visited so far in the word path. Say we are at node n, then number of words thus far = self.wordCount. 
4. consider the following case, puzzle = abelstu and words = [as, abel, abilty, act, acort, aces]. The correct solution would be a, as, abel. So as won't be counted. Thus, while traversing we keep a list of active pointers which point to some node in the tire. For every p for every pointer we check if p is present in pointer.children.   
"""
class TrieNode:
    def __init__(self, val):
        self.val = val
        self.children = {}
        self.wordCount = 0
        

class Trie(TrieNode):
    def __init__(self):
        self.root = TrieNode("-1")
        
    def insert(self, word):
        root = self.root
        for idx, c in enumerate(word):
            if c not in root.children:
                root.children[c] = TrieNode(c)
            root = root.children[c]
            if idx == len(word) - 1:
                root.wordCount += 1
                
    def traverse(self, root, wordTillNow):
        if root is None:
            return
        if root.wordCount > 0:
            print(wordTillNow)
        for child in root.children.values():
            self.traverse(child, wordTillNow + child.val)
        return
    
    def findValidWords(self, word, startChar):
        root = self.root
        totalWords = 0
        rootList = [(root, False)]
        for c in word:
            limit = len(rootList)
            for idx in range(limit):
                root, condition1 = rootList[idx]
                if c in root.children:
                    if c == startChar:
                        condition1 = True
                    root = root.children[c]
                    rootList.append((root, condition1))
                    if condition1:
                        totalWords += root.wordCount
        return totalWords

class Solution:
    def findNumOfValidWords(self, words: List[str], puzzles: List[str]) -> List[int]:
        reducedPuzzles = ["".join(sorted(set(puzzle))) for puzzle in puzzles]
        words = [sorted(set(word)) for word in words]
        trie = Trie()
        for word in words:
            trie.insert(word)
        # trie.traverse(trie.root, "")
        
        answer = []
        for reducedPuzzle, puzzle in zip(reducedPuzzles, puzzles):
            answer.append(trie.findValidWords(reducedPuzzle, puzzle[0]))
            
        return answer
