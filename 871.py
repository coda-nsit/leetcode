'''
Greedy solution. Don't refill until the oil is over i.e. assume that gas won't be put at any station, rather put the gas value in a max priority queue.
Whenever the gas gets over i.e. fuel_left < 0, pop from the priority queue until the fuel_left >= 0
'''
import heapq
class Solution:
    def minRefuelStops(self, target: int, startFuel: int, stations: List[List[int]]) -> int:
        stations.append([target, 0])
        corrected_stations = [stations[0]]
        for idx in range(1, len(stations)):
            corrected_stations.append([stations[idx][0] - stations[idx - 1][0], stations[idx][1]])
        fuel_left = startFuel
        stops = 0
        pq = []
        heapq.heapify(pq)
        for station in corrected_stations:
            fuel_left -= station[0]
            while fuel_left < 0:
                if len(pq) == 0:
                    return -1
                fuel_left -= heapq.heappop(pq)
                stops += 1
            heapq.heappush(pq, -station[1])
        return stops
