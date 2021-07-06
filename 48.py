'''
0,0 -> 0,3 -> 3,3 -> 3,0 -> 0,0
0,1 -> 1,3 -> 3,2 -> 2,0 -> 0,1
0,2 -> 2,3 -> 3,1 -> 1,0 -> 0,2
0,3 -> 3,3 -> 3,0 -> 0,0 -> 0,3

1,1 -> 1,2 -> 2,2 -> 2,1 -> 1,1

x,x+i -> x+i,y -> y,y-i -> y-i,x -> x,x+i

Algoritm: 
1. swap in groups of 4 for all elements in outer rows and columsn
2. recurse on the rest
'''

class Solution:
    def inplaceRotate(self, matrix, x, y):
        if x > y:
            return
        counter = 0
        for loop in range(y - x):
            # print(x, x + counter, '->', x + counter, y, '->', y, y - counter, '->', y - counter, x, '->', x, x + counter)
            saved = matrix[x][x + counter]
            matrix[x][x + counter] = matrix[y - counter][x]
            matrix[y - counter][x] = matrix[y][y - counter]
            matrix[y][y - counter] = matrix[x + counter][y]
            matrix[x + counter][y] = saved
            counter += 1
        return self.inplaceRotate(matrix, x + 1, y - 1)
        
        
    def rotate(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        self.inplaceRotate(matrix, 0, len(matrix) - 1)
        return matrix 
        
