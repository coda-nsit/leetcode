class Solution:
    def findLength(self, nums1: List[int], nums2: List[int]) -> int:
        n1, n2 = len(nums1), len(nums2)
        dp = [[0] * n2 for i in range(n1)]
        
        for i in range(n1):
            if nums1[i] == nums2[0]:
                dp[i][0] = 1
        for i in range(n2):
            if nums1[0] == nums2[i]:
                dp[0][i] = 1
                
        for i in range(1, n1):
            for j in range(1, n2):
                if nums1[i] == nums2[j]:
                    if nums1[i - 1] == nums2[j - 1]:
                        dp[i][j] = dp[i - 1][j - 1] + 1
                    else:
                        dp[i][j] = 1
        maxi = 0
        for i in range(n1):
            maxi = max(maxi, max(dp[i]))
        return maxi
