// 1. Find the first element from the right side where the array stops becoming decreasing, [3,6,5,2,6,8,5,1] would be 6 -> 8. Lets call 6's index = i - 1
// 2. From 6 move right and find the nextGreater element in this case 8 and swap them. [3,6,5,2,8,6,5,1]
// 3. Sort the array from i till the end -> [3,6,5,2,8,(6,5,1)] becomes [3,6,5,2,8,1,5,6]
class Solution {
    public void nextPermutation(int[] nums) {
        int i, nextGreater, nextGreaterIndex, swapVariable;
        for (i = nums.length - 1; i >= 1; --i) {
            if (nums[i - 1] < nums[i]) {
                nextGreater = Integer.MAX_VALUE;
                nextGreaterIndex = -1;
                for (int j = i; j < nums.length; ++j) {
                    if (nums[j] > nums[i - 1] && nextGreater > nums[j]) {
                        nextGreater = nums[j];
                        nextGreaterIndex = j;
                    }
                }
                swapVariable = nums[i - 1];
                nums[i - 1] = nums[nextGreaterIndex];
                nums[nextGreaterIndex] = swapVariable;
                break;
            }
        }
        Arrays.sort(nums, i, nums.length);
    }
}
