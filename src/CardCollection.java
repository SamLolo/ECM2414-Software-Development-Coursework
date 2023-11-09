import java.util.ArrayList;

abstract class CardCollection {

    protected ArrayList<Card> cards;
    public abstract String toString();

    public CardCollection() {
        cards = new ArrayList<Card>();
    }
    
    public void addCard(Card card) {
        cards.add(card);
    }
    
    public Card getCard(int index) {
        return cards.get(index);
    }
    
    public ArrayList<Card> getCards() {
        return cards;
    }
    
    public void removeCard(int index) {
        cards.remove(index);
    }
    
    public void removeCard(Card card) {
        cards.remove(card);
    }
    
    public int size() {
        return cards.size();
    }
}
