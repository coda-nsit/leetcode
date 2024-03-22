"""
1. sort nums
2. diff = nums[i] - nums[i - 1]
ans[i] = ans[i-1] + diff * cumSum[0...i - 1] - dif * cumSum[i...n]
"""

class Solution:
    def minCost(self, nums: List[int], cost: List[int]) -> int:
        combined = sorted([(nums[i], cost[i]) for i in range(len(nums))])
        
        prefixCost = [combined[0][1]]
        for i in range(1, len(combined)):
            prefixCost.append(prefixCost[-1] + combined[i][1])
        
        suffixCost = [combined[len(combined) - 1][1]]
        for i in range(len(combined) - 2, -1, -1):
            suffixCost.append(suffixCost[-1] + combined[i][1])
        suffixCost = suffixCost[::-1]

        # print("combined", combined)
        # print("prefixCost:", prefixCost)
        # print("suffixCost:", suffixCost)

        currCost = 0
        for i in range(len(combined)):
            currCost += (combined[i][0] - combined[0][0]) * combined[i][1]
        minCost = currCost
        # print(0, minCost)

        for i in range(1, len(combined)):
            diff = combined[i][0] - combined[i - 1][0]
            currCost = currCost + diff * prefixCost[i - 1] - diff * suffixCost[i]
            minCost = min(minCost, currCost)
            # print(i, minCost)
        return minCost
