from collections import defaultdict

"""
(a * b) % k === (gcd(a, k) * gcd(b, k)) % k
https://leetcode.com/problems/count-array-pairs-divisible-by-k/discuss/1785027/C%2B%2BPython-Easy-and-Concise-with-Explanation

the usual algorithm is:
for idx1, a in nums:
    for idx2, b in nums:
        if idx1 < idx2 and a * b % k:
            count += 1

now since we know (a * b) % k === (gcd(a, k) * gcd(b, k)) % k 
instead of looping on all the numbers loop on every element pair, loop on every gcd pair

for idx1, a in gcds:
    for idx2, b in gcds:
        if idx1 <= idx2 and a * b % k == 0:
            case in which a == b
            case in which a <= b
"""
class Solution:
    def gcdCalculator(self, num1, num2):
        if num1 == 0:
            return num2
        return self.gcdCalculator(num2 % num1, num1)
        
    def countPairs(self, nums: List[int], k: int) -> int:
        gcdToIndices = defaultdict(int)
        for idx, num in enumerate(nums):
            gcdToIndices[self.gcdCalculator(num, k)] += 1
        
        total = 0
        for a in gcdToIndices.keys():
            for b in gcdToIndices.keys():
                if a <= b and (a * b) % k == 0:
                    if a < b:
                        total += gcdToIndices[a] * gcdToIndices[b]
                    else:
                        total += (gcdToIndices[a] * (gcdToIndices[b] - 1)) // 2
        return total
