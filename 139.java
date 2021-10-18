class Solution {
    private boolean wordBreak(String s, Set<String> wordDictSet, Map<String, Boolean> dp) {
        if (s.length() == 0 || wordDictSet.contains(s)) {
            return true;
        }
        
        for (int i = 1; i < s.length(); ++i) {
            String stringPrefix = s.substring(0, i);
            String stringSuffix = s.substring(i);
            if (!dp.containsKey(stringSuffix)) {
                dp.put(stringSuffix, wordBreak(stringSuffix, wordDictSet, dp));
            }
            // System.out.println(stringPrefix + " " + stringSuffix);
            if (wordDictSet.contains(stringPrefix) && dp.get(stringSuffix)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        Map<String, Boolean> dp = new HashMap<>();
        return wordBreak(s, wordDictSet, dp);
    }
}
