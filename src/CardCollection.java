import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Iterator;

abstract class CardCollection {

    protected BlockingQueue<Card> cards;

    public CardCollection() {
        cards = new LinkedBlockingQueue<Card>();
    }
    
    public void addCard(Card card) {
        cards.add(card);
    }
    
    public Card getCard() {
        return cards.peek();
    }
    
    public Iterator<Card> getIterator() {
        return cards.iterator();
    }
    
    public Card removeCard() {
        // Try returning card at head of collection
        try {
            Card card = cards.take();
            return card;

        // Return null if thread is interupted
        } catch (InterruptedException ex) {
            return null;
        }
    }
    
    public int size() {
        return cards.size();
    }

    @Override
    public String toString() {
        // Return "" if cards is empty, otherwise iterate through using iterator object, 
        // concatenating as a string to be returned
        Iterator<Card> iter = cards.iterator();
        if (!cards.isEmpty()) {
            String str = iter.next().toString();
            while (iter.hasNext()) {
                str += " "+iter.next().toString();
            }
            return str;
        } else {
            return "";
        }
    }
}
