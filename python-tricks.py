# 2D array
twoDList = [[0] * ncols for i in range(nrows)]

# 3D array: create k matrics of dimension r * c
r = 2
c = 8
k = 3
mat = [[[0] * k for i in range(c)] for _ in range(r)]
print(mat[r - 1][c - 1][k - 1])
