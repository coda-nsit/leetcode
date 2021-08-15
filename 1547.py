'''
Video: https://www.youtube.com/watch?v=8uvWmiawJ8Q&t=5s
Text: https://leetcode.com/problems/minimum-cost-to-cut-a-stick/discuss/780880/DP-with-picture-(Burst-Balloons)
We dont know which cut to make so make all possible cuts. The main trick is to realize that the values of cuts array can be used as boundary points i.e. left and right. So, suppose we have cuts [1, 3, 5, 7] and we cut at 5 then left subproblem = [1, 5] and right subproblem = [5, 7]
'''
class Solution:
    def findMinCost(self, left, right, cuts, dp):
        if (left, right) in dp:
            return dp[(left, right)]
        if len(cuts) == 0 or right == left:
            return 0
        min_cut_cost = float("inf")
        for idx in range(len(cuts)):
            min_cut_cost = min(min_cut_cost, self.findMinCost(left, cuts[idx], cuts[:idx], dp) + self.findMinCost(cuts[idx], right, cuts[idx + 1:], dp))
        dp[(left, right)] = right - left + min_cut_cost
        return dp[(left, right)]
        
    def minCost(self, n: int, cuts: List[int]) -> int:
        dp = {}
        cuts = sorted(cuts)
        sol = self.findMinCost(0, n, cuts, dp)
        return sol
