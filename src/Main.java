import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        TrieTree trieTree = new TrieTree();
        trieTree.insert("amora");
        trieTree.insert("amor");
        trieTree.suggestions("am");
//        if( args.length > 0 ) {
//            try {
//                Scanner scanner = new Scanner(new File(args[0]));
//
//                while (scanner.hasNextLine()) {
//                    trieTree.insert(scanner.nextLine());
//                }
//                scanner.close();
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        if( args.length == 3 )
//            trieTree.suggestions(args[1], Integer.parseInt(args[2]));
//        else
//            trieTree.suggestions(args[1]);
    }

}
