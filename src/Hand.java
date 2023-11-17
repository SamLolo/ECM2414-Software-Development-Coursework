import java.util.Random;

public class Hand extends CardCollection {
    
    public Hand() {
        super();
    }

    public Card getRandomCard() {
        Random selector = new Random();
        int n = selector.nextInt(3);
        return cards.get(n);
    }

    public void removeCard(Card card) {
        if (cards.contains(card)) {
            cards.remove(card);
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < cards.size(); i++) {
            str += " "+cards.get(i).toString();
        }
        return str;
    }
}
