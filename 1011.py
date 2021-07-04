class Solution:
    def evaluate_days(self, weights, max_value):
        days = 1
        current_weight = 0
        for i in range(len(weights)):
            if weights[i] > max_value:
                return float('inf')
            elif current_weight + weights[i] <= max_value:
                current_weight += weights[i]
            else:
                current_weight = weights[i]
                days += 1
        return days
            
    def shipWithinDays(self, weights: List[int], days: int) -> int:
        mini, maxi = min(weights), sum(weights)
        best_answer = float('inf')
        while mini <= maxi:
            mid = (mini + maxi) // 2
            evaluated_days = self.evaluate_days(weights, mid)
            # print(mini, maxi, mid, 'evaluated_days:', evaluated_days)
            if evaluated_days > days:
                mini = mid + 1
            else:
                best_answer = min(mid, best_answer)
                maxi = mid - 1
        return best_answer
    
# [1,2,3,4,5,6,7,8,9,10]
# 5
# [3,2,2,4,1,4]
# 3
# [1,2,3,1,1]
# 4
