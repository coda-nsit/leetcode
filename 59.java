class Solution {
    private void generateMatrixInRange(int[][] spiralMatrix, int left, int right, int start) {
        if (left > right) {
            return;
        }
        // L -> R
        for (int i = left; i <= right; ++i) {
            spiralMatrix[left][i] = start++;
        }
        // T -> B
        for (int i = left + 1; i <= right; ++i) {
            spiralMatrix[i][right] = start++;
        }
        // R -> L
        for (int i = right - 1; i >= left; --i) {
            spiralMatrix[right][i] = start++;
        }
        // B -> T
        for (int i = right - 1; i > left; --i) {
            spiralMatrix[i][left] = start++;
        }
        generateMatrixInRange(spiralMatrix, left + 1, right - 1, start);
        return;
        
    }
    
    public int[][] generateMatrix(int n) {
        int[][] spiralMatrix = new int[n][n];
        generateMatrixInRange(spiralMatrix, 0, n - 1, 1);
        return spiralMatrix;
    }
}
