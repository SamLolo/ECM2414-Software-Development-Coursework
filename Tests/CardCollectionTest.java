import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CardCollectionTest {

    @Nested
    class EmptyCardCollectionTests {
        //Test Suite to test when the card collection is empty

        private CardCollection emptyCollection;
        //Create an empty card collection that all the tests can access

        @BeforeEach
        void setUp() {
            //Before each test, allows the empty card collection to be reset
            emptyCollection = new CardCollection() {};
        }

        @Test
        void testEmptyCollectionSize() {
            //Test the size of an empty collection, it should be 0
            assertEquals(0, emptyCollection.size());
        }

        @Test
        void testEmptyCollectionToString() {
            //Test the string representation of an empty collection, it should be an empty string
            assertEquals("", emptyCollection.toString());
        }

        @Test
        void testEmptyCollectionIterator() {
            //Test that the iterator of an empty collection does not have a next element
            assertFalse(emptyCollection.getIterator().hasNext());
        }
    }

    @Nested
    class NonEmptyCardCollectionTests {
        //Test Suite to test when the card collection is not empty

        private CardCollection nonEmptyCollection;
        //Create an empty card collection that all the tests can access

        @BeforeEach
        void setUp() {
            //Before each test set up a non-empty collection with three cards
            nonEmptyCollection = new CardCollection() {};
            nonEmptyCollection.addCard(new Card(1));
            nonEmptyCollection.addCard(new Card(2));
            nonEmptyCollection.addCard(new Card(3));
        }

        @Test
        void testNonEmptyCollectionSize() {
            //Test the size of a non-empty collection, it should be the number of added cards (3 in this case)
            assertEquals(3, nonEmptyCollection.size());
        }

        @Test
        void testNonEmptyCollectionToString() {
            //Test the string representation of a non-empty collection, it should concatenate the card values
            assertEquals("1 2 3", nonEmptyCollection.toString());
        }

        @Test
        void testGetCard() {
            //Test getting a card from the non-empty collection, and compare its value
            assertEquals(1, nonEmptyCollection.getCard().getValue());
        }

        @Test
        void testRemoveCard() {
            //Test removing a card from the non-empty collection, and check the size afterward
            Card removedCard = nonEmptyCollection.removeCard();
            assertEquals(1, removedCard.getValue());
            assertEquals(2, nonEmptyCollection.size());
        }
    }
}