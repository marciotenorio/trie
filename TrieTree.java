import java.util.*;

public class TrieTree {
    private TrieNode root;
    private HashMap<String, Boolean> wordList = new HashMap<>();

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

    public void printSuggestions(HashMap<String, Boolean> wordList){
        for(Map.Entry<String, Boolean> pair : wordList.entrySet()){
            if( pair.getValue() ) {
                System.out.println(pair.getKey());
            }
        }
    }

    public void printSuggestions(HashMap<String, Boolean> wordList, int qtd){//3
        int count = 0;
        for(Map.Entry<String, Boolean> pair : wordList.entrySet()){
            if( pair.getValue() )
                System.out.println(pair.getKey());
            if( count > qtd  )
                return;
            count++;
        }
    }

    public void suggestions(String prefix, TrieNode trieNode){
        if( trieNode.getChildren().isEmpty() )
            return;
        HashMap<Character, TrieNode> children = trieNode.getChildren();
        StringBuilder generatingWord = new StringBuilder(prefix);

        for(Map.Entry<Character, TrieNode> pair : children.entrySet()){
            generatingWord.append(pair.getKey());

            if( wordList.containsKey(generatingWord.toString()) ){
                if( children.get(pair.getKey()) != null )
                    suggestions(generatingWord.toString(), children.get(pair.getKey()));
            }else{
                //add word and telling if is word or not
                wordList.put(generatingWord.toString(), pair.getValue().getIsWord());
                if( children.get(pair.getKey()) != null ) {
                    suggestions(generatingWord.toString(), children.get(pair.getKey()));
                    removeLastCharacter(generatingWord.toString());
                    generatingWord = new StringBuilder(removeLastCharacter(generatingWord.toString()));
                }
            }

        }
    }

    public static String removeLastCharacter(String str) {
        String result = null;
        if ((str != null) && (str.length() > 0)) {
            result = str.substring(0, str.length() - 1);
        }
        return result;
    }

    public void suggestions(String prefix){
        TrieNode trieNode = trieNodeLastLetter(prefix);
        wordList.put(prefix, trieNode.getIsWord());
        suggestions(prefix, trieNode);
        printSuggestions(wordList);
    }

    public void suggestions(String prefix, int qtd){
        TrieNode trieNode = trieNodeLastLetter(prefix);
        wordList.put(prefix, trieNode.getIsWord());
        suggestions(prefix, trieNode);
        printSuggestions(wordList, qtd);
    }

    public boolean prefixExists(String prefix){
        HashMap<Character, TrieNode> children = root.getChildren();
        char letter;
        TrieNode trieNode;

        for (int i = 0; i < prefix.length(); i++) {
            letter = prefix.charAt(i);
            if( children.containsKey(letter) ){
                trieNode = children.get(letter);
                children = trieNode.getChildren();
            }
            else{
                return false;
            }
        }
        return true;
    }

    public TrieNode trieNodeLastLetter(String word){
        HashMap<Character, TrieNode> children = root.getChildren();
        TrieNode trieNode = new TrieNode();

        for (int i = 0; i < word.length(); i++) {
            trieNode = children.get(word.charAt(i));
            children = trieNode.getChildren();
        }
        return trieNode;
    }

}
