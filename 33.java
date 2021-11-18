// I didn't see the solution. Just patiently solve it.
class Solution {
    private int binarySearch(int[] nums, int start, int end, int target) {
        int mid = (start + end) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        
        if (start > end) {
            return -1;
        }
        
        if (nums[mid] >= nums[start]) { // left subarray sorted, the equal is important try [3,1] 1
            if (target > nums[mid]) {
                return binarySearch(nums, mid + 1, end, target);
            } else {
                if (target < nums[start]) {
                    return binarySearch(nums, mid + 1, end, target);
                } else {
                    return binarySearch(nums, start, mid - 1, target);
                }
            }
        } else { // right subarray is sorted
            if (target > nums[mid]) {
                if (target > nums[end]) {
                   return binarySearch(nums, start, end - 1, target); 
                } else {
                    return binarySearch(nums, mid + 1, end, target); 
                }
            } else {
                return binarySearch(nums, start, mid - 1, target); 
            }
        }
    }
    public int search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }
}

// [4,5,6,7,0,1,2]
// 0
// [4,5,6,7,0,1,2]
// -1
// [1]
// 0
// [24,26,38,41,46,53,58,61,74,81,4,8,16,21]
// 8
// [24,26,38,41,46,53,58,61,74,81,4,8,16,21]
// 21
// [24,26,38,41,46,53,58,61,74,81,4,8,16,21]
// 22
// [24,26,38,41,46,53,58,61,74,81,4,8,16,21]
// 24
// [4,5,6,7,0,1,2]
// 0
// [5,1,2,3,4]
// 4
// [3,1]
// 2
// [3,1]
// 1
// [5,1,3]
// 5
