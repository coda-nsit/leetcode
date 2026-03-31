import java.util.*;

/**
For every string
    1. Create a frequency map of characters.
    2. Combine those characters i.e. a12#b6#c1, etc to form a key
 */

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();

        for (String word : strs) {
            int[] frequency = new int[26];

            for (char ch : word.toCharArray()) {
                frequency[ch - 'a']++;
            }

            StringBuilder keyBuilder = new StringBuilder();
            for (int count : frequency) {
                keyBuilder.append(count).append('#');
            }

            String key = keyBuilder.toString();
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(groups.values());
    }
}