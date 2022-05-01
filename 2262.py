"""
    0123456789
s = azdcabdcmd
dp[i] = appeal of all substrings ending at i

till 9 we have following substrings ending at "d"

d                |  1

d m              |  (1 + 2) + 2
d mc             |

d mcd            |
d mcdb           |
d mcdba          |
d mcdbac         |   E
d mcdbacd        |
d mcdbacdz       |
d mcdbacdza      |

dp[i] = E + (m + mc) + (d coming from m and mc) + d
      = dp[i - 1]    + #number of strings ending at i - 1 without d + 1
      
so to keep track of (#number of strings ending at i - 1 without d) we keep track of last occurrence of d
"""
class Solution:
    def appealSum(self, s: str) -> int:
        lastOccurence = {}
        dp = [0] * len(s)
        
        # base case
        lastOccurence[s[0]] = 0
        dp[0] = 1
        
        # induction
        for i in range(1, len(s)):
            if s[i]  not in lastOccurence:
                dp[i] = dp[i - 1] + (i + 1)
            else:
                dp[i] = dp[i - 1] + i - lastOccurence[s[i]]
            lastOccurence[s[i]] = i
        return sum(dp)
    
