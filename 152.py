class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        maxTillNow = minTillNow = nums[0]
        maxi = nums[0]
        for i in range(1, len(nums)):
            if minTillNow == 0 and maxTillNow == 0:
                minTillNow = maxTillNow = nums[i]
            else:
                miniTemp = min(minTillNow * nums[i], maxTillNow * nums[i], nums[i])
                maxiTemp = max(minTillNow * nums[i], maxTillNow * nums[i], nums[i])
                minTillNow, maxTillNow = miniTemp, maxiTemp
            # print(nums[i], minTillNow, maxTillNow)
            maxi = max(maxi, maxTillNow)
        return maxi
