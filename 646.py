class Solution:
    def findLongestChain(self, pairs: List[List[int]]) -> int:
        sorted_pairs = list(sorted(pairs, key = lambda x: x[1]))
        total = 1
        e = sorted_pairs[0][1]
        for idx in range(1, len(sorted_pairs)):
            if sorted_pairs[idx][0] > e:
                total += 1
                e = sorted_pairs[idx][1]
        return total
