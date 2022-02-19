from collections import defaultdict

class WordDistance:

    def __init__(self, wordsDict: List[str]):
        self.wordIndexes = defaultdict(list)
        for idx, word in enumerate(wordsDict):
            self.wordIndexes[word].append(idx)

    def shortest(self, word1: str, word2: str) -> int:
        l1, l2 = self.wordIndexes[word1], self.wordIndexes[word2]
        i, j = 0, 0
        mini = float("inf")
        while i < len(l1) and j < len(l2):
            mini = min(mini, abs(l2[j] - l1[i]))
            if l1[i] < l2[j]:
                i += 1
            else:
                j += 1
        return mini


# Your WordDistance object will be instantiated and called as such:
# obj = WordDistance(wordsDict)
# param_1 = obj.shortest(word1,word2)
