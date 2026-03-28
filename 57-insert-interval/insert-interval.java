class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        // Result list to accumulate merged intervals
        List<int[]> merged = new ArrayList<>();
        
        int index, n, newStart, newEnd;
        
        index = 0;
        n = intervals.length;

        newStart = newInterval[0];
        newEnd = newInterval[1];

        // 1. Add all intervals that end BEFORE the new interval starts. These intervals cannot overlap with newInterval.
        while (index < n && intervals[index][1] < newStart) {
            merged.add(intervals[index]);
            index++;
        }

        // 2. Merge all intervals that overlap with newInterval. Overlap occurs when interval.start <= newInterval.end.
        while (index < n && intervals[index][0] <= newEnd) {

            // Expand newInterval to include the overlapping interval
            newStart = Math.min(newStart, intervals[index][0]);
            newEnd   = Math.max(newEnd, intervals[index][1]);
            index++;
        }

        // Add the merged interval (the union of all overlaps)
        merged.add(new int[]{newStart, newEnd});

        // 3. Add all intervals that start AFTER the merged newInterval.
        while (index < n) {
            merged.add(intervals[index]);
            index++;
        }

        // Convert List<int[]> → int[][]
        return merged.toArray(new int[merged.size()][]);
    }
}