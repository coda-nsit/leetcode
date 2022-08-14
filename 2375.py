from queue import Queue
class Solution:    
    def __init__(self):
        self.ans = "9999999999"
        
    def smallestNumber(self, pattern: str) -> str:
        n = len(pattern)
        bfsQ = Queue()
        for i in range(1, 10):
            bfsQ.put(str(i))
        
        # print(bfsQ)
            
        mini = "9999999999999"
        
        while bfsQ.empty() is False:
            currentString = bfsQ.get()
            # print(currentString)
            if len(currentString) == n + 1:
                mini = min(mini, currentString)
                continue
            currentStringSet = set([int(c) for c in currentString])
            patternIdx = len(currentString) - 1
            if pattern[patternIdx] == "I":
                for i in range(int(currentString[-1]) + 1, 10):
                    if i not in currentStringSet:
                        bfsQ.put(copy.copy(currentString) + str(i))
            else:
                for i in range(1, int(currentString[-1])):
                    if i not in currentStringSet:
                        bfsQ.put(copy.copy(currentString) + str(i))
        return mini
                
        




# class Solution:    
#     def smallestNumber(self, pattern: str) -> str:
#         n = len(pattern)
#         dp = [["9999999999"] * 10 for i in range(n + 1)]
        
#         for i in range(1, 10):
#             dp[0][i] = str(i)
        
#         for i in range(1, n + 1):
#             if pattern[i - 1] == "I":
#                 for j in range(1, 10):
#                     mini = "9999999999"
#                     for k in range(1, j):
#                         mini = min(mini, dp[i - 1][k])
#                     dp[i][j] = copy.copy(mini) + str(j)
#             else:
#                 for j in range(1, 10):
#                     mini = "9999999999"
#                     for k in range(j + 1, 10):
#                         mini = min(mini, dp[i - 1][k])
#                     dp[i][j] = copy.copy(mini) + str(j)
        
#         print(dp)
#         ans = "9999999999"
#         for i in range(1, 10):
#             ans = min(ans, dp[n][i])
#         return ans
                
        
