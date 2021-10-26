// dp[i] = {the jump sizes that can be used to reach i}
// 1. base case: dp[0] = {0}
// 2. start from 0 and see what all steps can be taken and take all the steps i.e. 
//    dp[1] = {1}
//    dp[2] = {1 (from 1)}
//    dp[3] = {1 (from 2), 2 (from 1)}
// Note the solution doesn't grow to 10^9
class Solution {
    public boolean canCross(int[] stones) {
        Map<Integer, Set<Integer>> dp = new HashMap<>();
        
        // base case
        dp.put(0, new HashSet<Integer>());
        dp.get(0).add(0);
        
        for (int i = 0; i < stones.length; ++i) {
            int stonePosition = stones[i];
            Set<Integer> possibleJumps = dp.get(stonePosition);
            if (possibleJumps != null) {
                for (int possibleJump: possibleJumps) {
                    // k - 1
                    if (stonePosition + possibleJump - 1 > stonePosition) {
                        if (dp.containsKey(stonePosition + possibleJump - 1)) {
                            dp.get(stonePosition + possibleJump - 1).add(possibleJump - 1);
                        } else {
                            dp.put(stonePosition + possibleJump - 1, new HashSet<>());
                            dp.get(stonePosition + possibleJump - 1).add(possibleJump - 1);
                        }
                    }
                    // k
                    if (stonePosition + possibleJump > stonePosition) {
                        if (dp.containsKey(stonePosition + possibleJump)) {
                            dp.get(stonePosition + possibleJump).add(possibleJump);
                        } else {
                            dp.put(stonePosition + possibleJump, new HashSet<>());
                            dp.get(stonePosition + possibleJump).add(possibleJump);
                        }
                    }
                    // k + 1
                    if (stonePosition + possibleJump + 1 > stonePosition) {
                        if (dp.containsKey(stonePosition + possibleJump + 1)) {
                            dp.get(stonePosition + possibleJump + 1).add(possibleJump + 1);
                        } else {
                            dp.put(stonePosition + possibleJump + 1, new HashSet<>());
                            dp.get(stonePosition + possibleJump + 1).add(possibleJump + 1);
                        }
                    }
                }
                if (dp.containsKey(stones[stones.length - 1]) == true) {
                    return true;
                }
            }
                
        }
        return false;
    }
}
// [0,1,3,5,6,8,12,17]
// [0,1,2,3,4,8,9,11]
// [0,1,2,3,6,7,8,11]
