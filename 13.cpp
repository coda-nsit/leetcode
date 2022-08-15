/*
Very easy question, just follow what is being said.
*/
class Solution {
public:
    int romanToInt(string s) {
        map<char, int> symbolToVal;
        symbolToVal['I'] = 1;
        symbolToVal['V'] = 5;
        symbolToVal['X'] = 10;
        symbolToVal['L'] = 50;
        symbolToVal['C'] = 100;
        symbolToVal['D'] = 500;
        symbolToVal['M'] = 1000;
        
        int total = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s[i] == 'I' and i <= s.length() - 1 and (s[i + 1] == 'V' || s[i + 1] == 'X')) {
                if (s[i + 1] == 'V') {
                    total += 4;
                } else {
                    total += 9;
                }
                i += 1;
            } else if (s[i] == 'X' and i <= s.length() - 1 and (s[i + 1] == 'L' || s[i + 1] == 'C')) {
                if (s[i + 1] == 'L') {
                    total += 40;
                } else {
                    total += 90;
                }
                i += 1;
            } else if (s[i] == 'C' and i <= s.length() - 1 and (s[i + 1] == 'D' || s[i + 1] == 'M')) {
                if (s[i + 1] == 'D') {
                    total += 400;
                } else {
                    total += 900;
                }
                i += 1;
            } else {
                total += symbolToVal[s[i]];
            }
        }
        return total;
    }
};
