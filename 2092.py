from heapq import heapify, heappop, heappush
from collections import defaultdict
class Solution:
    def findAllPeople(self, n: int, meetings: List[List[int]], firstPerson: int) -> List[int]:
        known_set = set()
        pq = []
        heapify(pq)
        graph = defaultdict(list)
        for meeting in meetings:
            source, destination, time = meeting
            graph[source].append((time, destination))
            graph[destination].append((time, source))
        
        known_set.add(0)
        known_set.add(firstPerson)
        time_elapsed = 0
        for neighbor in graph[firstPerson]:
            heappush(pq, neighbor)
        for neighbor in graph[0]:
            heappush(pq, neighbor)
        
        while len(pq) > 0:
            time, root = heappop(pq)
            if time >= time_elapsed and root not in known_set:
                known_set.add(root)
                time_elapsed = time
                for neighbor in graph[root]:
                    heappush(pq, neighbor)
        return list(known_set)
            
