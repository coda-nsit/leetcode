class Solution:
    def generate(self, numRows):
        if numRows == 0:
            return []
        if numRows == 1:
            return [[1]]
        res = [[1], [1, 1]]
        for idx in range(2, numRows):
            temp = [1]
            for idx2 in range(len(res[-1]) - 1):
                temp.append(res[-1][idx2] + res[-1][idx2 + 1])
            temp.append(1)
            res.append(temp)
        return res
