class TrieNode:
    def __init__(self, val):
        self.val = val
        self.children = {}
        self.isWord = False
        self.frequency = 0

        
class Trie(TrieNode):
    def __init__(self):
        self.root = TrieNode("-1")
    
    def insertWord(self, word):
        root = self.root
        for idx, c in enumerate(word):
            if c not in root.children:
                root.children[c] = TrieNode(c)
            root = root.children[c]
            if idx == len(word) - 1:
                root.isWord = True
                root.frequency += 1
        return
    
    def printTrie(self, root, currentChars):
        if root.val != "-1":
            currentChars.append(root.val)
        if root.isWord:
            print("".join(currentChars), root.frequency)
        for childNode in root.children:
            self.printTrie(root.children[childNode], currentChars)
        if root.val != "-1":
            currentChars.pop()
        return
    
    def getKWords(self, root, currentChars, kMostFrequent):
        if root.val != "-1":
            currentChars.append(root.val)
        if root.isWord:
            kMostFrequent[root.frequency].append("".join(currentChars))
        for childNode in root.children:
            self.getKWords(root.children[childNode], currentChars, kMostFrequent)
        if root.val != "-1":
            currentChars.pop()
        return
            
    
class Solution:
    def topKFrequent(self, words: List[str], k: int) -> List[str]:
        trie = Trie()
        for word in words:
            trie.insertWord(word)
        
        # trie.printTrie(trie.root, [])
        
        kMostFrequent = defaultdict(list)
        trie.getKWords(trie.root, [], kMostFrequent)
        frequencyKeys = sorted(kMostFrequent.keys(), reverse=True)
        finalWords = []
        for frequencyKey in frequencyKeys:
            frequentWords = sorted(kMostFrequent[frequencyKey])
            if k >= len(frequentWords):
                finalWords.extend(frequentWords)
            else:
                finalWords.extend(frequentWords[:k])
            k -= len(frequentWords)
            if k <= 0:
                break
        return finalWords
 
"""
["i","love","leetcode","i","love","coding"]
2
["the","day","is","sunny","the","the","the","sunny","is","is"]
4
"""
