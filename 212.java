class TrieNode
{
    public char val;
    public boolean isWord;
    public Map<Character, TrieNode> children;

    TrieNode(char val)
    {
        this.val = val;
        this.isWord = false;
        this.children = new HashMap<>();
    }
}

class Trie
{
    public TrieNode root = new TrieNode('-');
    public void insertWord(String word)
    {
        TrieNode currNode = root;
        for (int i = 0; i < word.length(); ++i)
        {
            char charAti = word.charAt(i);
            if (!currNode.children.containsKey(charAti))
            {
                currNode.children.put(charAti, new TrieNode(charAti));
            }
            currNode = currNode.children.get(charAti);
            if (i == word.length() - 1)
            {
                currNode.isWord = true;
            }
        }
    } 

    public void printTrie(TrieNode currNode, StringBuilder currWord)
    {
        if (currNode == null)
        {
            return;
        }
        if (currNode.val != '-')
        {
            currWord.append(currNode.val);
        }
        if (currNode.isWord)
        {
            System.out.println(currWord.toString());
        }
        for (Map.Entry<Character, TrieNode> point: currNode.children.entrySet())
        {
            printTrie(point.getValue(), currWord);
        }
        if (currNode.val != '-')
        {
            currWord.deleteCharAt(currWord.length() - 1);
        }
    }

    public void findWords(TrieNode currNode, boolean[][] visited, char[][] board, Set<String> answer, int r, int c, StringBuilder currWord)
    {
        int nr, nc;
        nr = board.length;
        nc = board[0].length;
        if (currNode.isWord)
        {
            answer.add(currWord.toString());
        }
        if (currNode == null || r < 0 || c < 0 || r == nr || c == nc || visited[r][c])
        {
            return;
        }
        if (currNode.children.containsKey(board[r][c]))
        {
            visited[r][c] = true;
            currWord.append(board[r][c]);
            findWords(currNode.children.get(board[r][c]), visited, board, answer, r + 1, c, currWord);
            findWords(currNode.children.get(board[r][c]), visited, board, answer, r, c + 1, currWord);
            findWords(currNode.children.get(board[r][c]), visited, board, answer, r - 1, c, currWord);
            findWords(currNode.children.get(board[r][c]), visited, board, answer, r, c - 1, currWord);
            visited[r][c] = false;
            currWord.deleteCharAt(currWord.length() - 1);
        }
    }
}

class Solution 
{
    public List<String> findWords(char[][] board, String[] words) 
    {
        Trie trie = new Trie();
        for (String word: words)
        {
            trie.insertWord(word);
        }
        
        // trie.printTrie(trie.root, new StringBuilder());

        Set<String> answers = new HashSet<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        // For every cell in board, start a new search in the Trie
        for (int i = 0; i < board.length; ++i)
        {
            for (int j = 0; j < board[0].length; ++j)
            {
                trie.findWords(trie.root, visited, board, answers, i, j, new StringBuilder());
            }
        }
        return new ArrayList<String>(answers);
    }
}
