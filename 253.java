// loccapollo

/*
** 1. Sort the list in increasing order of time.
** 2. Maintain a min queue based on ending times. 
** 3. loop the sorted intervals array and 
        if current start >= the min ending then no need for new room
        else assign a new room
        The main idea is that once we encounter such intervals that have start times greater than the min
        end times then we dont need a new room for that interval.
*/
class Interval {
    public int start;
    public int end;
    
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        
        Queue<Interval> runningSortedIntervals = new PriorityQueue<Interval>(intervals.length, (a, b) -> a.end - b.end);
        
        int totalRooms = 1;
        runningSortedIntervals.add(new Interval(intervals[0][0], intervals[0][1]));
        
        for (int i = 1; i < intervals.length; ++i) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            
            Interval leftMost = runningSortedIntervals.peek();

            if (start >= leftMost.end) {
                runningSortedIntervals.poll();
            } else {
                totalRooms += 1;
            }
            runningSortedIntervals.add(new Interval(start, end));
        }
        return totalRooms;
    }
}

// [[0,30],[5,10],[15,20]]
// [[7,10],[2,4]]
// [[6,15],[13,20],[6,17]]
