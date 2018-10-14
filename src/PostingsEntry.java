public class PostingsEntry implements Comparable<PostingsEntry> {
    private int wordFreq;
    private int docId;
    private double score;
    public PostingsEntry(int wordFreq, int docId){
        this.wordFreq = wordFreq;
        this.docId = docId;
    }

    public void incrementWordFreq() {
        wordFreq++;
    }

    public int getWordFreq() {
        return wordFreq;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getDocId() {
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
