public class Main {
    public static void main(String[] args) {
        TrieTree trieTree = new TrieTree();
        trieTree.insert("amora");
        trieTree.insert("amor");
        trieTree.insert("a");
        if( trieTree.search("a") ){
            System.out.printf("true");
        }else {
            System.out.println("false");
        }
    }
}
