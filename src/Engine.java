import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Engine {
    private Index index;
    private Searcher searcher;

    /**
     * Mapping docId to docName and vice-versa.
     */
    private Map<Integer, String> docId2Name = new HashMap<>();
    private Map<String, Integer> docName2Id = new HashMap<>();

    /**
     *
     * @param path2Docs: The path do the documents to be indexed into our SE.
     */
    public Engine(String path2Docs) throws IOException {
        File folder = new File(path2Docs);
        index = new Index();
        indexFiles(folder);
        searcher = new Searcher(index);
    }

    /**
     * Adds all files in folder to the index.
     * @param folder The folder containing files to be indexed
     * @throws IOException
     */
    private void indexFiles(File folder) throws IOException {
        FileReader fileReader;
        String name;
        int docId;
        // Tokenize and index one document at a time
        for(File file : folder.listFiles()){
            // Have we seen this file before?
            name = file.getName();
            System.err.println(name);
            if(docName2Id.containsKey(name)){
                docId = docName2Id.get(name);
            }else{
                docId = docName2Id.size();
                docName2Id.put(name, docId);
                docId2Name.put(docId, name);
            }
            // Tokenize and insert into index
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
