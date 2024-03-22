"""
let nums = [2, 5, 6, 7, x, 6, 4, 12, 15, y, 56, 6, 2, 7, z]
observation 1: 
we will never increase and decrease a number i.e. we will either increase a number or decrease it. why? see below:
[2, 5, 6, 7, x, 6, 4, 12, 15, y, 56, 6, 2, 7, z]
[2, 5, 6, 7, x + 2, 6, 4, 12, 15, y - 2, 56, 6, 2, 7, z]
[2, 5, 6, 7, x + 2 - 2, 6, 4, 12, 15, y - 2, 56, 6, 2, 7, z + 2]
why not directly do
[2, 5, 6, 7, x, 6, 4, 12, 15, y - 2, 56, 6, 2, 7, z + 2]

observation 2:
since adding +2/-2 even remains even, odd remains odd
 
so just greedily make nums equal to target
"""
class Solution:
    def makeSimilar(self, nums: List[int], target: List[int]) -> int:
        nums = sorted(nums)
        target = sorted(target)

        nums_odd = []
        nums_even = []
        for num in nums:
            if num % 2 == 1:
                nums_odd.append(num)
            else:
                nums_even.append(num)
        
        target_odd = []
        target_even = []
        for num in target:
            if num % 2 == 1:
                target_odd.append(num)
            else:
                target_even.append(num)
        ops = 0
        for i in range(len(nums_odd)):
            ops += abs(nums_odd[i] - target_odd[i])
        for i in range(len(nums_even)):
            ops += abs(nums_even[i] - target_even[i])
        return ops // 4
