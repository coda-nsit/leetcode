class Solution:
    def test2(self, x, y):
        return x == y
    
    def test3(self, x, y, z):
        return x == y == z
    
    def test1(self, x, y, z):
        return (x + 1 == y and y + 1 == z) or(x == y + 1 and y == z + 1)
    
    def validPartition(self, nums: List[int]) -> bool:
        n = len(nums)
        if n < 3:
            return False
        dp = [False] * n
        
        dp[0] = False
        if self.test2(nums[0], nums[1]):
            dp[1] = True
        if self.test3(nums[0], nums[1], nums[2]):
            dp[2] = True
        if self.test1(nums[0], nums[1], nums[2]):
            dp[2] = True
        
        for i in range(3, n):
            x, y, z = nums[i], nums[i - 1], nums[i - 2]
            if (self.test2(x, y) and dp[i - 2]) or (self.test3(x, y, z) and dp[i - 3]) or (self.test1(x, y, z) and dp[i - 3]):
                dp[i] = True
        # print(dp)
        return dp[-1]
    

"""
[4,4,4,5,6]
[1,1,1,2]

[993335,993336,993337,993338,993339,993340,993341]
false
"""
        
