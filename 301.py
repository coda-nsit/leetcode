'''
smart brute force algorithm-- for every brace we have 2 options either we take it or not take it. The smartness is in the realization that in all the final answers the number of ( and ) is fixed. So, we only skip a brace if we can i.e. we skip a ( if left_extra > 0 abd similarly we skip a ) if right_extra > 0
'''
class Solution:
    def isBalanced(self, brace_list):
        balance_counter = 0
        for c in brace_list:
            if c == '(':
                balance_counter += 1
            elif c == ')':
                if balance_counter <= 0:
                    return False
                balance_counter -= 1
        return balance_counter == 0
                
        
    def removeInvalidParanthesis(self, s, idx, current_s, answers, left_extra, right_extra):
        if idx == len(s):
            if left_extra == 0 and right_extra == 0 and self.isBalanced(current_s):
                answers.append(''.join(current_s))
            return
        
        if s[idx] == '(':
            # if ( can be skipped
            if left_extra > 0:
                self.removeInvalidParanthesis(s, idx + 1, current_s, answers, left_extra - 1, right_extra)
            # don't skip
            current_s.append('(')
            self.removeInvalidParanthesis(s, idx + 1, current_s, answers, left_extra, right_extra)
            current_s.pop()
        elif s[idx] == ')':
            # if ) can be skipped
            if right_extra > 0:
                self.removeInvalidParanthesis(s, idx + 1, current_s, answers, left_extra, right_extra - 1)
            # don't skip
            current_s.append(')')
            self.removeInvalidParanthesis(s, idx + 1, current_s, answers, left_extra, right_extra)
            current_s.pop()
        else:
            current_s.append(s[idx])
            self.removeInvalidParanthesis(s, idx + 1, current_s, answers, left_extra, right_extra)
            current_s.pop()
        return


    def removeInvalidParentheses(self, s: str) -> List[str]:
        # find how many extra ( and ) are present
        left_extra = right_extra = 0
        balance_counter = 0
        for c in s:
            if c == '(':
                balance_counter += 1
            elif c == ')':
                if balance_counter > 0:
                    balance_counter -= 1
                else:
                    right_extra += 1
        left_extra = max(balance_counter, 0)
        
        answers = []
        # find all the best solutions using backtracking
        self.removeInvalidParanthesis(s, 0, [], answers, left_extra, right_extra)
        return set(answers)
