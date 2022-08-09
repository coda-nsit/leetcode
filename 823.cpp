long MOD = 10e8+7;
class Solution {
public:
    /*
        Returns the number of vaid binary trees with root as arr[idx]
        1. make arr[idx] the root
        2. iterate over arr and try to fix the left child
        3. find the right child
        4. recurse on left and right child
    */
    int fixRootAndRecurse(vector<int>& arr, map<int, int>& elemIdx, map<int, int>& dp, int idx) {
        if (dp.find(idx) != dp.end()) {
            return dp[idx];
        }
        int leftIdx, rightIdx;
        long long l, r, total;
        total = 1;
        for (int i = 0; i <= idx; ++i) {
            int toFind = arr[idx] / arr[i];
            if (arr[idx] % arr[i] == 0 && elemIdx.find(toFind) != elemIdx.end()) {
                leftIdx = i;
                rightIdx = elemIdx[toFind];
                l = fixRootAndRecurse(arr, elemIdx, dp, leftIdx);
                r = fixRootAndRecurse(arr, elemIdx, dp, rightIdx);
                total += l * r;
                total %= MOD;
            }
        }
        dp[idx] = total;
        return total;
    }
    
    int numFactoredBinaryTrees(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        map<int, int> elemIdx;
        map<int, int> dp;
        for (int i = 0; i < arr.size(); ++i) {
            elemIdx[arr[i]] = i;
        }
        long long total = 0;
        for (int i = 0; i < arr.size(); ++i) {
            total += fixRootAndRecurse(arr, elemIdx, dp, i);
            total %= MOD;
        }
        return total % MOD;
    }
};
