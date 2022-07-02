class Solution:
    def maximumUnits(self, boxTypes: List[List[int]], truckSize: int) -> int:
        sortedBoxes = sorted(boxTypes, key = lambda x: -x[1])
        totalBoxes = 0
        totalUnits = 0
        for numBox, unit in sortedBoxes:
            if totalBoxes < truckSize:
                totalUnits += max(0, min(truckSize - totalBoxes, numBox) * unit)
                totalBoxes += numBox
        return totalUnits
