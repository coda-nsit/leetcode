/**
  * 1. We want to split the arrays into left half which should have (m + n + 1) / 2. +1 is needed for odd total length
  * 2. All elements in left half ≤ all elements in right half.
  * 3. We do this by choosing a partition i in A and j in B:
        i = how many elements we take from A into the left half
        j = how many we take from B into the left half = (m + n + 1) / 2 - i

        For example,
        x -> x1     x2   |    x3      x4      x5      x6
        y -> y1     y2      y3      y4      y5   |   y6      y7      y8
        
        The goal is to find a i, j like above because both sides have 7 elements using binary search.
        x1, x2, y1, y2, y3, y4, y5 === x3, x4, x5, x6, y6, y7, y8
        
        Such a partition will have the characteristics
        1. x2 <= y6  ===> (x1 <= x2) <> (y1 <= y2 <= y3 <= y4 <= y5) <= y6
        2. y5 <= x3  ===> (x1 <= x2) <> (y1 <= y2 <= y3 <= y4 <= y5) <= x3
        Both of these together mean,
        (x1 <= x2 <>  y1 <= y2 <= y3 <= y4 <= y5) <= (x3 <= x4 <= x5 <= x6 <> y6 <= y7 <= y8)
        
        But now the problem is we dont know x2 <> y5 and x3 <> y6 hence, the increasing order can be
        x1 y1 y2 y3 y4 x2 y5 or,
        x1 y1 y2 y3 y4 y5 x2 or,
        x1 x2 y1 y2 y3 y4 y5 etc.
        
        Once we find such a partition, the elements that matter are x2, x3, y5, y6 because the median will be around these elements.
        Now median if number of elements is even,
        median = avg(max(x2, y5), min(x3, y6))
        and if number of elements is odd,
        median = max(x2, y5)


  * Returns median of two sorted arrays in O(log(min(m,n))) time.
 */
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        // Always binary search on the smaller array to guarantee log(min(m,n)).
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int[] smaller = nums1;
        int[] larger  = nums2;

        int m = smaller.length;
        int n = larger.length;

        // Total number of elements that should be on the LEFT side of the partition.
        int leftPartitionSize = (m + n + 1) / 2;

        // Binary search boundaries for partition index in 'smaller'
        int low = 0;
        int high = m;

        while (low <= high) {

            // i = partition index in smaller array
            int i = (low + high) / 2;

            // j = partition index in larger array chosen so left side has leftPartitionSize elements
            int j = leftPartitionSize - i;

            // Now we will check if the i and j satify the condition of equally splitting.

            
            // Compute boundary values around the partition.
            // Use +/- infinity when partition touches array ends.
            int smallerLeftMax  = (i == 0) ? Integer.MIN_VALUE : smaller[i - 1];
            int smallerRightMin = (i == m) ? Integer.MAX_VALUE : smaller[i];

            int largerLeftMax   = (j == 0) ? Integer.MIN_VALUE : larger[j - 1];
            int largerRightMin  = (j == n) ? Integer.MAX_VALUE : larger[j];

            // Check if we found the correct partition:
            // left max values must be <= right min values across arrays
            // x2 <= y6 && y5 <= x3
            boolean isPartitionCorrect =
                    (smallerLeftMax <= largerRightMin) &&
                    (largerLeftMax  <= smallerRightMin);

            if (isPartitionCorrect) {

                // If total length is odd, median is the max of left side.
                // max(x2, y5)
                if ((m + n) % 2 == 1) {
                    return Math.max(smallerLeftMax, largerLeftMax);
                }

                // If total length is even, median is avg of:
                // max(left side) and min(right side)
                int leftMax  = Math.max(smallerLeftMax, largerLeftMax);
                int rightMin = Math.min(smallerRightMin, largerRightMin);

                return (leftMax + rightMin) / 2.0;

            } else if (smallerLeftMax > largerRightMin) {
                // We took too many from smaller -> move partition left
                high = i - 1;
            } else {
                // We took too few from smaller -> move partition right
                low = i + 1;
            }
        }

        // If arrays are sorted, we should never get here.
        throw new IllegalArgumentException("Input arrays must be sorted.");
    }
}