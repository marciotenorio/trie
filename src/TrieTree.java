import java.util.HashMap;

public class TrieTree {
    private TrieNode root;

    public TrieTree(){
        this.root = new TrieNode();
    }

    public void insert(String word){
        HashMap<Character, TrieNode> children = root.getChildren();
        char letterInsert;
        TrieNode trieNode;
        for (int i = 0; i < word.length(); i++) {
            letterInsert = word.charAt(i);
            if( children.containsKey(letterInsert) ){
                trieNode = children.get(letterInsert);
            }
            else{
                trieNode = new TrieNode(letterInsert);
                children.put(letterInsert, trieNode);
            }
            children = trieNode.getChildren();
            if( i == word.length() - 1 ){
                trieNode.setWord(true);
            }
        }
    }

    public boolean search(String word){
        HashMap<Character, TrieNode> children = root.getChildren();
        char letter;
        TrieNode trieNode = new TrieNode();
        for (int i = 0; i < word.length(); i++) {
            letter = word.charAt(i);
            if( children.containsKey(letter) ){
                trieNode = children.get(letter);
                children = trieNode.getChildren();
            }
            else{
                return false;
            }
        }
            return trieNode.isWord();
    }
    //TODO
    public void remove(String word){
        HashMap<Character, TrieNode> children = root.getChildren();
        TrieNode trieNode = new TrieNode();
        char letter;
        for (int i = 0; i < word.length(); i++) {
            
        }
    }
}
