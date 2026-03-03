/*
@author- Rishab Banerjee
while (even one node is crushed)
{
    for every node
    {
        1. go down and mark nodes for crushing (make it negative), if crushing possible
        2. go right and mark nodes for crusing (make it negative), if crusing possible
    }
    // this step is not strictly needed as nodes which are crushed are negative
    for every node
    {
        make node crushed i.e. 0 if marked for crusing
    }
    for every node
    {
        if node == 0
        {
            dropCount = go down to see how many nodes to drop
            drop the nodes 
        }
    }
}
*/

class Solution 
{
    private void printBoard(int[][] board)
    {
        int nr, nc;
        nr = board.length;
        nc = board[0].length;
        for (int i = 0; i < nr; ++i)
        {
            for (int j = 0; j < nc; ++j)
            {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private boolean threeRows(int[][] board, int i, int j)
    {
        return i + 1 < board.length && Math.abs(board[i][j]) == Math.abs(board[i + 1][j]) && i + 2 < board.length && Math.abs(board[i][j]) == Math.abs(board[i + 2][j]);
    }

    private boolean threeCols(int[][] board, int i, int j)
    {
        return j + 1 < board[0].length && Math.abs(board[i][j]) == Math.abs(board[i][j + 1]) && j + 2 < board[0].length && Math.abs(board[i][j]) == Math.abs(board[i][j + 2]);
    }

    private boolean markNodesToBeCrushed(int [][] board)
    {
        int nr, nc, firstOccurence, downIdx, rightIdx;
        boolean anyNodesToCrush;
        
        nr = board.length;
        nc = board[0].length;
        anyNodesToCrush = false;

        for (int i = 0; i < board.length; ++i)
        {
            for (int j = 0; j < board[0].length; ++j)
            {
                if (board[i][j] == 0)
                {
                    continue;
                }
                // if three consecutive rows are present
                if (threeRows(board, i, j))
                {
                    board[i][j] = -Math.abs(board[i][j]);
                    anyNodesToCrush = true;
                    firstOccurence = Math.abs(board[i][j]);
                    downIdx = i + 1;
                    while (downIdx < board.length && firstOccurence == Math.abs(board[downIdx][j]))
                    {
                        board[downIdx][j] = -Math.abs(board[i][j]);
                        downIdx += 1;
                    }
                }
                // if three consecutive columns are present
                if (threeCols(board, i, j))
                {
                    board[i][j] = -Math.abs(board[i][j]);
                    anyNodesToCrush = true;
                    firstOccurence = Math.abs(board[i][j]);
                    rightIdx = j + 1;
                    while (rightIdx < board[0].length && firstOccurence == Math.abs(board[i][rightIdx]))
                    {
                        board[i][rightIdx] = -Math.abs(board[i][j]);
                        rightIdx += 1;
                    }
                }
            }
        }
        return anyNodesToCrush;
    }

    private void crushNodes(int[][] board)
    {
        int nr, nc;
        nr = board.length;
        nc = board[0].length;
        for (int i = 0; i < nr; ++i)
        {
            for (int j = 0; j < nc; ++j)
            {
                if (board[i][j] < 0)
                {
                    board[i][j] = 0;
                }
            }
        }
    }

    private void dropNodes(int[][] board)
    {
        int nr, nc, dropCount, downIdx, dropFromIdx;
        nr = board.length;
        nc = board[0].length;

        for (int i = 0; i < nr; ++i)
        {
            for (int j = 0; j < nc; ++j)
            {
                if (board[i][j] == 0)
                {
                    // find how many nodes can be dropped
                    dropCount = 0;
                    downIdx = i;
                    while (downIdx < board.length && board[downIdx][j] == 0)
                    {
                        dropCount +=1 ;
                        downIdx += 1;
                    }
                    // drop the nodes
                    dropFromIdx = i - 1;
                    while (dropFromIdx >= 0)
                    {
                        board[dropFromIdx + dropCount][j] = board[dropFromIdx][j];
                        dropFromIdx -= 1;
                    }
                    // fill the remaining nodes with 0
                    for (int k = 0; k < dropCount; ++k)
                    {
                        board[k][j] = 0;
                    }
                }
            }
        }
    }

    public int[][] candyCrush(int[][] board) 
    {
        boolean canCrush = true;
        while (canCrush)
        {
            canCrush = false || this.markNodesToBeCrushed(board);
            this.printBoard(board);
            if (canCrush)
            {
                this.crushNodes(board);
                this.printBoard(board);

                this.dropNodes(board);
                this.printBoard(board);
            }
        }
        return board;
    }
}
