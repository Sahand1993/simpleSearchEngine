import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        if(args.length != 1){
            System.err.println("Only path to data files should be given as runtime argument");
        }
        String path2Docs = args[0];

        Engine engine = null;
        try {
            engine = new Engine(path2Docs);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Enter query word...");
            String userIn = sc.next();

            if(userIn.equals("q")){
                System.out.println("Quitting...");
                break;
            }

            List<PostingsEntry> results = engine.search(userIn);

            System.out.println("Matching docs:");
            for(PostingsEntry posting : results){
                int docId = posting.getDocId();
                String docName = engine.getDocNameFromId(docId);
                System.out.printf("%s (%f) | ", docName, posting.getScore());
            }
            System.out.println();
        }
    }
}
