import java.io.*;
import java.util.LinkedList;

class Tokenizer {
    /**
     * The ascii code for space
     */
    private static final int SPACE = 32;

    private Reader reader;

    /**
     * The token queue.
     */
    private LinkedList<String> tokens = new LinkedList<>();


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
     *
     * @return Total number of tokens in the token queue
     */
    int numberOfTokens() {
        return tokens.size();
    }
}
