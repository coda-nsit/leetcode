"""
mergesort and while merging just count how many elements in the rightArray are smaller.
So, while merging if an element of the left array is chosen count how many elements in right array are smaller. 
"""
# [originalNum, originalIdx, invCount]
class Solution:
    def merge(self, leftArr, rightArr):
        # print(leftArr, rightArr)
        i1, i2 = 0, 0
        finalArr = []
        while i1 < len(leftArr) and i2 < len(rightArr):
            if leftArr[i1][0] <= rightArr[i2][0]:
                leftArr[i1][2] += i2
                finalArr.append(leftArr[i1])
                i1 += 1
            else:
                # leftArr[i1][2] += (i2 + 1)
                finalArr.append(rightArr[i2])
                i2 += 1
        while i1 < len(leftArr):
            leftArr[i1][2] += len(rightArr)
            finalArr.append(leftArr[i1])
            i1 += 1
        while i2 < len(rightArr):
            finalArr.append(rightArr[i2])
            i2 += 1
        # print(f"finalArr: ${finalArr}\n\n", finalArr)
        return finalArr
        
    def mergeSort(self, nums, start, end):
        if start == end:
            return [nums[start]]
        mid = (start + end) // 2
        l = r = [] 
        if start <= mid:
            l = self.mergeSort(nums, start, mid)
        if mid + 1 <= end:
            r = self.mergeSort(nums, mid + 1, end)
        if len(l) > 0 and len(r) > 0:
            return self.merge(l, r)
        elif len(l) > 0:
            return l
        return r
        
    def mapNumsMergesortNums(self, mergesortNums):
        mapper = {}
        for mergesortNum in  mergesortNums:
            orgIdx = mergesortNum[1]
            invCount = mergesortNum[2]
            mapper[orgIdx] =  invCount
        return [mapper[key] for key in sorted(mapper.keys())]
            
    
    def countSmaller(self, nums: List[int]) -> List[int]:
        # nums = np.random.randint(1, 1000, 300)
        nums = [[num, idx, 0] for idx, num in enumerate(nums)]
        mergesortNums = self.mergeSort(nums, 0, len(nums) - 1)
        return self.mapNumsMergesortNums(mergesortNums)
        
