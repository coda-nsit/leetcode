// https://www.youtube.com/watch?v=L-EaPf5tD5A
// nums = [1, 1, 2, 2, 3, 5]
// 1 -> 001
// 2 -> 010
// 3 -> 011
// 5 -> 101
// 1. Take a xor of all elements in the array. This results in 3 (011) xor 5 (101) = 6 (110). We know that 3 and 5 differ at 1st and the 2nd MSBs. We can work with any set bit. Lets work with 2nd MSB.
// 2. Create 2 buckets -> one which contains the numbers with 2nd MSB is 0 (1, 5), second in which the numbers with 2nd MSB is 1 (2, 3).
// 3. 
// i.   0Buck = 1Bucket = 0
// ii.  for num in nums:
//          if num has bit 0:
//              0Bucket ^= num
//          else:
//              1Bucket ^= num
// iii. return 0Bucket, 1Bucket
class Solution {
    public int[] singleNumber(int[] nums) {
        int xorNums, rightmostSetBit, bucket0, bucket1;
        // 1. get the xor of all elements
        xorNums = 0;
        for (int num: nums) {
            xorNums ^= num;
        }
        
        // 2. get rightmost set bit of the xored number
        rightmostSetBit = 1;
        while ((xorNums & rightmostSetBit) == 0) {
            rightmostSetBit =  rightmostSetBit << 1;
        }
        
        // 3. sperate into 2 buckets
        bucket0 = bucket1 = 0;
        for (int num: nums) {
            if ((num & rightmostSetBit) == 0) {
                bucket0 ^= num;
            } else {
                bucket1 ^= num;
            }
        }
        
        return new int[] {bucket0, bucket1};
    }
}
