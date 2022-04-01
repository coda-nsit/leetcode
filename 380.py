"""
Have 3 data structures:
1. a list with all the elements
2. a set with all the elements
3. a hash map with number as as the key and its index in the hasmap as value

While removing swap the number with the last element in the list and pop it.
"""

from random import randrange
class RandomizedSet:

    def __init__(self):
        self.numbersIndex = {}
        self.numbersSet = set()
        self.numbersList = []

    def insert(self, val: int) -> bool:
        if val in self.numbersSet:
            return False
        self.numbersList.append(val)
        self.numbersSet.add(val)
        self.numbersIndex[val] = len(self.numbersList) - 1
        return True

    def remove(self, val: int) -> bool:
        if val not in self.numbersSet:
            return False
        self.numbersSet.remove(val)
        index = self.numbersIndex[val]
        self.numbersList[index], self.numbersList[-1] = self.numbersList[-1], self.numbersList[index]
        self.numbersList.pop()
        if len(self.numbersList) > 0:
            # if the element being removed is itself the last element
            if index == len(self.numbersList):
                self.numbersIndex.pop(val)
            else:
                self.numbersIndex[self.numbersList[index]] = index
        # if the list becomes empty
        else:
            self.numbersIndex = {}
        return True

    def getRandom(self) -> int:
        randomIndex = randrange(0, len(self.numbersList))
        return self.numbersList[randomIndex]
        


# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()
