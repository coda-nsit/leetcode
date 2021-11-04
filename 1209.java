class StackItem {
    public char item;
    public int freq;
    
    StackItem(char item, int freq) {
        this.item = item;
        this.freq = freq;
        
    }
}

class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<StackItem> stack = new Stack<>();
        stack.push(new StackItem(s.charAt(0), 1));
        
        for (int i = 1; i < s.length(); ++i) {
            if (stack.size() > 0) {
                StackItem stackTop = stack.peek();
                if (s.charAt(i) == stackTop.item) {
                    stackTop = stack.pop();
                    stackTop.freq += 1;
                    stack.push(stackTop);
                } else {
                    stack.push(new StackItem(s.charAt(i), 1));
                }
                stackTop = stack.peek();
                if (stackTop.freq == k) {
                    stack.pop();
                }
            } else {
                stack.push(new StackItem(s.charAt(i), 1));
            }
        }
        
        StringBuilder remainingString = new StringBuilder();
        while (stack.size() > 0) {
            StackItem stackTop = stack.pop();
            for (int i = 0; i < stackTop.freq; ++i) {
                remainingString.append(stackTop.item);
            }
        }
        return remainingString.reverse().toString();
    }
}
