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
            output.write(toString());
            output.close();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public synchronized void cleanup() {
        outputDeck();
        notifyAll();
    }

    @Override
    public synchronized void addCard(Card card) {
        cards.add(card);
        notifyAll();
    }

    @Override
    public synchronized Card removeCard(int index) {
        if (cards.size() == 0 & !Player.checkGameOver()) {
            try { 
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!Player.checkGameOver()) {
            Card card = cards.remove(index);
            return card;
        } else {
            return null;
        }
    }

    public int getIdentifier() {
        return identifier;
    }
    
    @Override
    public String toString() {
        String str = "deck "+identifier+" contents:";
        for (int i = 0; i < cards.size(); i++) {
            str += " "+cards.get(i).toString();
        }
        return str;
    }
}
