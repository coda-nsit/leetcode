// Sorting by end times doesn't work
// [[0,0],[1,2],[5,5],[2,4],[3,3],[5,6],[5,6],[4,6],[0,0],[1,2],[0,2],[4,5]]
class Interval {
    public int start;
    public int end;
    
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
class Solution {
    private boolean isOverlap(Interval interval1, Interval interval2) {
        return !(interval2.start > interval1.end);
    }
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });
        
        // for (int i = 0; i < intervals.length; ++i) {
        //     System.out.println(intervals[i][0] + " " + intervals[i][1]);
        // }
        
        int start, end, currStart, currEnd;
        Interval pastInterval, currInterval;
        Stack<Interval> stack = new Stack();
        start = intervals[0][0];
        end = intervals[0][1];
        stack.push(new Interval(start, end));
        
        for (int i = 1; i < intervals.length; ++i) {
            currStart = intervals[i][0];
            currEnd = intervals[i][1];
            currInterval = new Interval(currStart, currEnd);
            pastInterval = stack.pop();
            
            if (isOverlap(pastInterval, currInterval)) {
                stack.push(new Interval(pastInterval.start, Math.max(pastInterval.end, currInterval.end)));
            } else {
                stack.push(pastInterval);
                stack.push(currInterval);
            }
        }
        
        List<Interval> answerList = new ArrayList<>();
        while (stack.size() > 0) {
            // System.out.println(stack.peek().start + " " + stack.peek().end);
            answerList.add(stack.pop());
        }
        int[][] answer = new int[answerList.size()][2];
        for (int i = 0; i < answerList.size(); ++i) {
            answer[i][0] = answerList.get(i).start;
            answer[i][1] = answerList.get(i).end;
        }
        return answer;
    }
}


// [[1,3],[2,6],[8,10],[15,18]]
// [[1,4],[4,5]]
// [[0,0],[1,2],[5,5],[2,4],[3,3],[5,6],[5,6],[4,6],[0,0],[1,2],[0,2],[4,5]]
// [[5,5],[1,3],[3,5],[4,6],[1,1],[3,3],[5,6],[3,3],[2,4],[0,0]]
// [[2,3],[4,5],[6,7],[8,9],[1,10]]
