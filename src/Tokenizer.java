import java.io.*;
import java.util.LinkedList;

class Tokenizer {
    private Reader reader;

    /**
     * The ascii code for blankspace
     */
    private static final int SPACE = 32;

    /**
     * The token queue.
     */
    private LinkedList<String> tokens = new LinkedList<>();

    /**
     * The initial size of the queue, before any tokens are read and removed.
     */
    private int initialSize = -1;

    Tokenizer(FileReader fileReader) throws IOException {
        reader = new BufferedReader(fileReader);
        getTokens();
    }

    private void getTokens() throws IOException {
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = reader.read()) != -1){
            if(ch == SPACE){
                if(sb.length() > 0){
                    tokens.addLast(new String(sb));
                    // Empty stringbuilder
                    sb.setLength(0);
                }
                continue;
            }
            sb.append((char)ch);
        }
        if(sb.length() > 0){
            tokens.addLast(new String(sb));
        }
        initialSize = tokens.size();
    }

    /**
     * Removes a token from the head of the queue and returns it
     * @return The head of the token queue
     */
    String removeToken(){
        return tokens.remove();
    }

    boolean hasMoreTokens(){
        return !tokens.isEmpty();
    }

    /**
     * @return The total number of tokens found in the document.
     */
    int initialSize() {
        if(initialSize < 0) {
            throw new RuntimeException("Indexing not finished");
        }else{
            return initialSize;
        }
    }
}
