class Solution:
    def traverse(self, graph, root, visited, restricted):
        if root in visited or root in restricted:
            return 0
        visited.add(root)
        total = 0
        for child in graph[root]:
            total += self.traverse(graph, child, visited, restricted)
        return total + 1
        
        
    def reachableNodes(self, n: int, edges: List[List[int]], restricted: List[int]) -> int:
        graph = defaultdict(list)
        restricted = set(restricted)
        for edge in edges:
            graph[edge[0]].append(edge[1])
            graph[edge[1]].append(edge[0])
        
        visited = set()
        return self.traverse(graph, 0, visited, restricted)
