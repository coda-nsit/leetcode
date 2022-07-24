class Solution:
    def dfs(self, graph, answer, currPath, visited, source, dest):
        # not needed as the graph is DAG
        # if source in visited:
        #     return
        if source == dest:
            newCurrPath = copy.copy(currPath)
            answer.append(newCurrPath + [dest])
            return
        currPath.append(source)
        for child in graph[source]:
            self.dfs(graph, answer, currPath, visited, child, dest)
        # visited.add(source)
        currPath.pop()
        return
        
    
    def allPathsSourceTarget(self, graph: List[List[int]]) -> List[List[int]]:
        answer = []
        visited = set()
        self.dfs(graph, answer, [], visited, 0, len(graph) - 1)
        return answer
