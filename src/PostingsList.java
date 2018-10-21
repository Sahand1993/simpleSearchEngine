import java.util.ArrayList;

class PostingsList extends ArrayList<PostingsEntry> {
    /**
     * Add document to list.
     * @param docId The document to add.
     */
    void add(int docId) {
        int idx = indexOf(docId);
        if (idx > -1) {
            // We've seen this word in document before.
            this.get(idx).incrementWordFreq();
        } else {
            // First time seeing token in document. Create entry with wordFreq 1
            this.add(new PostingsEntry(1, docId));
        }
    }

    /**
     * Returns index of a document in the list.
     * @param docId The document to return index of.
     * @return Index of the document, or -1 if not in list.
     */
    private int indexOf(int docId) {
        int i = 0;
        while (i < this.size()) {
            if (this.get(i).getDocId() == docId) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
