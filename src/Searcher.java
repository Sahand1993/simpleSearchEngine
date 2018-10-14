import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Searcher {
    Index index;
    Engine engine;
    public Searcher(Index index, Engine engine) {
        this.index = index;
        this.engine = engine;
    }

    /**
     *
     * @param query: The search query (only handles one-word queries
     * @return a list of postingsEntries matching the query, each with an assigned tf-idf score
     */
    public List<PostingsEntry> search(String query) {
        List<PostingsEntry> indexHits = index.getDocuments(query);
        if(indexHits != null) {
            for (PostingsEntry posting : indexHits) {
                double score = tfIdf(posting, indexHits.size());
                posting.setScore(score);
            }
            Collections.sort(indexHits, Collections.reverseOrder());
            return indexHits;
        }
        return new ArrayList<>(); // Return empty list if token not in index.
    }

    // FIXME: scoring doesn't seem to work right
    private double tfIdf(PostingsEntry posting, int docsWithToken){
        double tf = (double) posting.getWordFreq() / index.getDocLength(posting.getDocId());
        double idf = Math.log10((double) index.documentsIndexed() / docsWithToken) + 1e-10; // Add small number so that ranking will still be made for one word queries with tokens that appear in all documents.
        return tf * idf;
    }
}
