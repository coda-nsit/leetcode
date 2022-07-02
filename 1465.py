class Solution:
    def maxArea(self, h: int, w: int, horizontalCuts: List[int], verticalCuts: List[int]) -> int:
        horizontalCuts = sorted([0] + horizontalCuts + [h])
        maxHorizontalCut = 0
        for i in range(1, len(horizontalCuts)):
            maxHorizontalCut = max(maxHorizontalCut, horizontalCuts[i] - horizontalCuts[i - 1])
        # print("maxHorizontalCut:", maxHorizontalCut)
        verticalCuts = sorted([0] + verticalCuts + [w])
        ans = 0
        for i in range(1, len(verticalCuts)):
            # print("verticalCuts:", (verticalCuts[i] - verticalCuts[i - 1]))
            ans = max(ans, maxHorizontalCut * (verticalCuts[i] - verticalCuts[i - 1]))
        return ans % (10 ** 9 + 7)
