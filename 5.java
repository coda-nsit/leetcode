import java.lang.Math;
class Solution {
    private Integer findPalindromeLength(String s, int startIndex) {
        int longestPalindromeLengthOdd, longestPalindromeLengthEven;
        longestPalindromeLengthOdd = 1;
        for (int i = startIndex - 1, j = startIndex + 1; i >= 0 && j < s.length(); --i, ++j) {
            if (s.charAt(i) == s.charAt(j)) {
                longestPalindromeLengthOdd += 2;
            } else {
                break;
            }
        }
        
        longestPalindromeLengthEven = 0;
        for (int i = startIndex, j = startIndex + 1; i >= 0 && j < s.length(); --i, ++j) {
            if (s.charAt(i) == s.charAt(j)) {
                longestPalindromeLengthEven += 2;
            } else {
                break;
            }
        }
        
        return Math.max(longestPalindromeLengthOdd, longestPalindromeLengthEven);
    }
    
    public String longestPalindrome(String s) {
        int longestPalindromeLength, startIndex, endIndex;
        longestPalindromeLength = 0;
        startIndex = endIndex = 0;
        for (int i = 0; i < s.length(); ++i) {
            int currentLength = findPalindromeLength(s, i);
            if (currentLength > longestPalindromeLength) {
                longestPalindromeLength = currentLength;
                if (longestPalindromeLength % 2 == 0) {
                    startIndex = i - longestPalindromeLength / 2 + 1;
                    endIndex = i + longestPalindromeLength / 2 + 1;
                } else {
                    startIndex = i - longestPalindromeLength / 2;
                    endIndex = i + longestPalindromeLength / 2 + 1;
                }
                // System.out.println(i + " " + startIndex + " " + endIndex + " " + currentLength);
            }
        }
        return s.substring(startIndex, endIndex);
    }
}
