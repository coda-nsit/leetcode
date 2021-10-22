class Solution {
    private int findBalancePoint(String s, int start) {
        int balance = 0;
        for (int i = start; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                balance -= 1;
            } else if(s.charAt(i) == ')') {
                balance += 1;
            }
            if (balance == 0) {
                return i;
            }
        }
        return -1;
    }
    private StringBuilder reverseParanthesesInRange(String s, int start, int end) {
        if (start == end) {
            StringBuilder singleString = new StringBuilder();
            singleString.append(s.charAt(start));
            return singleString;
        }
        
        int i = start;
        StringBuilder subProblem = new StringBuilder();
        while (i <= end) {
            if (s.charAt(i) == '(') {
                int j = findBalancePoint(s, i);
                subProblem.append(reverseParanthesesInRange(s, i + 1, j - 1).reverse());
                i = j + 1;
            } else {
                subProblem.append(s.charAt(i));
                i += 1;
            }
        }
        return subProblem;
    }
    
    public String reverseParentheses(String s) {
        StringBuilder answer = reverseParanthesesInRange(s, 0, s.length() - 1);
        return answer.toString();
    }
}
