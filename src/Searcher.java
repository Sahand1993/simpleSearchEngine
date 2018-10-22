import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Searcher {
    /**
     * The index we conduct our searches on.
     */
    private Index index;

    Searcher(Index index) {
        this.index = index;
    }

    /**
     * Search for query in index.
     * @param query The search query (only handles one-word queries.
     * @return A list of documents matching the query, each with an assigned tf-idf score.
     */
    List<PostingsEntry> search(String query) {
        List<PostingsEntry> indexHits = index.getDocuments(query);
        if(indexHits != null) {
            for (PostingsEntry posting : indexHits) {
                double score = tfIdf(posting, indexHits.size());
                posting.setScore(score);
            }
            indexHits.sort(Collections.reverseOrder());
            return indexHits;
        }
        return new ArrayList<>(); // Return empty list if token not in index.
    }

    /**
     * Calculates the tf-idf score of a document.
     * @param posting The document to be scored.
     * @param docsWithToken Number of documents in index containing token.
     * @return The score of the document.
     */
    private double tfIdf(PostingsEntry posting, int docsWithToken){
        double tf = (double) posting.getWordFreq() / index.getDocLength(posting.getDocId());
        double idf = Math.log10((double) index.getDocumentsIndexed() / docsWithToken) + 1e-10; // Adding small number so that ranking will still work for tokens present in all documents.
        return tf * idf;
    }
}
