class Solution {
public:
    int uniqueMorseRepresentations(vector<string>& words) {
        set<string> morseWords;
        vector<string> charToMorse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        
        for (string word: words) {
            string morseWord = "";
            for (char c: word) {
                // cout << c << " ";
                morseWord += charToMorse[c - 'a'];
            }
            
            morseWords.insert(morseWord);
        }
        return morseWords.size();
    }
};
