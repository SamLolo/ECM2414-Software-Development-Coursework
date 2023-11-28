import org.junit.jupiter.api.*;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class CardCollectionTest {

    @Nested
    class EmptyCardCollectionTests {

        private CardCollection emptyCollection;

        @BeforeEach
        void setUp() {
            emptyCollection = new CardCollection() {
            };
        }

        @Test
        void testEmptyCollectionSize() {
            assertEquals(0, emptyCollection.size());
        }

        @Test
        void testEmptyCollectionToString() {
            assertEquals("", emptyCollection.toString());
        }

        @Test
        void testEmptyCollectionIterator() {
            assertFalse(emptyCollection.getIterator().hasNext());
        }
    }

    @Nested
    class NonEmptyCardCollectionTests {

        private CardCollection nonEmptyCollection;

        @BeforeEach
        void setUp() {
            nonEmptyCollection = new CardCollection() {
            };
            nonEmptyCollection.addCard(new Card(1));
            nonEmptyCollection.addCard(new Card(2));
            nonEmptyCollection.addCard(new Card(3));
        }

        @Test
        void testNonEmptyCollectionSize() {
            assertEquals(3, nonEmptyCollection.size());
        }

        @Test
        void testNonEmptyCollectionToString() {
            assertEquals("1 2 3", nonEmptyCollection.toString());
        }

        @Test
        void testGetCard() {
            assertEquals(new Card(1).getValue(), nonEmptyCollection.getCard().getValue());
        }

        @Test
        void testRemoveCard() {
            Card removedCard = nonEmptyCollection.removeCard();
            assertEquals(1, removedCard.getValue());
            assertEquals(2, nonEmptyCollection.size());
        }
    }
}