# we don't know which element should be the final element so try all the elements. 
# for all i, j abs(a(i) - a(j)) = total changes needed for a(i)
# a[5] = a[5] - a[0]
# a[5] = a[5] - a[1]
# a[5] = a[5] - a[2]
# a[5] = a[5] - a[3]
# a[5] = a[5] - a[4]
# a[5] = a[5] - a[5]
# a[5] = a[6] - a[5]
# a[5] = a[7] - a[5]
# a[5] = a[8] - a[5]

class Solution:
    def minMoves2(self, nums: List[int]) -> int:
        nums = sorted(nums)
        n_nums = len(nums)
        
        prefix_sum = [nums[0]]
        for i in range(1, n_nums):
            prefix_sum.append(prefix_sum[-1] + nums[i])
        
        suffix_sum = [nums[-1]]
        for i in range(n_nums - 2, -1, -1):
            suffix_sum.append(suffix_sum[-1] + nums[i])
        suffix_sum = suffix_sum[::-1]
        
        return min([(i + 1) * nums[i] - prefix_sum[i] + suffix_sum[i] - (len(nums) - i) * nums[i]  for i in range(len(nums))])
