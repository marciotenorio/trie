import java.util.HashMap;

public class TrieNode {
    private char letter;
    private HashMap<Character, TrieNode> children  = new HashMap<>();
    private boolean isWord;

    public TrieNode(char letter){
        this.letter = letter;
    }
    public TrieNode(){}
    public boolean isWord() {
        return isWord;
    }
    public void setWord(boolean word) {
        isWord = word;
    }
    public HashMap<Character, TrieNode> getChildren(){
        return children;
    }
    public void setChildren(HashMap<Character, TrieNode> children){
        this.children = children;
    }


}
