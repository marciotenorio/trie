import java.util.*;

public class TrieTree {
    private TrieNode root;
    private HashMap<String, Boolean> wordList = new HashMap<>();
    //private List<String> wordsList = new ArrayList<>();

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
                //wordsList.add(word);
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

    public void printSuggestions(HashMap<String, Boolean> wordList, int qtd){
        int count = 0;
        for(Map.Entry<String, Boolean> pair : wordList.entrySet()){
            if( qtd > count  )
                return;
            if(pair.getValue())
                System.out.println(pair.getKey());
        }
    }

    public void suggestions(String prefix, TrieNode trieNode){
        if( trieNode.getChildren().isEmpty() )
            return;
        HashMap<Character, TrieNode> children = trieNode.getChildren();
        StringBuilder generatingWord = new StringBuilder(prefix);

        for(Map.Entry<Character, TrieNode> pair : children.entrySet()){
            generatingWord.append(pair.getKey());

            if( wordList.containsKey(generatingWord) ){
                suggestions(generatingWord.toString(), children.get(generatingWord));
            }else{
                //add word and telling if is word or not
                wordList.put(generatingWord.toString(), pair.getValue().getIsWord());
                suggestions(generatingWord.toString(), children.get(generatingWord));
            }

        }
    }

    public void suggestions(String prefix){
        TrieNode trieNode = trieNodeLastLetter(prefix);
        wordList.put(prefix, trieNode.getIsWord());
//        if( trieNode.getIsWord() )
//            wordList.put(prefix, true);
//        else
//            wordList.put(prefix, false);
        suggestions(prefix, trieNode);
        printSuggestions(wordList);
    }

    public void suggestions(String prefix, int qtd){
        TrieNode trieNode = trieNodeLastLetter(prefix);
//        if( trieNode.getIsWord() )
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
