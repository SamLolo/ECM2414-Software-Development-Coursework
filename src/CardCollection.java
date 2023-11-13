import java.util.ArrayList;

abstract class CardCollection {

    protected ArrayList<Card> cards;
    public abstract String toString();

    public CardCollection() {
        cards = new ArrayList<Card>();
    }
    
    public synchronized void addCard(Card card) {
        cards.add(card);
        notify();
    }

    public synchronized void insertCard(Card card, int index) {
        cards.add(index, card);
        notify();
    }
    
    public synchronized Card getCard(int index) {
        return cards.get(index);
    }
    
    public ArrayList<Card> getCards() {
        return cards;
    }
    
    public synchronized Card removeCard(int index) {
        if (cards.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Card card = cards.remove(index);
        return card;
    }

    public synchronized void removeCard(Card card) {
        if (cards.contains(card)) {
            cards.remove(card);
        }
    }
    
    public int size() {
        return cards.size();
    }
}
