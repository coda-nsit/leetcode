class Solution {
public:
    int firstUniqChar(string s) {
        vector<int> charIdx(26, -1);
        for (int i = s.length() - 1; i >= 0; --i) {
            // if haven't seen before
            if (charIdx[s[i] - 'a'] == -1) {
                charIdx[s[i] - 'a'] = i;
            } else {
                charIdx[s[i] - 'a'] = -2;
            }
        }
        int mini = -1;
        for (int x: charIdx) {
            // cout << x << " ";
            if (x >= 0) {
                if (mini == -1) {
                    mini = x;
                } else {
                    mini = min(mini, x);
                }
            }
        }
        return mini;
    }
};
