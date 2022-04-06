"""
if a > 0
the quadratic curve looks like a 
\       /
 \     /
  \   /
    \/
so the problem boils down to sorting an array which is decreasing and then increasing

if a < 0
the quadratic curve looks like an inverted v
so the problem boils down to sorting an array which is increasing and then decreasing
"""

class Solution:
    def evaluateQuadratic(self, x, a, b, c):
        return a * (x ** 2) + b * x + c
        
    def sortTransformedArray(self, nums: List[int], a: int, b: int, c: int) -> List[int]:
        answer = []
        leftPtr, rightPtr = 0, len(nums) - 1
        if a > 0:
            while leftPtr <= rightPtr:
                lVal, rVal = self.evaluateQuadratic(nums[leftPtr], a, b, c), self.evaluateQuadratic(nums[rightPtr], a, b, c)
                if lVal >= rVal:
                    answer.append(lVal)
                    leftPtr += 1
                else:
                    answer.append(rVal)
                    rightPtr -= 1
            return answer[::-1]
        else:
            while leftPtr <= rightPtr:
                lVal, rVal = self.evaluateQuadratic(nums[leftPtr], a, b, c), self.evaluateQuadratic(nums[rightPtr], a, b, c)
                if lVal <= rVal:
                    answer.append(lVal)
                    leftPtr += 1
                else:
                    answer.append(rVal)
                    rightPtr -= 1
            return answer
            
        
"""
[-4,-2,2,4]
1
3
5
[-4,-2,2,4]
-1
3
5
[-4,-2,2,4]
0
3
5
[-99,-94,-90,-88,-84,-83,-79,-68,-58,-52,-52,-50,-47,-45,-35,-29,-5,-1,9,12,13,25,27,33,36,38,40,46,47,49,57,57,61,63,73,75,79,97,98]
-2
44
-56
"""
            
                    
