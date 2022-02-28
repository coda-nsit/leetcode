"""
Basic Euler circuit algorithm.
"""
class Solution:
    def performEulerTraversal(self, graph, root, path):
        for idx in range(len(graph[root])):
            end, numEdges = graph[root][idx]
            if numEdges > 0:
                graph[root][idx] = (end, numEdges - 1)
                self.performEulerTraversal(graph, end, path)
        path.append(root)
        return
        
        
    def findItinerary(self, tickets: List[List[str]]) -> List[str]:
        graph = defaultdict(lambda: defaultdict(int))
        for start, end in tickets:
            graph[start][end] += 1
        
        for start in graph.keys():
            graph[start] = sorted((end, edges) for end, edges in graph[start].items())
        
        path = []
        self.performEulerTraversal(graph, "JFK", path)
        return path[::-1]
