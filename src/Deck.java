import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Deck extends CardCollection {
    private static final AtomicInteger counter = new AtomicInteger();
    
    private final int identifier;
    
    public Deck() {
        super();
        identifier = counter.incrementAndGet();
    }

    public void outputDeck() {
        try {
            BufferedWriter output = new BufferedWriter( new FileWriter("deck"+identifier+"_output.txt"));
            output.write("deck "+identifier+" contents: "+toString());
            output.close();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int getIdentifier() {
        return identifier;
    }
}
