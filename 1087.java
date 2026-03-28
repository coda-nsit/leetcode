/**
author: @Rishab Banerjee
Explain with example. Example exiting string till now ["ac", "bc"] (which is formed from {a, b}c). Now {d, e} need to be processed. Then, with every string in string till now i.e. ["ac", "bc"] append d and e, so now string till now should become, ["acd", "bcd", "ace", "bce"]
 */
class Solution {
    public String[] expand(String s) {
        List<StringBuilder> answerList = new ArrayList<>();
        List<StringBuilder> braceExpansionStrings = new ArrayList<>();
        Character currentChar;
        int itr;
        boolean inBrace; // tracks if we are processing characters inside {}, because the logic to process characters inside {} is different from characters outside {}

        // initially the string till now is empty
        answerList.add(new StringBuilder(""));
        itr = 0;
        inBrace = false;

        while (itr < s.length()) {
            currentChar = s.charAt(itr);
            if (currentChar == '{') {
                // at every {, we create a new braceExpansionStrings which stores the lists of strings formed by appending brace characters.
                // exiting answerList = ["ac", "bc"]
                // current brace = {d, e}
                // braceExpansionStrings = ["acd", "bcd", "ace", "bce"]
                // braceExpansionStrings is needed as we can't modify the answerList till all characters in {} are not processed.
                // if we modify answerList then following happens,
                // exiting answerList = ["ac", "bc"]
                // after processing d, answerList becomes, answerList = ["acd", "bcd"] and then when we process e,
                // answerList = ["acde", "bcde"] which is not what we want
                braceExpansionStrings = new ArrayList<>();
                inBrace = true;
            } else if (currentChar == '}') {
                // braceExpansionStrings becomes the answerList now.
                answerList = new ArrayList<>();
                for (StringBuilder sb: braceExpansionStrings) {
                    answerList.add(sb);
                }
                braceExpansionStrings = null;
                inBrace = false;
            } else if (currentChar == ',') {
                // do nothing
            } else if (inBrace) {
                // append the current character to all the exisiting strings so far.
                List<StringBuilder> existingStringsPlusCurrentBraceCharacter = new ArrayList<>();
                for (StringBuilder exitingString: answerList) {
                    var exitingStringCopy = new StringBuilder(exitingString);
                    existingStringsPlusCurrentBraceCharacter.add(exitingStringCopy.append(currentChar));
                }
                for (var sb: existingStringsPlusCurrentBraceCharacter) {
                    braceExpansionStrings.add(sb);
                }
            } else {
                for (StringBuilder point: answerList) {
                    point.append(currentChar);
                }
            }
            itr += 1;
        }
        String[] answer = new String[answerList.size()];
        for (int i = 0; i < answerList.size(); ++i) {
            answer[i] = answerList.get(i).toString();
        }
        Arrays.sort(answer);
        return answer;
    }
}
