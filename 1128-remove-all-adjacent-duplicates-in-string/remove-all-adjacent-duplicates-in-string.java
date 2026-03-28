/** 
Question is lot simpler than might look-- we only remove elements in pairs.
So, aaa answer is a and not "".

Algo,
if s[idx] == stacktop: 
1. dont process the current element
2. pop the stack
we do this since, we process elements in pair
 */
class Solution {
    public String removeDuplicates(String s) {
        StringBuilder stack = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (stack.length() > 0 && stack.charAt(stack.length() - 1) == c) {
                // pop
                stack.deleteCharAt(stack.length() - 1);
            } else {
                // push
                stack.append(c); // push
            }
        }

        return stack.toString();
    }
}
