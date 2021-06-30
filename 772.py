is_digit = lambda x: ord('0') <= ord(x) <= ord('9')

# Algorithm: 
# current element is
# 1. '(' then recusively solve the part () example, 1 + (2 - (3 * 5 + 2) + 5) / 2 + 3 => solve 2 - (3 * 5 + 2) + 5
# 2. number
#     1. if stack is empty push the number
#     2. if stack top is * or / do the arithmatic
#     3. if stack top is + or - push the element
# 3. if operator then push
class Solution:
    def operate(self, op1, op2, operator):
        if operator == '*':
            return op1 * op2
        elif operator == '/':
            # -3 // 4 = -1 in python
            sign = (op1 // abs(op1)) * (op2 // abs(op2))
            return sign * (abs(op1) // abs(op2))
        elif operator == '+':
            return op1 + op2
        elif operator == '-':
            return op1 - op2
        
    def convertStringInputToList(self, s):
        listInput = []
        idx = 0
        while idx < len(s):
            if is_digit(s[idx]) is True:
                num_list = []
                while idx < len(s) and is_digit(s[idx]) is True:
                    num_list.append(s[idx])
                    idx += 1
                listInput.append(int(''.join(num_list)))
            else:
                listInput.append(s[idx])
                idx += 1
        return listInput
        
    def calculateListResult(self, s):
        stack = []
        idx = 0
        while idx < len(s):
            if s[idx] == '(':
                imbalance = 1
                closing_idx = idx + 1
                while imbalance != 0:
                    if s[closing_idx] == '(':
                        imbalance += 1
                    elif s[closing_idx] == ')':
                        imbalance -= 1
                    closing_idx += 1
                closing_idx -= 1
                op2 = self.calculateListResult(s[idx + 1 : closing_idx])
                idx = closing_idx
                if len(stack) == 0:
                    stack.append(op2)
                else:
                    if stack[-1] in ['*', '/']:
                        operator = stack.pop()
                        op1 = stack.pop()
                        stack.append(self.operate(op1, op2, operator))
                    else:
                        stack.append(op2)
            elif type(s[idx]) == int:
                if len(stack) > 0:
                    if stack[-1] in ['*', '/']:
                        operator = stack.pop()
                        op1 = stack.pop()
                        stack.append(self.operate(op1, s[idx], operator))
                    else:
                        stack.append(s[idx])
                else:
                    stack.append(s[idx])
            else:
                stack.append(s[idx])
            idx += 1
        total = 0
        while len(stack) > 1:
            op = stack.pop()
            operator = stack.pop()
            total = self.operate(total, op, operator)
        total = self.operate(total, stack.pop(), '+')
        return total
        
        
    def calculate(self, s: str) -> int:
        s = self.convertStringInputToList(s)
        return self.calculateListResult(s)
    
# "1*2-3/4+5*6-7*8+9/10"
# "2*(5+5*2)/3+(6/2+8)"
# "1+1"
# "6-4/2"
# "(2+6*3+5-(3*14/7+2)*5)+3"
# "0"
# "1*2-3/4+5*6-7*8+9/10"
# "(0-3)/4"
                
