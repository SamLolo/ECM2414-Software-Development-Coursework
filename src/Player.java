import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player extends Thread {
    private static final AtomicInteger counter = new AtomicInteger();
    private static AtomicBoolean gameOver = new AtomicBoolean(false);
    
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

    public Boolean checkWin() {
        Card lastCard = hand.getCard(0);
        if (lastCard.getValue() != identifier) {
            return false;
        }

        for (int i=1; i < hand.size(); i++) {
            Card newCard = hand.getCard(i);
            if (lastCard.getValue() != newCard.getValue()) {
                return false;
            }
        }
        return true;
    }

    public Card getRandomDiscard() {
        Card toDiscard = hand.getRandomCard();
        while (toDiscard.getValue() == identifier) {
            toDiscard = hand.getRandomCard();
        }
        return toDiscard;
    }

    @Override
    public void run() {
        if (checkWin()) {
            System.out.println("Player "+identifier+" wins!");
            gameOver.set(true);
        }
        
        while (!gameOver.get()) {
            System.out.println("Player "+identifier+" running!");
            Card newCard = leftDeck.removeCard(0);
            hand.addCard(newCard);
            Card toDiscard = getRandomDiscard();
            hand.removeCard(toDiscard);
            rightDeck.addCard(toDiscard);
            if (checkWin() & !gameOver.get()) {
                System.out.println("Player "+identifier+" wins!");
                gameOver.set(true);
            }
        }
        System.out.println("Player "+identifier+" finished!");
    }
}