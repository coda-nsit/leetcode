class Solution {
    private int countPalindromes(String s, int startIndex) {
        int totalPalindromes = 0;
        // odd case
        for (int i = startIndex, j = startIndex; i >= 0 && j < s.length(); --i, ++j) {
            if (s.charAt(i) == s.charAt(j)) {
                totalPalindromes += 1;
            } else {
                break;
            }
        }
        // even case
        for (int i = startIndex, j = startIndex + 1; i >= 0 && j < s.length(); --i, ++j) {
            if (s.charAt(i) == s.charAt(j)) {
                totalPalindromes += 1;
            } else {
                break;
            }
        }
        return totalPalindromes;
    }
    
    public int countSubstrings(String s) {
        int totalPalindromes = 0;
        for (int i = 0; i < s.length(); ++i) {
            totalPalindromes += countPalindromes(s, i);
        }
        return totalPalindromes;
    }
}
