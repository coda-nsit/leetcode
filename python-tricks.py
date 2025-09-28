# 2D array
nrows = 2
ncols = 5
twoDList = [[0] * ncols for i in range(nrows)]

# 3D array: create k matrics of dimension r * c
r = 2
c = 8
k = 3
mat = [[[0] * k for i in range(c)] for _ in range(r)]
print(mat[r - 1][c - 1][k - 1])


# class comparator for maximum flexibility while sorting
# https://leetcode.com/problems/custom-sort-string/submissions/1434347376/: sort based on custom comparator
from functools import cmp_to_key
class CustomComparator:
    def __init__(self, order):
        self.order = order
    def __call__(self, x, y):
        if x not in self.order or y not in self.order:
            return -1
        idxX = self.order[x]
        idxY = self.order[y]
        # since sorting in accessing order if x appears before y, return -1
        if idxX < idxY:
            return -1
        elif idxX > idxY:
            return 1
        else:
            return 0
comparator = CustomComparator({"c": 0, "b": 1, "a": 2})
sorted_data = sorted("abcd", key=cmp_to_key(comparator))
print(sorted_data)
