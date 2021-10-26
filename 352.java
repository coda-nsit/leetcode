class Interval {
    public int start;
    public int end;
    
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class SummaryRanges {
    // [start] = [start, end]
    private TreeMap<Integer, Interval> startToInterval;
    
    public SummaryRanges() {
        startToInterval = new TreeMap<>();
    }
    
    public void addNum(int val) {
        // 4 cases:
        // 1. [4, 8] new number is [7] -> ignore
        // 2. new number doesn't lie in any range
        // 3. [4, 8] new number is [3] -> [3, 8]
        // 4. [4, 8] new number is [9] -> [4, 9]
        
        // 1. [4, 8] new number is [4] -> ignore
        if (startToInterval.containsKey(val)) {
            return;
        }
        
        // get the lower and upper start times
        Interval lowerInterval = null;
        Interval higherInterval = null;
        int lowerStart, lowerEnd, higherStart, higherEnd;
        lowerStart = lowerEnd = higherStart = higherEnd = 0;
        
        if (startToInterval.lowerKey(val) != null) {
            lowerInterval = startToInterval.get(startToInterval.lowerKey(val));
            lowerStart = lowerInterval.start;
            lowerEnd = lowerInterval.end;
        }
        if (startToInterval.higherKey(val) != null) {
            higherInterval = startToInterval.get(startToInterval.higherKey(val));
            higherStart = higherInterval.start;
            higherEnd = higherInterval.end;
        }
        
        // first item in tree
        if (lowerInterval == null && higherInterval == null) {
            startToInterval.put(val, new Interval(val, val));
            return;
        } 
        
        // System.out.println("val:" + val + " lowerStart:" + lowerStart + " lowerEnd:" + lowerEnd + " higherStart:" + higherStart + " higherEnd:" + higherEnd);
        
        // lowest item or in the lowest interval
        if(lowerInterval == null) {
            if (val == higherStart - 1 || val == higherStart) {
                startToInterval.put(val, new Interval(val, higherEnd));
                startToInterval.remove(higherStart);
            } else {
                startToInterval.put(val, new Interval(val, val));
            }
            return;
        }
        // highest item or in the highest interval
        else if(higherInterval == null) {
            if (val >= lowerStart && val <= lowerEnd + 1) {
                startToInterval.put(lowerStart, new Interval(lowerStart, Math.max(val, lowerEnd)));
            } else {
                startToInterval.put(val, new Interval(val, val));
            }
            return;
        }
        // both ways merge needed
        else {
            if((val >= lowerStart && val <= lowerEnd) || (val >= higherStart && val <= higherEnd)) {
                return;
            } else if (val == lowerEnd + 1 && val == higherStart - 1) {
                startToInterval.remove(higherStart);
                startToInterval.put(lowerStart, new Interval(lowerStart, higherEnd));
            } else if (val == lowerEnd + 1 || val == lowerEnd) {
                startToInterval.put(lowerStart, new Interval(lowerStart, val));
            } else if (val == higherStart - 1 || val == higherStart) {
                startToInterval.remove(higherStart);
                startToInterval.put(val, new Interval(val, higherEnd));
            } else {
                startToInterval.put(val, new Interval(val, val));
            }
        }
    }
    
    public int[][] getIntervals() {
        int[][] answer = new int[startToInterval.size()][2];
        int index = 0;
        for (int intervalStart: startToInterval.keySet()) {
            answer[index][0] = startToInterval.get(intervalStart).start;
            answer[index++][1] = startToInterval.get(intervalStart).end;
        }
        return answer;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */

// ["SummaryRanges","addNum","getIntervals","addNum","getIntervals"]
// [[],[1],[],[0],[]]
// ["SummaryRanges","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals"]
// [[],[1],[],[3],[],[7],[],[2],[],[6],[]]
