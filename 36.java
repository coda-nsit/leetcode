class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<String> isPresent = new HashSet<>();
        
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] != '.') {
                    String rowString = "r" + String.valueOf(board[i][j]) + String.valueOf(i);
                    String colString = "c" + String.valueOf(board[i][j]) + String.valueOf(j);
                    String squareString = "s" + String.valueOf(board[i][j]) + String.valueOf(i / 3) + String.valueOf(j / 3);

                    // System.out.println(rowString + " " + colString + " " + squareString);
                    if (isPresent.contains(rowString) || isPresent.contains(colString) || isPresent.contains(squareString)) {
                        return false;
                    } else {
                        isPresent.add(rowString);
                        isPresent.add(colString);
                        isPresent.add(squareString);
                    }
                }
            }
        }
        return true;
    }
}
