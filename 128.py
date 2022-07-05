"""

4,5,6,7     8     9,10,11
When 8 comes in just update 4,11. All elements in between are useless

"""
class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        if len(nums) == 0:
            return 0
        size = {num: 1 for num in nums}
        visited = set()
        for num in nums:
            if num in visited:
                continue
            visited.add(num)
            if num - 1 in visited:
                # get the extreme element on the left
                ll = num - size[num - 1]
                # fix the extreme elements
                size[ll] += size[num]
                size[num] = size[ll]
                # print(num, size[num], num - 1, size[num - 1])
                
            if num + 1 in visited:
                # get the extreme element on the right
                rr = num + size[num + 1]
                # fix the extreme element
                size[rr] += size[num]
                size[num] = size[rr]
                # print(num, size[num], num + 1, size[num + 1])
            
            # if both 7 and 9 exist we need to combine both 4-7 and 9-11, cause right now both were updated independently
            if num - 1 in visited and num + 1 in visited:
                size[ll] = size[rr] = size[num]
        # print(size)
        return max(size.values())

"""
[100,4,200,1,3,2]
[0,3,7,2,5,8,4,6,0,1]
[100,4,200,1,3,2]
[1,0,-1]
"""
