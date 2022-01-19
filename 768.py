'''
we can create a split at index i only if Max arr[0...i] <= min arr[i + 1...]
'''
class Solution:
    def maxChunksToSorted(self, arr: List[int]) -> int:
        left_max = [arr[0]]
        for i in range(1, len(arr)):
            left_max.append(max(left_max[-1], arr[i]))
        right_min = [arr[-1]]
        for i in range(len(arr) - 2, -1, -1):
            right_min.append(min(right_min[-1], arr[i]))
        right_min = right_min[::-1]
        
        total_split = 0
        for i in range(len(arr) -1):
            if left_max[i] <= right_min[i + 1]:
                total_split += 1
        return min(total_split + 1, len(arr))
    
# [5,4,3,2,1]
# [2,1,3,4,4]
# [0,0,1,1,1]
