import java.util.*;

class Index {

    private Map<String, PostingsList> index = new HashMap<>();
    private Map<Integer, Integer> docLengths = new HashMap<>();

    /**
     * Inserts token into index.
     * @param token The token to be inserted.
     * @param docId The document that the token was found in.
     */
    private void insert(String token, int docId) {
        // First time seeing token?
        if (!index.containsKey(token)){
            // Add new postings list with docId in it to index.
            PostingsList postings = new PostingsList();
            postings.add(docId);
            index.put(token, postings);
        }else{
            // Add docId to existing list of postings
            PostingsList existing = index.get(token);
            existing.add(docId);
        }
    }

    /**
     * Returns documents matching the query.
     * @param query One word search query
     * @return List of documents
     */
    List<PostingsEntry> getDocuments(String query) {
        return index.get(query);
    }

    /**
     * Inserts all tokens from document in tokenizer into index.
     * @param tokenizer Contains all tokens.
     * @param docId The document containing the tokens.
     */
    void insert(Tokenizer tokenizer, int docId) {
        docLengths.put(docId, tokenizer.initialSize());
        while(tokenizer.hasMoreTokens()){
            String token = tokenizer.removeToken();
            insert(token, docId);
        }
    }

    int getDocLength(int docId) {
        return docLengths.get(docId);
    }

    int getDocumentsIndexed(){
        return docLengths.size();
    }
}
