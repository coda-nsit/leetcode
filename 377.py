class Solution:
    def reachTarget(self, nums, target, dp):
        if target == 0:
            return 1
        if target in dp:
            return dp[target]
        total = 0
        for num in nums:
            if target - num < 0:
                break
            total += self.reachTarget(nums, target - num, dp)
        dp[target] =  total
        return total
        
    def combinationSum4(self, nums: List[int], target: int) -> int:
        nums = sorted(nums)
        dp = {}
        return self.reachTarget(nums, target, dp)
