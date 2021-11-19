class Solution {
    public int hammingDistance(int x, int y) {
        int z, setBitCount;
        z = x ^ y;
        
        setBitCount = 0;
        while (z > 0) {
            if ((z & 1) == 1) {
                setBitCount += 1;
            }
            z >>= 1;
        }
        return setBitCount;
    }
}
