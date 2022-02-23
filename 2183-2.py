from collections import defaultdict

"""
https://youtu.be/d1RZF-pD9r0?t=1044
a = 6, k = 20

(6 * b) % 20 = 0
(2.3.b) % (2**2 * 5)

The factor 3 in a is not of any use since, it's not present in k. So to weed out the factors not present in k, we do,
gcd(a, k) = gcd(6, 20) = 2

So, b can be any multiple of,
    k           2**2 * 5       2**2 * 5       10
---------   =   ---------   =  ---------  = 
gcd(a, k)       gcd(6, 20)         2


So the algorithm is as follows:
answer = 0
for i1 in range(len(nums)):
  a = nums[i1]
  b = k / gcd(a, k)
  for i2 in range(0, i1):
    if nums[i2] is multiple of b:
      answer += 1
return answer

We just need to optimize the inner for loop i.e. finding indexes that before i which are multiple of b
"""
class Solution:
    def countPairs(self, nums: List[int], k: int) -> int:
        # for our use case, all numbers that have same gcd with k are equivilant. Doing this will reduce the number of possible keys put inside numsSeenSoFar thereby, speeding up the inner loop
        nums = [gcd(num, k) for num in nums]
        numsSeenSoFar = defaultdict(int)
        pairs = 0
        for a in nums:
            b = k / a
            for numSeen in numsSeenSoFar:
                if numSeen % b == 0:
                    pairs += numsSeenSoFar[numSeen]
            numsSeenSoFar[a] += 1
        return pairs      
