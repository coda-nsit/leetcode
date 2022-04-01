"""
have 2 stacks:
1. for ( braces
2. for * braces

1. if ( ==> push to (Stack
2. if * ==> push to *Stack
3. if ) ==> pop from (Stack if possible, elif from *Stack if possible, else return False

4. Finally we will be left with (Stack and *Stack elements. Just check if for every ( in (Stack we have a * in. *Stack which comes after the ( for cases like 
(((*** ==> True 
***((( ==> False

https://leetcode.com/problems/valid-parenthesis-string/discuss/107572/Java-using-2-stacks.-O(n)-space-and-time-complexity.
"""
class Solution:
    def checkValidString(self, s: str) -> bool:
        braceStack = []
        starStack = []
        for idx, c in enumerate(s):
            if c == "(":
                braceStack.append(idx)
            elif c == "*":
                starStack.append(idx)
            else:
                if len(braceStack) > 0:
                    braceStack.pop()
                elif len(starStack) > 0:
                    starStack.pop()
                else:
                    return False
        
        while len(braceStack) > 0:
            currentOpeningBrace = braceStack.pop()
            if len(starStack) > 0 and starStack.pop() > currentOpeningBrace:
                continue
            else:
                return False
        return len(braceStack) == 0

"""
"()"
"(*)"
"(*))"
"("
"((**)))))(()())()"
"(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())"
"(**("
"(((()))())))*))())()(**(((())(()(*()((((())))*())(())*(*(()(*)))()*())**((()(()))())(*(*))*))())"
"""
