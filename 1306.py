from queue import Queue
class Solution:
    def canReach(self, arr: List[int], start: int) -> bool:
        visited = set()
        
        bfsQ = Queue()
        bfsQ.put(start)
        
        while bfsQ.empty() is False:
            source = bfsQ.get()
            # print(source)
            if arr[source] == 0:
                return True
            d1, d2 = source + arr[source], source - arr[source]
            if d1 not in visited and 0 <= d1 < len(arr):
                bfsQ.put(d1)
            if d2 not in visited and 0 <= d2 < len(arr):
                bfsQ.put(d2)
            visited.add(source)
        return False
                
