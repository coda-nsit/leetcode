// Solved it myself. Just be patient and solve.
// In sorted rotated case always split the array in 2 parts based on which subarray is sorted.
class Solution {
    private boolean isMidSmallest(int[] nums, int mid) {
        if (nums.length == 1) {
            return true;
        }
        
        if (mid == 0) {
            return nums[mid] < nums[mid + 1];
        }
        
        if (mid == nums.length - 1) {
            return nums[mid] < nums[mid - 1];
        }
        
        return nums[mid] < nums[mid - 1] && nums[mid] < nums[mid + 1];
    }
    
    private int findMinBinarySearch(int[] nums, int start, int end) {
        int mid = (start + end) / 2;
        if (nums[mid] >= nums[start]) { // left side sorted, the equality is important [2, 1] case
            if (nums[start] <= nums[end]) {
                return nums[start];
            } else {
                return findMinBinarySearch(nums, mid + 1, end);
            }
        } else { // right side is sorted
            if (isMidSmallest(nums, mid)) {
                return nums[mid];
            } else {
                return findMinBinarySearch(nums, start, mid - 1);
            }
        }
    }
    
    public int findMin(int[] nums) {
        return findMinBinarySearch(nums, 0, nums.length - 1);
    }
}

// [3,4,5,1,2]
// [4,5,6,7,0,1,2]
// [11,13,15,17]
// [1,2,3,4,5]
// [3,4,5,1,2]
// [4,5,6,7,0,1,2]
// [1]
