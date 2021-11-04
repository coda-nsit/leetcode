// We don't need an n^3 solution as i1 and i2 uniquely determine any state (since i3 = i1 + i2) i.e. if we have (4, 6, 10) (5, 5, 10) we don't need the third value 10. We can sure have (4, 6, 10) and (5, 5, 10) but that doesn't matter. We basically are saying i1, i2 uniquely determines any state.
class StringIndices {
    public int i1;
    public int i2;
    
    StringIndices(int i1, int i2) {
        this.i1 = i1;
        this.i2 = i2;
    }
    
    public int hashCode() {
        return (i1 << 16) + i2;
    }
    
    public boolean equals (final Object O) {
        if (!(O instanceof StringIndices)) return false;
        if (((StringIndices) O).i1 != i1) return false;
        if (((StringIndices) O).i2 != i2) return false;
        return true;
    }
}
class Solution {
    private boolean isInterleave(String s1, String s2, String s3, int i1, int i2, int i3, Map<StringIndices, Boolean> memo) {
        StringIndices stringIndices = new StringIndices(i1, i2);
        if (memo.containsKey(stringIndices)) {
            return memo.get(stringIndices);
        }
        
        if (i3 == -1 && i1 == -1 && i2 == -1) {
            return true;
        }
        
        if (i1 >= 0 && i2 >=0 && i3 >=0 && s1.charAt(i1) == s3.charAt(i3) && s2.charAt(i2) == s3.charAt(i3)) {
            memo.put(stringIndices, isInterleave(s1, s2, s3, i1 - 1, i2, i3 - 1, memo) || isInterleave(s1, s2, s3, i1, i2 - 1, i3 - 1, memo));
            return memo.get(stringIndices);
        }
        
        if (i1 >= 0 && i3 >= 0 && s1.charAt(i1) == s3.charAt(i3)) {
            memo.put(stringIndices, isInterleave(s1, s2, s3, i1 - 1, i2, i3 - 1, memo));
            return memo.get(stringIndices);
        }
        
        if (i2 >= 0 && i3 >= 0 && s2.charAt(i2) == s3.charAt(i3)) {
            memo.put(stringIndices, isInterleave(s1, s2, s3, i1, i2 - 1, i3 - 1, memo));
            return memo.get(stringIndices);
        }
        return false;
    }
    
    public boolean isInterleave(String s1, String s2, String s3) {
        Map<StringIndices, Boolean> memo = new HashMap<>();
        return isInterleave(s1, s2, s3, s1.length() - 1, s2.length() - 1, s3.length() - 1, memo);
    }
}

// Interesting test case
// "aabc"
// "abad"
// "aabadabc"
