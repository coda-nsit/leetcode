class Solution:
    def longestSubsequence(self, arr: List[int], difference: int) -> int:
        dp = {}
        ending_digit = {}
        dp[0] = 1
        ending_digit[arr[0]] = 0
        
        for i in range(1, len(arr)):
            if arr[i] - difference in ending_digit:
                dp[i] = dp[ending_digit[arr[i] - difference]] + 1
            else:
                dp[i] = 1
            ending_digit[arr[i]] = i
        # print(dp)
        return max(dp.values())
