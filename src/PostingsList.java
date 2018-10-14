import java.util.ArrayList;
import java.util.List;

public class PostingsList extends ArrayList<PostingsEntry> {
    public void add(int docId) {
        // Make sure that when a document contains the same word more than once, the wordFreq of the relevant postingsEntry is incremented
        int idx = indexOf(docId);
        if (idx > -1) {
            // We've seen this word in document before.
            this.get(idx).incrementWordFreq();
        } else {
            // First time seeing token in document. Create entry with wordFreq 1
            this.add(new PostingsEntry(1, docId));
        }
    }

    public int indexOf(int docId) {
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
