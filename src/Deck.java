import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Deck extends CardCollection {
    private static final AtomicInteger counter = new AtomicInteger();
    
    private final int identifier;
    
    public Deck() {
        // Instanciate extended CardCollection class and create unique integer identifer from static counter
        super();
        identifier = counter.incrementAndGet();
    }

    public void outputDeck() {
        // Create buffered file writer output for each deck and write the contents of the cards queue
        int attempts = 3;
        while (attempts >= 1) {
            try {
                BufferedWriter output = new BufferedWriter( new FileWriter("deck"+identifier+"_output.txt"));
                output.write("deck "+identifier+" contents: "+toString());
                output.close();
                break;

            // If IOException occurs, reduce max attempts by 1 and try again. If out of attempts, method will finish without output file
            } catch(IOException ex) {
                attempts -= 1;
            }
        }
    }

    public int getIdentifier() {
        return identifier;
    }
}
