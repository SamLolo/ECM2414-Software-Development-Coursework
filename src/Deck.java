import java.util.ArrayList;

public class Deck {
    
    private int number;
    protected ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
    }
    
    public Deck(int n) {
        number = n;
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
    
    @Override
    public String toString() {
        String str = "deck "+number+" contents:";
        for (int i = 0; i < cards.size(); i++) {
            str += " "+cards.get(i).toString();
        }
        return str;
    }
}
