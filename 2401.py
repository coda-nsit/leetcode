"""
Have a sliding window.
Invariance: 
1. the & of all elements in the window = 0
2. current element is always present in the sliding window

If adding current element makes & of sliding window != 0, remove elements from left.
How to remove elements from left? 
Maintain a | of all elements in the window called queueOr.
Removing an element nums[startIdx] means, queueOr ^= nums[startIdx].

How does ^ undo |?
a | b | c | d | e
we want to remove a, since a ^ a = 0 so,
a ^ a | b | c| d | e = 0 | b | c | d| e = b | c | d | e
"""
class Solution:
    def longestNiceSubarray(self, nums: List[int]) -> int:
        queueOr = nums[0]
        startIdx = 0
        endIdx = 1
        maxLen = 1

        while endIdx < len(nums):
            while startIdx < endIdx and (queueOr & nums[endIdx]) != 0 :
                queueOr ^= nums[startIdx]
                startIdx += 1
            # add the current element to queue
            queueOr |= nums[endIdx]
            endIdx += 1
            maxLen = max(maxLen, endIdx - startIdx)
        return maxLen
