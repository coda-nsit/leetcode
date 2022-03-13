class Solution:
    def replaceNonCoprimes(self, nums: List[int]) -> List[int]:
        stack = [nums[0]]
        ptr = 1
        while ptr < len(nums):
            stackTop = stack.pop()
            gcdAB = gcd(stackTop, nums[ptr])
            if gcdAB > 1:
                lcmAB = (nums[ptr] * stackTop) // gcdAB
                stack.append(lcmAB)
                while len(stack) >= 2:
                    item1, item2 = stack.pop(), stack.pop()
                    gcdItem1Item2 = gcd(item1, item2)
                    if gcdItem1Item2 > 1:
                        lcmItem1Item2 = (item1 * item2) // gcdItem1Item2
                        stack.append(lcmItem1Item2)
                    else:
                        stack.append(item2)
                        stack.append(item1)
                        break
            else:
                stack.append(stackTop)
                stack.append(nums[ptr])
            ptr += 1
        return stack
