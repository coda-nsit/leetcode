/**
For any consecutive sequence, we just care about the first element of the sequence. So, of we see num and num - 1 exists, then we don't care about num.
As soon as we encounter the first element of a sequence i.e. num - 1 doesn't exist, we calculate how long is the sequence that starts with num.
 */
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numsSet = new HashSet<>(Arrays.stream(nums).boxed().collect(Collectors.toSet()));
        List<Integer> numsNoDups = new ArrayList<>(Arrays.stream(nums).boxed().collect(Collectors.toSet())); // optimization in case there are duplicates. No part of core algorithm.
        int maxi, currSeqLength;

        maxi = 0;
        for (int num: numsNoDups) {
            // new sequence can start
            if (!numsSet.contains(num - 1)) {
                currSeqLength = 1;
                while (numsSet.contains(num + currSeqLength)) {
                    currSeqLength += 1;
                }
                maxi = Math.max(maxi, currSeqLength);
            }
        }
        return maxi;
    }
}