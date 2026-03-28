/**
Solved using AI. Python version I had solved myself.

Slide pattern over text. At every new character, remove the old character which is now out of sliding window and add the new character. After adding and deleting update the frequency counts.
1. Maintain two frequency arrays of size 26:
    a. one for s1
    b. one for the current sliding window in s2
2. Keep a variable matchingCharCount that tracks how many of the 26 character frequencies currently match between the two arrays.
3. Initialize the first window of size s1.length() and compute how many character frequencies match.
4. Slide the window across s2 one character at a time:
    a. add the new right character
    b. remove the old left character
    c. update matchingCharCount intelligently i.e. if the character ejected from the sliding window was causing a match but now doesnt matchingCharCount-- and if adding the new chracter from right now causes a match matchingCharCount++
    d. If at any point matchingCharCount == 26, it means all character frequencies match, so the window is a permutation of s1.

 */
public class Solution {

    public boolean checkInclusion(String pattern, String text) {

        // If pattern is longer, no permutation can fit inside text
        if (pattern.length() > text.length()) {
            return false;
        }

        // Frequency arrays for characters in pattern and current window in text
        int[] patternFreq = new int[26];
        int[] windowFreq = new int[26];

        // Build initial frequency counts for pattern and the first window
        for (int i = 0; i < pattern.length(); i++) {
            patternFreq[pattern.charAt(i) - 'a']++;
            windowFreq[text.charAt(i) - 'a']++;
        }

        // Count how many character frequencies currently match
        int matchingCharCount = 0;
        for (int i = 0; i < 26; i++) {
            if (patternFreq[i] == windowFreq[i]) {
                matchingCharCount++;
            }
        }

        // Slide the window across the text, removing the "left" character and adding the "left + pattern.length()"" character
        for (int left = 0; left < text.length() - pattern.length(); left++) {

            // If all 26 character frequencies match, we found a permutation
            if (matchingCharCount == 26) {
                return true;
            }

            // Character entering the window (right side)
            int rightCharIndex = text.charAt(left + pattern.length()) - 'a';

            // Character leaving the window (left side)
            int leftCharIndex = text.charAt(left) - 'a';

            // Add the new right character to the window
            windowFreq[rightCharIndex]++;
            if (windowFreq[rightCharIndex] == patternFreq[rightCharIndex]) {
                matchingCharCount++;
            } else if (windowFreq[rightCharIndex] == patternFreq[rightCharIndex] + 1) {     // It used to match, but now it doesn't because of windowFreq[rightCharIndex]++
                matchingCharCount--;
            }

            // Remove the old left character from the window
            windowFreq[leftCharIndex]--;
            if (windowFreq[leftCharIndex] == patternFreq[leftCharIndex]) {
                matchingCharCount++;
            } else if (windowFreq[leftCharIndex] == patternFreq[leftCharIndex] - 1) {       // It used to match, but now it doesn't because of windowFreq[rightCharIndex]++
                matchingCharCount--;
            }
        }

        // Final check for the last window
        return matchingCharCount == 26;
    }
}
