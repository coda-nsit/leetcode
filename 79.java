class MatrixIndices {
    private int x;
    private int y;
    private static final int MOD = 31;
    
    MatrixIndices(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int hashCode() {
        return ((this.x ^ this.y) * 17) % MOD;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass()) 
            return false;
        MatrixIndices other = (MatrixIndices) obj;
        return other.x == this.x && other.y == this.y;
    }

}

class Solution {
    private boolean findWordInGrid(Set<MatrixIndices> visited, char[][] board, String word, int startX, int startY) {
        if (word.length() == 0) {
            return true;
        }
        
        MatrixIndices matrixIndices = new MatrixIndices(startX, startY);
        if (startX < 0 || startX >= board.length || startY < 0 || startY >= board[0].length || board[startX][startY] != word.charAt(0) || visited.contains(matrixIndices)) {
            return false;   
        }
        
        visited.add(matrixIndices);
        String nextString = word.substring(1);
        boolean isFound = findWordInGrid(visited, board, nextString, startX + 1, startY) || findWordInGrid(visited, board, nextString, startX - 1, startY) || findWordInGrid(visited, board, nextString, startX, startY + 1) || findWordInGrid(visited, board, nextString, startX, startY - 1);
        // backtrack
        visited.remove(matrixIndices);
        return isFound;
    }
    
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                Set<MatrixIndices> visited = new HashSet<>();
                if (board[i][j] == word.charAt(0) && findWordInGrid(visited, board, word, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
}
