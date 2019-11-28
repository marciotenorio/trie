import java.util.HashMap;

public class TrieNode {
    private char letter;
    //Hashmap pois inserção e remoção tem complexidade O(1)
    private HashMap<Character, TrieNode> children  = new HashMap<>();
    private boolean isWord;

    public TrieNode(char letter){
        this.letter = letter;
    }
    public TrieNode(){}

    public char getLetter() {
        return letter;
    }
    public void setLetter(char letter) {
        this.letter = letter;
    }
    public HashMap<Character, TrieNode> getChildren(){
        return children;
    }
    public void setChildren(HashMap<Character, TrieNode> children){
        this.children = children;
    }
    public boolean getIsWord() {
        return isWord;
    }
    public void setIsWord(boolean word) {
        isWord = word;
    }



}
