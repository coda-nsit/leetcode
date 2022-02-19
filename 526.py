"""
brute force: at every i check if i % remainingNumber == 0 or remainingNumber % i == 0
if yes then continue else prune
"""
from copy import copy
class Solution:
    def generateCombinations(self, validCombinations, currentCombination, remainingNumbers):
        if len(remainingNumbers) == 0:
            validCombinations.append(currentCombination)
            return
        currentIndex = len(currentCombination) + 1
        for num in remainingNumbers:
            if (currentIndex % num == 0) or (num % currentIndex == 0):
                subproblemCurrentCombination = copy(currentCombination)
                subproblemRemainingNumbers = copy(remainingNumbers)
                subproblemCurrentCombination.append(num)
                subproblemRemainingNumbers.remove(num)
                self.generateCombinations(validCombinations, subproblemCurrentCombination, subproblemRemainingNumbers)
        
    def countArrangement(self, n: int) -> int:
        validCombinations = []
        remainingNumbers = set([i for i in range(1, n + 1)])
        self.generateCombinations(validCombinations, [], remainingNumbers)
        # print(validCombinations)
        return len(validCombinations)
