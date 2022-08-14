class Solution:
    def edgeScore(self, edges: List[int]) -> int:
        n = len(edges)
        scores = {i:0 for i in range(n)}
        
        for idx, node in enumerate(edges):
            scores[node] += idx
        
        maxi = 0
        ans = 0
        for k in sorted(scores.keys()):
            if scores[k] > maxi:
                maxi = scores[k]
                ans = k
        return ans
