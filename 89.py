# Algorithm: Use simple recursion
# n = 2 ==> 00 01 11 10
# n = 2 ==> 0(00 01 11 10) + 1(10 11 01 00) ==> 000 001 011 010 110 111 101 100
import copy
class Solution:
    def grayCode(self, n: int) -> List[int]:
        if n == 1:
            return [0, 1]
        sub_problem_solution = self.grayCode(n - 1)
        # form the numbers by prefixing 0 i.e. the same numbers
        problem_solution = copy.copy(sub_problem_solution)
        # form the numbers by prefixing 1 i.e. 10000 + 0110
        for idx in range(len(sub_problem_solution) - 1, -1, -1):
            problem_solution.append((1 << (n - 1)) +  sub_problem_solution[idx])
        return problem_solution
