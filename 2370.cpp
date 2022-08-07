class Solution {
public:
    int longestIdealString(string s, int k) {
        int n = s.length();
        int dp[n][26];
        
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 26; ++j) {
                dp[i][j] = 0;
            }
        }
        dp[0][s[0] - 'a'] = 1;
        
        for (int i = 1; i < n; ++i) {
            int numericChar = s[i] - 'a';
            for (int j = 0; j < 26; ++j) {
                if (numericChar != j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    for (int l = max(0, numericChar - k); l < min(numericChar + k + 1, 26); ++l) {
                        dp[i][j] = max(dp[i][j], dp[i - 1][l] + 1);
                    }
                }
            }
        }
        
        int maxi = 1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 26; ++j) {
                maxi = max(maxi, dp[i][j]);
            }
        }
        return maxi;
    }
};

/*
"eduktdb"
15


"azaza"
25
5
*/
