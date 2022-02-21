"""
Algorithm:
intermidiateString = []
for c in s:
    if c != "(" or ")":
        intermidiateString.append(c)
    elif c == "(":
        increase the imbalance
    else:
        1. decrease the imbalance
        2. if there are more ")" than "(" remove it now only cause any extra ")" has to be removed and set imbalance to 0

At this point the intermidiateString will only contain extra "(" or nothing extra at all.
So if we read from reverse then if at any point "(" increases, it means we have to remove it.
for c in reverse(s):
    if c == "(":
        1. decrease the imbalance
        2. if # "(" > 0 don't use the "("
"""
class Solution:
    def minRemoveToMakeValid(self, s: str) -> str:
        imbalance = 0
        intermidiateString = []
        for c in s:
            intermidiateString.append(c)
            if c == "(":
                imbalance += 1
            elif c == ")":
                imbalance -= 1
                if imbalance < 0:
                    intermidiateString.pop()
                    imbalance = 0
                
        
        answer = []
        for idx in range(len(intermidiateString) - 1, -1, -1):
            answer.append(intermidiateString[idx])
            if intermidiateString[idx] == "(":
                imbalance -= 1
                if imbalance >= 0:
                    answer.pop()
        
        return "".join(answer[::-1])
            

"""
"lee(t(c)o)de)"
"a)b(c)d"
"))(("
"""
