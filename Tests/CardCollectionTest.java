import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class CardCollectionTest {
    CardCollection cardCollection = new CardCollection() {
        @Override
        public String toString() {
            return null;
        }
    };

    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;

    ArrayList<Card> cards = new ArrayList<Card>();

    CardCollectionTest() {
    }

    @BeforeEach
    void setUp() {
        card1 = new Card(3);
        card2 = new Card(4);
        card3 = new Card(6);
        card4 = new Card(7);

        cardCollection.addCard(card1);
        cardCollection.addCard(card2);
        cardCollection.addCard(card3);
        cardCollection.addCard(card4);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
    }

    @Test
    void testAddCards() {

        assertEquals(4, cardCollection.size());
        assertEquals(card1, cardCollection.getCard(0));
        assertEquals(card2, cardCollection.getCard(1));

    }

    @Test
    void testGetAllCards() {
        assertEquals(cards, cardCollection.getCards());
    }

    @Test
    void testRemoveCard() {
        cardCollection.removeCard(0);
        cards.remove(0);

        assertEquals(cards, cardCollection.getCards());

    }

    @Test
    void testCardCollectionSize() {
        assertEquals(cards.size(), cardCollection.size());
    }


}