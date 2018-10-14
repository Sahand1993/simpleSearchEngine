import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Engine {
    private Index index;
    private Searcher searcher;
    private Map<Integer, String> docId2Name = new HashMap<>();
    private Map<String, Integer> docName2Id = new HashMap<>();
    /**
     * Must be unique
     */
    int docsSeen = 0;

    /**
     *
     * @param path2Docs: The path do the documents to be indexed into our SE.
     */
    public Engine(String path2Docs) throws IOException {
        File folder = new File(path2Docs);
        FileReader fileReader;
        int docId;
        String name;
        index = new Index();

        tokenizeFiles(folder);
        searcher = new Searcher(index, this);
    }

    private void tokenizeFiles(File folder) throws IOException {
        String name;
        int docId;
        FileReader fileReader;
        for(File file : folder.listFiles()){
            // Have we seen this file before?
            name = file.getName();
            if(docName2Id.containsKey(name)){
                docId = docName2Id.get(name);
            }else{
                docId = docsSeen++;
                docName2Id.put(name, docId);
                docId2Name.put(docId, name);
            }
            // Tokenize and index 1 doc at a time
            fileReader = new FileReader(file);
            Tokenizer tokenizer = new Tokenizer(fileReader);
            index.insert(tokenizer, docId);
        }
    }

    /**
     * @param query: The search query (only handles one-word queries
     * @return a list of file names containing the search term ordered by tf-idf
     */
    public List<PostingsEntry> search(String query) {
        return searcher.search(query);
    }
    public String getDocNameFromId(int docId){
        return docId2Name.get(docId);
    }
}
