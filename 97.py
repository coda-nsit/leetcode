"""
dp[i][j] = dp[i - 1][j] or dp[i][j - 1]
"""
class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        i1 = i2 = 0
        
        s1 = [''] + [c for c in s1]
        s2 = [''] + [c for c in s2]
        s3 = [''] + [c for c in s3]
        
        n1, n2, n3 = len(s1), len(s2), len(s3)
        
        
        if n3 + 1 != n1 + n2:
            return False
        
        dp = [[False] * (n2) for i in range(n1)]
        
        dp[0][0] = True
        
        for i in range(1, n1):
            if s1[i] == s3[i]:
                dp[i][0] = dp[i - 1][0]
                
        for i in range(1, n2):
            if s2[i] == s3[i]:
                dp[0][i] = dp[0][i - 1]
        
        for i in range(1, n1):
            for j in range(1, n2):
                dp[i][j] = (dp[i - 1][j] and s1[i] == s3[i + j]) or (dp[i][j - 1] and s2[j] == s3[i + j])
        # print(dp)
        return dp[n1 - 1][n2 - 1]
