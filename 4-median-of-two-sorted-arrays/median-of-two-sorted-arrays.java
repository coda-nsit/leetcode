public class Solution {

    /**
     * In a merge sort merge step just keep track of the last two picked values.
     * * O(M + N) time, O(1) extra space.
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1, n2, totalElements, i, j, mid1, mid2, prev, curr;

        n1 = nums1.length;
        n2 = nums2.length;
        totalElements = n1 + n2;

        // Middle indices in the conceptual merged array
        // Example: 0, 1, [2], 3, 4
        // totalElements = 5 -> mid2=2 (0-based), only need that element
        // Example: 0, 1, [2, 3], 4, 5
        // Example: totalElements = 6 -> mid1=2, mid2=3, need both
        mid1 = totalElements / 2 - 1; // needed for even total elements
        mid2 = totalElements / 2;

        // Two pointers for nums1 and nums2
        i = 0;
        j = 0;

        // We'll keep track of last two chosen elements from the merge process
        prev = 0;  // value at mergedIndex - 1
        curr = 0;  // value at mergedIndex

        // mergedIndex is the index in the conceptual merged array
        for (int mergedIndex = 0; mergedIndex <= mid2; mergedIndex++) {

            prev = curr;  // shift curr into prev before choosing next

            // Choose the smaller "next" element from nums1[i] or nums2[j]
            // Handle cases where one array is already exhausted.
            if (i < n1 && (j >= n2 || nums1[i] <= nums2[j])) {
                curr = nums1[i];
                i++;
            } else {
                curr = nums2[j];
                j++;
            }
        }

        // If total is odd, median is the middle element (curr at index mid2)
        if (totalElements % 2 == 1) {
            return curr;
        }

        // If total is even, median is average of mid1 and mid2 elements: prev and curr
        return ((double) prev + (double) curr) / 2.0;
    }
}