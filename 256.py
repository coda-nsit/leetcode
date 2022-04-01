class Solution:
    def minCost(self, costs: List[List[int]]) -> int:
        nCosts = len(costs)
        dp = [[0] * 3 for i in range(nCosts)]
        
        # base case
        dp[0] = costs[0]
        
        for idx in range(1, len(costs)):
            r, g, b = costs[idx]
            dp[idx][0] = min(dp[idx - 1][1], dp[idx - 1][2]) + r
            dp[idx][1] = min(dp[idx - 1][0], dp[idx - 1][2]) + g
            dp[idx][2] = min(dp[idx - 1][0], dp[idx - 1][1]) + b
        return min(dp[-1])
