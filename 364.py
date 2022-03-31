# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
#class NestedInteger:
#    def __init__(self, value=None):
#        """
#        If value is not specified, initializes an empty list.
#        Otherwise initializes a single integer equal to value.
#        """
#
#    def isInteger(self):
#        """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        :rtype bool
#        """
#
#    def add(self, elem):
#        """
#        Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
#        :rtype void
#        """
#
#    def setInteger(self, value):
#        """
#        Set this NestedInteger to hold a single integer equal to value.
#        :rtype void
#        """
#
#    def getInteger(self):
#        """
#        @return the single integer that this NestedInteger holds, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        :rtype int
#        """
#
#    def getList(self):
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        :rtype List[NestedInteger]
#        """

class Solution:
    def parseLevelByLevel(self, nestedInteger, currentLevel, maxDepth):
        total = 0
        if type(nestedInteger) == list:
            for point in nestedInteger:
                total += self.parseLevelByLevel(point, currentLevel + 1, maxDepth)
        elif nestedInteger.getInteger() is not None:
            total = nestedInteger.getInteger() * (maxDepth - currentLevel + 1)
        else:
            total = self.parseLevelByLevel(nestedInteger.getList(), currentLevel, maxDepth)
        return total
    
    def getMaxDepth(self, nestedInteger, currentLevel):
        maxDepth = 0
        if type(nestedInteger) == list:
            for point in nestedInteger:
                maxDepth = max(maxDepth, self.getMaxDepth(point, currentLevel + 1))
        elif nestedInteger.getInteger() is not None:
            maxDepth = max(maxDepth, currentLevel)
        else:
            maxDepth = max(maxDepth, self.getMaxDepth(nestedInteger.getList(), currentLevel))
        return maxDepth
        
    def depthSumInverse(self, nestedList: List[NestedInteger]) -> int:
        maxDepth = self.getMaxDepth(nestedList, 0)
        return self.parseLevelByLevel(nestedList, 0, maxDepth)
        
                
            
        
            
        
