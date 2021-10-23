class Solution {
    private int findBalancePoint(String s, int start) {
        int balance = 0;
        for (int i = start; i < s.length(); ++i) {
            if (s.charAt(i) == '[') {
                balance += 1;
            } else if (s.charAt(i) == ']') {
                balance -= 1;
            }
            if (balance == 0) {
                return i;
            }
        }
        return -1;
    }
    
    private void multiplyString(StringBuilder stringToMultiply, int times) {
        int originalLength = stringToMultiply.length();
        for (int count = 0; count < times; ++count) {
            int i = 0;
            while (i < originalLength) {
                stringToMultiply.append(stringToMultiply.charAt(i));
                i += 1;
            }
        }
        return;
    }
    
    private String getMultiplicationFactor(String s, int start) {
        StringBuilder numberBuilder = new StringBuilder();
        for (int i = start; i < s.length(); ++i) {
            if (Character.isDigit(s.charAt(i))) {
                numberBuilder.append(s.charAt(i));
            } else {
                break;
            }
        }
        return numberBuilder.toString();
    }
    
    private StringBuilder decodeStringInRange(String s, int start, int end) {
        StringBuilder decodedString = new StringBuilder();
        int i = start;
        while (i <= end) {
            if (Character.isDigit(s.charAt(i))) {
                String stringMultiplicationFactor = getMultiplicationFactor(s, i);
                int multiplicationFactor = Integer.parseInt(stringMultiplicationFactor);
                i += stringMultiplicationFactor.length(); 
                int j = findBalancePoint(s, i);
                StringBuilder subProblem = decodeStringInRange(s, i + 1, j - 1);
                multiplyString(subProblem, multiplicationFactor - 1); //  -1 cause the string has already been formed once
                decodedString.append(subProblem);
                i = j + 1;
                
            } else {
                decodedString.append(s.charAt(i));
                i += 1;
            }
        }
        return decodedString;
    }
    
    public String decodeString(String s) {
        return decodeStringInRange(s, 0, s.length() - 1).toString();
    }
}
