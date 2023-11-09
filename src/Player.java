import java.util.concurrent.atomic.AtomicInteger;

public class Player {
    private static final AtomicInteger counter = new AtomicInteger();
    
    private Hand hand;
    private final int identifier;
    
    public Player() {
        hand = new Hand();
        identifier = counter.incrementAndGet();
    }

    public int getIdentifier() {
        return identifier;
    }

    public void drawCard(Card card) {
        hand.addCard(card);
    }
}