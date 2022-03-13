import heapq
class Solution:
    def djkstra(self, graph, source, distance):
        pq = []
        heapq.heapify(pq)
        heapq.heappush(pq, (0, source))
        while len(pq) > 0:
            _, source = heapq.heappop(pq)
            # the following line prevents the TLE cause if a node has been visited before all its children have already been visited
            if distance[source] < _:
                continue
            for point in graph[source]:
                child, edgeWeight = point
                if distance[child] > distance[source] + edgeWeight:
                    distance[child] = distance[source] + edgeWeight
                    heapq.heappush(pq, (distance[child], child))
        return
        
    def minimumWeight(self, n: int, edges: List[List[int]], src1: int, src2: int, dest: int) -> int:
        graph = defaultdict(list)
        inverseGraph = defaultdict(list)
        for edge in edges:
            s, d, w = edge
            graph[s].append((d, w))
            inverseGraph[d].append((s, w))
        
        distanceFromSrc1 = {i: float("inf") for i in range(n)}
        distanceFromSrc2 = {i: float("inf") for i in range(n)}
        distanceFromDest = {i: float("inf") for i in range(n)}
        distanceFromSrc1[src1] = 0
        distanceFromSrc2[src2] = 0
        distanceFromDest[dest] = 0
        
        self.djkstra(graph, src1, distanceFromSrc1)
        self.djkstra(graph, src2, distanceFromSrc2)
        self.djkstra(inverseGraph, dest, distanceFromDest)
        
        # print(distanceFromSrc1)
        # print(distanceFromSrc2)
        # print(distanceFromDest)
        
        totalDist = float("inf")
        for middle in range(n):
            totalDist = min(totalDist, distanceFromSrc1[middle] + distanceFromSrc2[middle] + distanceFromDest[middle])
        if totalDist != float("inf"):
            return totalDist
        return -1
        
        
