class Solution:
    # 2 strings are similar if we can one swap will make both of them equal
    def isSimilar(self, word1, word2):
        total = 0
        for c1, c2 in zip(word1, word2):
            if c1 != c2:
                total += 1
            if total > 2:
                return False
        return True
    
    def findParent(self, parents, word):
        while parents[word] != word:
            word = parents[word]
        return word
    
    def unite(self, parents, sizes, word1, word2):
        p1 = self.findParent(parents, word1)
        p2 = self.findParent(parents, word2)
        if sizes[p1] > sizes[p2]:
            parents[p2] = p1
            sizes[p1] += sizes[p2]
        else:
            parents[p1] = p2
            sizes[p2] += sizes[p1]
        return
    
    def find(self, word1, word2):
        return self.findParent(word1) == self.findParent(word2)
        
        
    def numSimilarGroups(self, strs: List[str]) -> int:
        sizes = {}
        parents = {}
        for word in strs:
            sizes[word] = 1
            parents[word] = word
        
        for i1 in range(len(strs)):
            for i2 in range(i1 + 1, len(strs)):
                if self.isSimilar(strs[i1], strs[i2]):
                    self.unite(parents, sizes, strs[i1], strs[i2])
        
        answer = {}
        for word in strs:
            parentWord = self.findParent(parents, word)
            if parentWord not in answer:
                answer[parentWord] = [parentWord]
            else:
                answer[parentWord].append(word)
        return len(answer.keys())
