"""
4 cases:
-------------      -------------         ----------                 --------
     ----         ----------                   ----------       -------------------
which can be reduced to 2 cases:
s1 <= s2 <= e1
and
s2 <= s1 <= e2
"""
class Solution:
    def intervalIntersection(self, firstList: List[List[int]], secondList: List[List[int]]) -> List[List[int]]:
        i1 = i2 = 0
        ans = []
        while i1 < len(firstList) and i2 < len(secondList):
            s1, e1 = firstList[i1]
            s2, e2 = secondList[i2]
            if s1 <= s2 <= e1:
                ans.append([s2, min(e1, e2)])
            elif s2 <= s1 <= e2:
                ans.append([s1, min(e1, e2)])
            if e1 >= e2:
                i2 += 1
            else:
                i1 += 1
        return ans
                
