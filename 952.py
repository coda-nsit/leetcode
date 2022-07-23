class Solution:
    def seive(self):
        primes = set()
        isPrimes = [True] * 320
        isPrimes[0] = isPrimes[1] = False
        for i in range(2, 320):
            if isPrimes[i] is True:
                primes.add(i)
                for j in range(2 * i, 320, i):
                    isPrimes[j] = False
        return sorted(primes)
        
    def dfs(self, graph, visited, primes, numsSet, root):
        if root in visited:
            return 0
        visited.add(root)
        if root not in primes or root in numsSet:
            totalNodes = 1
        else:
            totalNodes = 0
        for child in graph[root]:
            totalNodes += self.dfs(graph, visited, primes, numsSet, child)
        return totalNodes
        
    def largestComponentSize(self, nums: List[int]) -> int:
        primes = self.seive()
        graph = defaultdict(set)
        
        newPrimes = set()
        for num in nums:
            currentNum = num
            for prime in primes:
                if prime > currentNum:
                    break
                if currentNum % prime == 0:
                    graph[prime].add(num)
                    graph[num].add(prime)
                while currentNum % prime == 0:
                    currentNum //= prime
            if currentNum > 1:
                newPrimes.add(currentNum)
                graph[currentNum].add(num)
                graph[num].add(currentNum)
            
        primes.extend(newPrimes)
        primes = set(primes)
        numsSet = set(nums)
        largestSet = 0
        visited = set()
        # print(graph)
        for num in nums:
            if num not in visited:
                # print("num:", num)
                largestSet = max(largestSet, self.dfs(graph, visited, primes, numsSet, num))
        return largestSet
            
