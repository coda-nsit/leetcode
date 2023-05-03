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
}
