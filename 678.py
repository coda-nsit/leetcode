"""
(leftMin, leftMax) = range of min and max number of ( that can exist.
So, 
1. if ( comes do a leftMin +1 and leftMax +1
2. if ) comes do a leftMin -1 and leftMax -1
3. if * comes it can be both ( or ), so leftMin -1 and leftMax +1

Also, 
1. if ever leftMin < 0, it means that we have unnecessarily made * ), so reset leftMin to 0
2. leftMax < 0, it means that even after our best efforts we didn't find enough ( braces. This happens in the normal * less valid paranthesis also.

https://www.youtube.com/watch?v=QhPdNS143Qg
"""
class Solution:
    def checkValidString(self, s: str) -> bool:
        leftMax, leftMin = 0, 0
        for c in s:
            if c == "(":
                leftMin += 1
                leftMax += 1
            elif c == ")":
                leftMin -= 1
                leftMax -= 1
            else:
                leftMin -= 1
                leftMax += 1
            if leftMin < 0:
                leftMin = 0
            if leftMax < 0:
                return False
        return leftMin <= 0 <= leftMax

"""
"()"
"(*)"
"(*))"
"("
"((**)))))(()())()"
"(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())"
"(**("
"(((()))())))*))())()(**(((())(()(*()((((())))*())(())*(*(()(*)))()*())**((()(()))())(*(*))*))())"
"""
