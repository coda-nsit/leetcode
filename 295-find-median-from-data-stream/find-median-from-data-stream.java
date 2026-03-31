/**
Maintain 2 PQs
MaxQ          MinQ
(1 6 9 11 18) (23 28 34 51 60)

For every number we maintain 2 invariants,
1. lowerHalfMaxHeap.size() is either:
    a. equal to upperHalfMinHeap.size(), OR
    b. exactly 1 bigger (so median is easy to read)
2. Every element in lowerHalfMaxHeap ≤ every element in upperHalfMinHeap
 */

class MedianFinder {

    
    /**
     * Max-heap (largest element on top) that stores the LOWER half of the numbers.
     * Example: if numbers are [1, 2, 3, 4, 5],
     * lower half might store [1, 2, 3] -> top is 3.
     */
    private final PriorityQueue<Integer> lowerHalfMaxHeap;

    /**
     * Min-heap (smallest element on top) that stores the UPPER half of the numbers.
     * Example: if numbers are [1, 2, 3, 4, 5],
     * upper half might store [4, 5] -> top is 4.
     */
    private final PriorityQueue<Integer> upperHalfMinHeap;


    public MedianFinder() {
        lowerHalfMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
        upperHalfMinHeap = new PriorityQueue<>();

    }
    
    
    /**
     *
     * Strategy:
     *  1) Dont consider of maintaining the lowerHalfMaxHeap.size() == upperHalfMinHeap.size() or upperHalfMinHeap.size() + 1. Just insert the number in whichever queue it should be put.
     *  2) Rebalance heaps so size difference never exceeds 1
     *  3) Maintain ordering: all lowerHalf <= all upperHalf
     */

    public void addNum(int num) {
        
        // Blindly put in lowerHalfMaxHeap or upperHalfMinHeap first.
        // This might lead to imbalance of size which we will deal later in code.
        if (lowerHalfMaxHeap.isEmpty() || num <= lowerHalfMaxHeap.peek()) {
            lowerHalfMaxHeap.add(num);
        } else {
            upperHalfMinHeap.add(num);
        }

        // rebalance 
        // We want:
        //   lowerHalf.size == upperHalf.size or lowerHalf.size == upperHalf.size + 1
        // If lowerHalfMaxHeap has too many elements (more than 1 extra), move one to upperHalf
        if (lowerHalfMaxHeap.size() > upperHalfMinHeap.size() + 1) {
            upperHalfMinHeap.add(lowerHalfMaxHeap.poll());
        }

        // If upperHalf has more elements, move one back to lowerHalf
        if (upperHalfMinHeap.size() > lowerHalfMaxHeap.size()) {
            lowerHalfMaxHeap.add(upperHalfMinHeap.poll());
        }
    }
    
    
    /**
     * Case A: total count is odd
     *   -> lowerHalf has one more element
     *   -> median is top of lowerHalf
     *
     * Case B: total count is even
     *   -> both heaps have same size
     *   -> median is average of two middle values:
     *      max(lowerHalf) and min(upperHalf)
     */

    public double findMedian() {
        
        int totalCount;
        totalCount = lowerHalfMaxHeap.size() + upperHalfMinHeap.size();

        if (totalCount == 0) {
            throw new IllegalStateException("No numbers are available to find median.");
        }

        // Odd count -> lowerHalfMaxHeap has one extra element
        if (totalCount % 2 == 1) {
            return lowerHalfMaxHeap.peek();
        }

        // Even count -> average of two middle elements
        return (lowerHalfMaxHeap.peek() + upperHalfMinHeap.peek()) / 2.0;

    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */