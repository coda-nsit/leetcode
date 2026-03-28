/**
Author: @Rishab Banerjee

Standard Kadane but here even if all elements are negative we return the max value instead of 0.
For that we keep track of any non-negative element being present in the array using nonNegPresent.
 */
class Solution {
    public int maxSubArray(int[] nums) {
        int currMax, maxi;
        boolean nonNegPresent;
        maxi = nums[0];
        nonNegPresent = false;
        currMax = 0;
        for (int num: nums) {
            if (num >= 0) {
                nonNegPresent = true;
            }
            currMax += num;
            if (currMax < 0) {
                currMax = 0;
            }
            // Compare with currMax only if some non-negative number is present or else currMax = 0 and maxi will become 0, even if all elements in nums are negative.
            if (nonNegPresent) {
                maxi = Math.max(currMax, maxi);
            } else {
                maxi = Math.max(num, maxi);
            }
        }
        return maxi;
    }
}