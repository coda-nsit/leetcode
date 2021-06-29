from copy import copy
from collections import defaultdict
class Solution:
    def createSentences(self, s, idx, wordSet, dp):
        if idx == len(s):
            return []
        if idx in dp:
            return dp[idx]
        for i in range(idx, len(s)):
            if s[idx : i] in wordSet:
                dp[i] = self.createSentences(s, i, wordSet, dp)
                for sentence in dp[i]:
                    dp[idx].append(s[idx : i] + ' ' + sentence)
            if s[idx :] in wordSet and s[idx :] not in dp[idx]:
                dp[idx].append(s[idx :])
        return dp[idx]
        
    def wordBreak(self, s: str, wordDict: List[str]) -> List[str]:
        wordSet = set(wordDict)
        # dp[i] = list of all the sentences that can be formed from s[i : ...]
        # algorithm is:
        # for all i in idx to len(s)
        #   check if the word s[idx : i] is present in the wordDict. If yes then 
        # dp[idx] = prepend s[idx : i] to all the dp[i]
        dp = defaultdict(list)
        return self.createSentences(s, 0,  wordSet, dp)
