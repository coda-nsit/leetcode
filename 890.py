class Solution:
    def reduceWords(self, words):
        reducedWords = []
        for word in words:
            sortedWord = word
            mappedWord = {}
            currOffset = -1
            reducedWord = []
            for c in sortedWord:
                if c not in mappedWord:
                    currOffset += 1
                    mappedWord[c] = chr(ord("a") + currOffset)
                reducedWord.append(mappedWord[c])
            reducedWords.append("".join(reducedWord))
        return reducedWords
    
        
    def findAndReplacePattern(self, words: List[str], pattern: str) -> List[str]:
        reducedWords = self.reduceWords(words)
        reducedPattern = self.reduceWords([pattern])[0]
        # print("reducedWords:", reducedWords)
        # print("reducedPattern:", reducedPattern)
        answer = []
        for idx in range(len(reducedWords)):
            if reducedWords[idx] == reducedPattern:
                answer.append(words[idx])
        return answer
