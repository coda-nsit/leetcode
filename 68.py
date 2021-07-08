'''
Algorithm: https://leetcode.com/problems/text-justification/discuss/24902/Java-easy-to-understand-broken-into-several-functions
1. while there are words:
2.     collect words that will go in the same line
3.     fix spaces in these words
'''
class Solution:
    def findSameLineWords(self, start, maxWidth, words):
        chars = 0
        '''
        maxWidth + 1 cause the last word of every sentence doesn't have a space, so in case the last word of the sentence fits exactly we need to accomodate the fact that it has no trailing space
        "enough to explain to" maxWidth=20
        ''' 
        while start < len(words) and chars + len(words[start]) + 1 <= maxWidth + 1:
            chars += len(words[start]) + 1
            start += 1
        return start - 1
    
    
    def fixSpaces(self, start, end, words, maxWidth):
        generate_spaces = lambda x: ' ' * x
        
        answer = []
        
        # if only 1 word: pad spaces at the end
        if end == start:
            answer.append(words[start])
            answer.append(generate_spaces(maxWidth - len(words[start])))
            return ''.join(answer)
        
        # special care is required for the last line as it has no spaces between words 
        is_last_line = (end == len(words) - 1)
        total_chars = sum(len(words[i]) for i in range(start, end + 1))
        total_spaces = maxWidth - total_chars 
        even_spaces = total_spaces // (end - start) if is_last_line is False else 1
        remainder_spaces = total_spaces - even_spaces * (end - start) if is_last_line is False else 0
        
        # print(words[start : end + 1], 'total_chars:', total_chars, 'total_spaces:', total_spaces, 'even_spaces:', even_spaces, 'remainder_spaces:', remainder_spaces)
        
        for idx in range(start, end + 1):
            answer.append(words[idx])
            answer.append(generate_spaces(even_spaces))
            if remainder_spaces > 0:
                answer.append(generate_spaces(1))
                remainder_spaces -= 1
        sentence = ''.join(answer[: -1])     # remove the trailing spaces
        if is_last_line is False:            # if not the last sentence return without the trailing spaces
            return sentence
        else:                                # add the trailing spaces
            return sentence + generate_spaces(maxWidth - len(sentence))
                
        
        
    def fullJustify(self, words, maxWidth):
        start = 0
        answer = []
        while start < len(words):
            end = self.findSameLineWords(start, maxWidth, words)
            answer.append(self.fixSpaces(start, end, words, maxWidth))
            # print(start, end)
            start = end + 1
        return answer
