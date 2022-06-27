"""
standard binary search-- handle the edge cases separately.
"""
class Solution:
    def singleNonDuplicate(self, nums: List[int]) -> int:
        start = 0
        end = len(nums) - 1
        if len(nums) == 1:
            return nums[0]
        if nums[0] != nums[1]:
            return nums[0]
        if nums[-1] != nums[-2]:
            return nums[-1]
        while start <= end:
            mid = (start + end) // 2
            if 1 <= mid < len(nums) - 1 :
                if (mid % 2 == 0 and nums[mid] == nums[mid + 1]) or (mid % 2 == 1 and nums[mid] == nums[mid - 1]):
                    start = mid + 1
                elif (mid % 2 == 0 and nums[mid] == nums[mid - 1]) or (mid % 2 == 1 and nums[mid] == nums[mid + 1]):
                    end = mid - 1
                else:
                    return nums[mid]
