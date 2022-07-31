class Solution(object):
    def isSubset(self, counter1, counter2):
        for idx in range(26):
            if counter1[idx] < counter2[idx]:
                return False
        return True
        
    def wordSubsets(self, A, B):
        words1 = [[0] * 26 for i in range(len(A))]
        words2 = [0] * 26
        
        for idx, a in enumerate(A):
            for c in a:
                words1[idx][ord(c) - ord('a')] += 1
        for idx, b in enumerate(B):
            currWord = [0] * 26
            for c in b:
                currWord[ord(c) - ord('a')] += 1
            # print(b, currWord)
            for i in range(ord('a'), ord('z') + 1):
                words2[i - ord('a')] = max(words2[i - ord('a')], currWord[i - ord('a')])
                
        # print("words1", words1)
        # print("words2", words2)
        
        ans = []
        for idx in range(len(A)):
            if self.isSubset(words1[idx], words2):
                ans.append(A[idx])
        return ans
