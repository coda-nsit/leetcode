class Solution:
    def parseLevelByLevel(self, nestedList, currentDepth):
        if type(nestedList) == list:
            total = 0
            for point in nestedList:
                total += self.parseLevelByLevel(point, currentDepth + 1)
            return total
        elif nestedList.getInteger() is not None:
            return nestedList.getInteger() * currentDepth
        else:
            return self.parseLevelByLevel(nestedList.getList(), currentDepth)
        
    def depthSum(self, nestedList: List[NestedInteger]) -> int:
        return self.parseLevelByLevel(nestedList, 0)
