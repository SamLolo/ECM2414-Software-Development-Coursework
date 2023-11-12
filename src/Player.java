import java.util.concurrent.atomic.AtomicInteger;

public class Player extends Thread {
    private static final AtomicInteger counter = new AtomicInteger();
    
    private Hand hand;
    private Deck leftDeck;
    private Deck rightDeck;
    private final int identifier;
    
    public Player(Deck left, Deck right) {
        hand = new Hand();
        leftDeck = left;
        rightDeck = right;
        identifier = counter.incrementAndGet();
    }

    public int getIdentifier() {
        return identifier;
    }

    public void drawCard(Card card) {
        hand.addCard(card);
    }

    @Override
    public void run() {
        System.out.println("Player "+identifier+" running!");
    }
}