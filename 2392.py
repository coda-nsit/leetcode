from queue import Queue
class Solution:
    def topoBfs(self, graph, k):
        bfsQ = Queue()
        indegree = {i : 0 for i in range(1, k + 1)}
        for node in graph:
            for child in graph[node]:
                indegree[child] += 1
        for i in range(1, k + 1):
            if indegree[i] == 0:
                bfsQ.put(i)
        topoOrder = []
        while bfsQ.empty() is False:
            root = bfsQ.get()
            topoOrder.append(root)
            for child in graph[root]:
                indegree[child] -= 1
                if indegree[child] == 0:
                    bfsQ.put(child)
        return topoOrder
        
    def assignPoints(self, topoOrder, assignments, k):
        for point in topoOrder:
            assignments[point] = k
            k -= 1
        return
        
    def buildMatrix(self, k: int, rowConditions: List[List[int]], colConditions: List[List[int]]) -> List[List[int]]:
        # form graph
        graphR = defaultdict(list)
        graphC = defaultdict(list)
        for x, y in rowConditions:
            graphR[y].append(x)
        for x, y in colConditions:
            graphC[y].append(x)
                
        # find topological order. If all nodes are not present in the topological ordering then there is a cycle.
        rowTopoOrder = self.topoBfs(graphR, k)
        colTopoOrder = self.topoBfs(graphC, k)
        if len(rowTopoOrder) < k or len(colTopoOrder) < k:
            return []
        
        # assign values
        assignmentsRow = {}
        self.assignPoints(rowTopoOrder, assignmentsRow, k)
        assignmentsCol = {}
        self.assignPoints(colTopoOrder, assignmentsCol, k)
        
        answer = [[0] * k for i in range(k)]
        for i in range(1, k + 1):
            answer[assignmentsRow[i] - 1][assignmentsCol[i] - 1] = i
        return answer
            
        
        
        
        
