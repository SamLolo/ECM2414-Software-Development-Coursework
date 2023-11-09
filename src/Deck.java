import java.util.concurrent.atomic.AtomicInteger;

public class Deck extends CardCollection {
    private static final AtomicInteger counter = new AtomicInteger();
    
    private final int identifier;
    
    public Deck() {
        super();
        identifier = counter.incrementAndGet();
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
