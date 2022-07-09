"""
Have 2 pointers i and j. 

invariance: to reach any i we can come from j, j + 1, j + 2, j + 3, ... Let's call these active indices.
Put active indices in a monotonically decreasing queue. Suppose for an i we are coming node k = j + 10, then all indices j, j + 1, j + 2, ..., j + 9 become inactive and must be removed from the queue.
"""
from collections import deque
class Solution:
    def fixQueue(self, dq, jumpValues, currentIdx, k):
        # remove all elements which are more than k distance away
        while len(dq) > 0:
            dqIdx = dq.popleft()
            if currentIdx - dqIdx < k:
                dq.appendleft(dqIdx)
                break
        
        # make the dq monotonically decreasing
        while len(dq) > 0:
            dqIdx = dq.pop()
            if jumpValues[dqIdx] > jumpValues[currentIdx]:
                dq.append(dqIdx)
                break
        dq.append(currentIdx)
        return
        
    def maxResult(self, nums: List[int], k: int) -> int:
        n = len(nums)
        jumpValues = [float("inf")] * n
        dq = deque()
        
        jumpValues[0] = nums[0]
        j = 0
        dq.append(j)
        
        for i in range(1, n):
            bestJumpIdx = dq.popleft()
            dq.appendleft(bestJumpIdx)
            jumpValues[i] = jumpValues[bestJumpIdx] + nums[i]
            self.fixQueue(dq, jumpValues, i, k)
            # print(i, bestJumpIdx)
        # print(jumpValues)
        return jumpValues[-1]
