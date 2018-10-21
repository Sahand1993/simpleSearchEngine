/**
 * Represents a document containing a particular token.
 * Compares itself to other instances according to score.
 */
class PostingsEntry implements Comparable<PostingsEntry> {
    /**
     * The number of times the token was found in the document.
     */
    private int wordFreq;

    private int docId;

    /**
     * tf-idf score for document.
     */
    private double score;

    PostingsEntry(int wordFreq, int docId){
        this.wordFreq = wordFreq;
        this.docId = docId;
    }

    void incrementWordFreq() {
        wordFreq++;
    }

    int getWordFreq() {
        return wordFreq;
    }

    double getScore() {
        return score;
    }

    void setScore(double score) {
        this.score = score;
    }

    int getDocId() {
        return docId;
    }

    @Override
    public int compareTo(PostingsEntry other) {
        double diff = this.score - other.getScore();
        if (diff > 0){
            return 1;
        }else if (diff == 0){
            return 0;
        }else{
            return -1;
        }
    }

    @Override
    public String toString(){
        return Integer.toString(docId) + " " + wordFreq + " " + Double.toString(score);
    }
}
