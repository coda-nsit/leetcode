class Solution:
    def minimalKSum(self, nums: List[int], k: int) -> int:
        totalSum = 0
        totalNums = 0
        mini = min(nums)
        if mini > 1:
            totalSum = 1
            totalNums = 1
            nums.append(1)
        nums = sorted(set(nums))
        # print("nums:", nums)
        for i in range(1, len(nums)):
            diff = nums[i] - nums[i - 1] - 1
            if diff + totalNums <= k:
                right = nums[i] - 1
                left = nums[i - 1]
                totalSum += (right * (right + 1)) // 2 - (left * (left + 1)) // 2
                totalNums += nums[i] - nums[i - 1] - 1
                if totalNums == k:
                    return totalSum
            else:
                numsLeft = k - totalNums
                right = nums[i - 1] + numsLeft
                totalSum += (right * (right + 1)) // 2 - (nums[i - 1] * (nums[i - 1] + 1)) // 2
                return totalSum
        if totalNums < k:
            numsLeft = k - totalNums
            right = nums[-1] + numsLeft
            totalSum += (right * (right + 1)) // 2 - (nums[-1] * (nums[-1] + 1)) // 2
            return totalSum
