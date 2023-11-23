import java.util.Random;

public class Hand extends CardCollection {

    private Random selector;
    
    public Hand() {
        super();
        selector = new Random();
    }

    public Card getRandomCard() {
        Object[] cardsArray = cards.toArray();
        int n = selector.nextInt(4);
        return (Card)cardsArray[n];
    }

    public void removeCard(Card card) {
        if (cards.contains(card)) {
            cards.remove(card);
        }
    }
}
