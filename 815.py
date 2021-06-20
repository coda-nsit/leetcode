from collections import defaultdict
from queue import Queue
class Solution:
    def numBusesToDestination(self, routes: List[List[int]], source: int, target: int) -> int:
        if target == source:
            return 0
        # stores which all route indices have a stop/station
        reverse_idx = defaultdict(list)
        for idx, route in enumerate(routes):
            for stop in route:
                reverse_idx[stop].append(idx)
                
        
        bfs_queue = Queue()
        dist = defaultdict(int)
        # don't visit a bus's route if its already considered
        visited_idx = set()
        
        # put all the stops that can be accessed from the same bus as that of source in the queue and update their distance
        for route_idx in reverse_idx[source]:
            visited_idx.add(route_idx)
            for stop in routes[route_idx]:
                dist[stop] = 1
                bfs_queue.put(stop)
        if target in dist:
            return dist[target]
        
        
        while bfs_queue.empty() is False:
            start = bfs_queue.get()
            for route_idx in reverse_idx[start]:
                if route_idx not in visited_idx:
                    visited_idx.add(route_idx)
                    for stop in routes[route_idx]:
                        if stop not in dist:
                            bfs_queue.put(stop)
                            dist[stop] = dist[start] + 1
                            if stop == target:
                                return dist[stop]
        return -1
        
        
        
