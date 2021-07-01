# Example 1 explaination: https://youtu.be/lla6QlAF4HQ?t=382
class Solution:
    def revealEmptyCells(self, board, x, y, adjacentMines, nr, nc):
        for pointX, pointY in [[x - 1, y - 1], [x - 1, y], [x - 1, y + 1], [x, y + 1], [x + 1, y + 1], [x + 1, y], [x + 1, y - 1], [x, y - 1]]:
            if 0 <= pointX < nr and 0 <= pointY < nc:
                if board[pointX][pointY] == 'E' and (pointX, pointY) not in adjacentMines:
                    board[pointX][pointY] = 'B'
                    self.revealEmptyCells(board, pointX, pointY, adjacentMines, nr, nc)
                elif (pointX, pointY) in adjacentMines:
                    board[pointX][pointY] = str(adjacentMines[(pointX, pointY)])
        return
                
        
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        nr, nc = len(board), len(board[0])
        
        # populate values of cells adjacent to mines but don't populate them yet
        adjacentMines = defaultdict(int)
        for i in range(nr):
            for j in range(nc):
                if board[i][j] == 'M':
                     for x, y in [[i - 1, j - 1], [i - 1, j], [i - 1, j + 1], [i, j + 1], [i + 1, j + 1], [i + 1, j], [i + 1, j - 1], [i, j - 1]]:
                            if x >= 0 and x < nr and y >= 0 and y < nc and board[x][y] == 'E':
                                adjacentMines[(x, y)] += 1
        x, y = click
        # bomb clicked 'M'
        if board[x][y] == 'M':
            board[x][y] = 'X'
        
        # empty square clicked 'E'
        else:
            if (x, y) in adjacentMines:
                board[x][y] = str(adjacentMines[(x, y)])
            else:
                board[x][y] = 'B'
                self.revealEmptyCells(board, x, y, adjacentMines, nr, nc)
        return board
