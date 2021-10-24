/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/

// loccapollo
/*
1. It doesn't matter how many employees are there. What matters is the intervals, so first convert the list of list to a list of intervals.
2. Sort by start times.
3. start = interval[0].start, end = interval[0].end
4. for all intervals if the current intervals start > the latest end then we have found a free interval
*/
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Pair<Integer, Integer>> flatEmployeeFreeTime = new ArrayList<>();
        for (int i = 0; i < schedule.size(); ++i) {
            for (int j = 0; j < schedule.get(i).size(); ++j) {
                Interval interval = schedule.get(i).get(j);
                flatEmployeeFreeTime.add(new Pair<Integer, Integer>(interval.start, interval.end));
            }
        }
        Collections.sort(flatEmployeeFreeTime, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(final Pair<Integer, Integer> o1, final Pair<Integer, Integer> o2) {
                if (o1.getKey() != o2.getKey()) {
                    return o1.getKey() - o2.getKey();
                }
                return o1.getValue() - o2.getValue();
            }
        });
        
        // for(int i = 0; i < flatEmployeeFreeTime.size(); ++i) {
        //     System.out.println(flatEmployeeFreeTime.get(i).getKey() + " " + flatEmployeeFreeTime.get(i).getValue());
        // }
        
        List<Interval> answer =  new ArrayList<Interval>();
        
        int start = flatEmployeeFreeTime.get(0).getKey();
        int end = flatEmployeeFreeTime.get(0).getValue();
        
        for (int i = 1; i < flatEmployeeFreeTime.size(); ++i) {
            Pair<Integer, Integer> interval = flatEmployeeFreeTime.get(i);
            int currentStart = interval.getKey();
            int currentEnd = interval.getValue();
            if (currentStart > end) {
                answer.add(new Interval(end, currentStart));
                start = currentStart;
                end = currentEnd;
            } else {
                end = Math.max(currentEnd, end);
            }
        }
        return answer;
    }
}

// [[[1,2],[5,6]],[[1,3]],[[4,10]]]
// [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
// [[[45,56],[89,96]],[[5,21],[57,74]]]
