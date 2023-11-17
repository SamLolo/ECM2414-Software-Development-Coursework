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
    
    public Card removeCard(int index) {
        Card card = cards.remove(index);
        return card;
    }
    
    public int size() {
        return cards.size();
    }
}
