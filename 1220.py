MOD = 1000000007
class Solution:
    def __init__(self):
        self.dp = defaultdict()
        
    def countVowelPermutation(self, n: int) -> int:
        if n == 1:
            self.dp[(1, 'a')] = self.dp[(1, 'e')] = self.dp[(1, 'i')] = self.dp[(1, 'o')] = self.dp[(1, 'u')] = 1
            return 5
        if n in self.dp:
            return self.dp
        sub_problem = self.countVowelPermutation(n - 1)
        
        self.dp[(n, 'a')] = (self.dp[(n - 1, 'e')] + self.dp[(n - 1, 'i')] + self.dp[(n - 1, 'u')]) % MOD
        self.dp[(n, 'e')] = (self.dp[(n - 1, 'a')] + self.dp[(n - 1, 'i')]) % MOD
        self.dp[(n, 'i')] = (self.dp[(n - 1, 'e')] + self.dp[(n - 1, 'o')]) % MOD
        self.dp[(n, 'o')] = self.dp[(n - 1, 'i')]
        self.dp[(n, 'u')] = (self.dp[(n - 1, 'i')] + self.dp[(n - 1, 'o')]) % MOD
        return (self.dp[(n, 'a')] + self.dp[(n, 'e')] + self.dp[(n, 'i')] + self.dp[(n, 'o')] + self.dp[(n, 'u')]) % MOD
