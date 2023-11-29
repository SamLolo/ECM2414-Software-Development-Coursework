import java.util.Random;

public class Hand extends CardCollection {

    private Random selector;
    
    public Hand() {
        // Instancaiate extended CardCollection class and setup global random number generator
        super();
        selector = new Random();
    }

    public Card getRandomCard() {
        // Convert protected BlockingQueue into array and get a random integer between 0 and 4 as there are 5 cards in the hand
        Object[] cardsArray = cards.toArray();
        int n = selector.nextInt(4);

        // Return the Card stored at the value generated above in the Array, type casting to Card from Object
        // This casting is safe as cardsArray will only contain Card objects
        return (Card)cardsArray[n];
    }

    public void removeCard(Card card) {
        // If Card object passed in is inside Cards queue, remove the instance of that card
        if (cards.contains(card)) {
            cards.remove(card);
        }
    }
}
