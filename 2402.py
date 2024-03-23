from heapq import heappush, heappop, heapify

class Solution:
    def mostBooked(self, n: int, meetings: List[List[int]]) -> int:
        meetings = sorted(meetings)
        # print("meetings:", meetings)
        pq = []
        pqAvailableRooms = [i for i in range(n)]
        heapify(pqAvailableRooms)
        roomMeetingCount = defaultdict(int)

        # schedule the 1st meeting
        roomNumber = heappop(pqAvailableRooms)
        heappush(pq, (meetings[0][1], roomNumber))
        roomMeetingCount[roomNumber] = 1

        # schedule the ith meeting
        for i in range(1, len(meetings)):
            iMeetingStartTime, iMeetingEndTime = meetings[i]
            while len(pq) > 0:
                endTime, roomNumber = heappop(pq)
                # no need to schedule now, make this room available first
                if endTime <= iMeetingStartTime:
                    heappush(pqAvailableRooms, roomNumber)
                    continue
                # schedule now
                else:
                    heappush(pq, (endTime, roomNumber))
                    break
            # get the smallest available room and schedule the meeting
            if len(pqAvailableRooms) > 0:
                roomNumber = heappop(pqAvailableRooms)
                heappush(pq, (iMeetingEndTime, roomNumber))
                roomMeetingCount[roomNumber] += 1
            else:
                endTime, roomNumber = heappop(pq)
                heappush(pq, (endTime + iMeetingEndTime - iMeetingStartTime, roomNumber))
                roomMeetingCount[roomNumber] += 1
        
        flatCountRoomNumber = sorted([(val, roomNumber) for roomNumber, val in roomMeetingCount.items()], key=lambda x: (-x[0], x[1]))
        # print("flatCountRoomNumber:", flatCountRoomNumber)
        return flatCountRoomNumber[0][1]

        
        
