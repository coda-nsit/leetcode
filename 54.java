class Solution {
    private void printSingleStrip(List<Integer> answer, int[][] matrix, int x1, int y1, int x2, int y2) {
        // System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
        // left to right
        for (int i = y1; i <= y2; ++i) {
            answer.add(matrix[x1][i]);
        }
        // top to bottom
        for (int i = x1 + 1; i <= x2; ++i) {
            answer.add(matrix[i][y2]);
        }
        // right to left, also take care of single row
        if (x2 > x1) {
            for (int i = y2 - 1; i >= y1; --i) {
                answer.add(matrix[x2][i]);
            }
        }
        // bottom to top, also take care of single column
        if (y2 > y1) {
            for (int i = x2 - 1; i > x1; --i) {
                answer.add(matrix[i][y1]);
            }
        }
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> answer = new ArrayList<>();
        int x1, y1, x2, y2;
        x1 = 0;
        x2 = matrix.length - 1;
        y1 = 0;
        y2 = matrix[0].length - 1;
        
        while (x1 <= x2 && y1 <= y2) {
            printSingleStrip(answer, matrix, x1, y1, x2, y2);
            x1++;
            x2--;
            y1++;
            y2--;
        }
        return answer;
    }
}
