import java.util.*;

class Index {
    private Map<String, PostingsList> index = new HashMap<>();
    private Map<Integer, Integer> docLengths = new HashMap<>();
    private void insert(String token, int docId) {
        if (!index.containsKey(token)){
            // The token has not been seen before and thus has no PostingsList
            PostingsList postings = new PostingsList();
            postings.add(docId);
            index.put(token, postings);
        }else{
            // We have seen token before. Add docId to existing list of postings
            index.get(token).add(docId);
        }
    }

    /**
     * Returns a list of PostingsEntry:s matching the query.
     * @param query
     * @return
     */
    List<PostingsEntry> getDocuments(String query) {
        return index.get(query);
    }

    void insert(Tokenizer tokenizer, int docId) {
        docLengths.put(docId, tokenizer.numberOfTokens());
        String token;
        while(tokenizer.hasMoreTokens()){
            token = tokenizer.removeToken();
            insert(token, docId);
        }
    }

    int getDocLength(int docId) {
        return docLengths.get(docId);
    }

    int documentsIndexed(){
        return docLengths.size();
    }
}
