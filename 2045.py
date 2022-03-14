"""
for every node store 2 values:
1. the best value
2. the second best value

don't process the child nodes if both these values have been found for a node
"""
from heapq import heappush, heappop
class Solution:
    def timeWhenGreen(self, nodeTime, change):
        factor = nodeTime // change
        return (factor + 1) * change
        
    def secondMinimum(self, n: int, edges: List[List[int]], time: int, change: int) -> int:
        graph = defaultdict(list)
        for edge in edges:
            s, d = edge
            graph[s].append(d)
            graph[d].append(s)
        
        pq = []
        dist1 = defaultdict(lambda: float("inf"))
        dist2 = defaultdict(lambda: float("inf"))
        heappush(pq, (0, 1))
        while len(pq) > 0:
            rootTime, root = heappop(pq)
            # found the strictly second best distance
            if root == n and root in dist1 and dist1[root] != rootTime:
                return rootTime
            # root's both best and second best found or second best == best i.e. not strictly second best
            if root in dist2 or (root in dist1 and rootTime == dist1[root]):
                continue
            # red-- wait till it becomes green
            if (rootTime // change) % 2 == 1:
                heappush(pq, (self.timeWhenGreen(rootTime, change), root))
                continue
            # green so proceed with usual djikstra
            if root not in dist1:
                dist1[root] = rootTime
            else:
                dist2[root] = rootTime
            for child in graph[root]:
                if (child not in dist1) or (child not in dist2):
                    heappush(pq, (rootTime + time, child))
        return -1
