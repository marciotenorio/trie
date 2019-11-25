import java.util.*;

public class TrieTree {
    private TrieNode root;
    private List<String> wordsList = new ArrayList<>();

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
                trieNode.setIsWord(true);
                wordsList.add(word);
            }
        }
    }

    public boolean check(String word){
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
            return trieNode.getIsWord();
    }

    public void remove(String word){
        if( check(word) ) {

            HashMap<Character, TrieNode> children = root.getChildren();
            TrieNode trieNode = new TrieNode();
            char letter;

            for (int i = 0; i < word.length(); i++) {
                letter = word.charAt(i);
                trieNode = children.get(letter);
                children = trieNode.getChildren();
            }
            trieNode.setIsWord(false);
        }
    }

//    public void  suggestions(String prefix){
//        if( prefixExists(prefix) ) {
//
//            HashMap<Character, TrieNode> children = root.getChildren();
//            TrieNode trieNode = TrieNodeLastLetter(prefix);
//            StringBuilder word = new StringBuilder(prefix);
//
//            while (children.isEmpty() ){
//                for (HashMap.Entry<Character, TrieNode> pair : trieNode.getChildren().entrySet() ){
//
//                }
//            }
//
//
//        }
//        System.out.println("ERROR: Prefix do not exists!");
//    }

    public void suggestions(String prefix){
        if( prefixExists(prefix) ){
            for (String arrWords: wordsList) {
                if( arrWords.contains(prefix) ){
                    System.out.println(arrWords);
                }
            }
        }
    }

    public void suggestions(String prefix, int qtd){
        if( prefixExists(prefix) ){
            int count = 0;
            for (String arrWords: wordsList) {
                if( arrWords.contains(prefix) && count < qtd){
                    System.out.println(arrWords);
                    count++;
                }
            }
        }
    }

    public boolean prefixExists(String prefix){
        if ( check(prefix) ){
            return true;
        }

        HashMap<Character, TrieNode> children = root.getChildren();
        TrieNode trieNode = new TrieNode();

        for (int i = 0; i < prefix.length(); i++) {
            trieNode = children.get(prefix.charAt(i));
            children = trieNode.getChildren();
        }

        return !children.isEmpty();
    }

    public TrieNode TrieNodeLastLetter(String word){
        HashMap<Character, TrieNode> children = root.getChildren();
        TrieNode trieNode = new TrieNode();

        for (int i = 0; i < word.length(); i++) {
            trieNode = children.get(word.charAt(i));
            children = trieNode.getChildren();
        }
        return trieNode;
    }

}
