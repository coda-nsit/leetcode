"""
ceabaacb
create 2 data structures

freqs:
a: 3
b: 2
c: 2
e: 1
[3, 2, 2, 1]

repetitions:
3: 1
2: 2
1: 1

now keep reducing freqs array greedily until all elements become unique i.e. repetitions[freqs[i]] <= 1
[3, 2, 1, 0]
"""
from collections import Counter
class Solution:
    def minDeletions(self, s: str) -> int:
        freqs = list(Counter(s).values())
        repetitions = Counter(freqs)
        
        # print(freqs)
        # print(repetitions)
        
        deletions = 0
        for idx in range(len(freqs)):
            while freqs[idx] in repetitions and repetitions[freqs[idx]] > 1 and freqs[idx] > 0:
                repetitions[freqs[idx]] -= 1
                freqs[idx] -= 1
                repetitions[freqs[idx]] += 1
                deletions += 1
            repetitions[freqs[idx]] += 1
        return deletions
        

# "aab"
# "aaabbbcc"
# "ceabaacb"
# "abcabc"
