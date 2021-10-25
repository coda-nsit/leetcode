/*
    https://www.youtube.com/watch?v=iVKfN1zJSxI
    1. find the point where the array stops being increasing.
    2. find the point where the array stops being decreasing.
    3. find the smallest and largest elements in this range.
    4. find the location where we should be placing the smallest and the largest elements in the sorted array. This gives the length.
*/
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int leftInflextion, rightInflextion;
        leftInflextion = 0;
        rightInflextion = nums.length - 1;
        boolean isSorted = true;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                leftInflextion = i;
                isSorted = false;
                break;
            }
        }
        
        for (int i = nums.length - 1; i > 0; --i) {
            if (nums[i] < nums[i - 1]) {
                rightInflextion = i;
                break;
            }
        }
        
        // sorted
        if (leftInflextion == 0 && rightInflextion == nums.length - 1 && isSorted){
            return 0;
        }
        
        // System.out.println(leftInflextion + " " + rightInflextion);
        
        // find the largest and the smallest elements in the inflextion range
        int smallest = nums[leftInflextion];
        int largest = nums[leftInflextion];
        for (int i = leftInflextion + 1; i <= rightInflextion; ++i) {
            smallest = Math.min(smallest, nums[i]);
            largest = Math.max(largest, nums[i]);
        }
        
        // find where the largest and the smallest should be thats the range to be sorted
        int left, right;
        left = 0;
        right = nums.length - 1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > smallest) {
                left = i;
                break;
            }
        }
        for (int i = nums.length - 1; i >= 0; --i) {
            if (nums[i] < largest) {
                right = i;
                break;
            }
        }
        return right - left + 1;
    }
}

// [6,5,4,3,2,1]
// [1,3,2,4,5]
// [2,6,4,8,10,9,15]
// [1,2,3,4]
// [1]
// [2, 1]
