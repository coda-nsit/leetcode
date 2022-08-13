/*
Not a hard problem. AC in time > s.length * word[i].length
For every starting index left in s
    find if s[left : words.length * words[i].length] can be formed from words
*/
class Solution {
public:
    vector<int> findSubstring(string s, vector<string>& words) {
        int seedWordLength, n;
        map<string, int> seedWords;
        vector<int> answer;
        
        n = s.length();
        seedWordLength = words[0].length();
        for (string word: words) {
            if (seedWords.find(word) == seedWords.end()) {
                seedWords[word] = 0;
            }
            seedWords[word] += 1;
        }
        
        for(int left = 0; left + words.size() * seedWordLength <= n; left++) {
            string windowString;
            map<string, int> seen;
            for (int j = left; j + seedWordLength <= n; j += seedWordLength) {
                windowString = s.substr(j, seedWordLength);
                if (seedWords.find(windowString) != seedWords.end()) {
                    if (seen.find(windowString) == seen.end()) {
                        seen[windowString] = 0;
                    }
                    seen[windowString] += 1;
                    if (seen[windowString] > seedWords[windowString]) {
                        break;
                    }
                    if (seen.size() == seedWords.size()) {
                        bool flag = true;
                        for (auto [k, v]: seen) {
                            if (v != seedWords[k]) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            answer.push_back(left);
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }
        return answer;
    }
};
