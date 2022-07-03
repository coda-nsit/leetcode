class Solution:
    def peopleAwareOfSecret(self, n: int, delay: int, forget: int) -> int:
        totalKnowns = [0] * (n + 1)
        newKnowns = [0] * (n + 1)
        MOD = 10 ** 9 + 7
        
        newKnowns[1] = 1
        for i in range(1 + delay, 1 + forget):
            newKnowns[i] += 1
        for i in range(1, 1 + forget):
            totalKnowns[i] = 1
        
        
        for day in range(2, n + 1):
            dayNewKnowns = newKnowns[day]
            for i in range(day + delay, min(day + forget, n + 1)):
                newKnowns[i] += dayNewKnowns
                newKnowns[i] %= MOD
            
            for i in range(day, min(day + forget, n + 1)):
                totalKnowns[i] += dayNewKnowns
                totalKnowns[i] %= MOD
        return totalKnowns[-1] % MOD
