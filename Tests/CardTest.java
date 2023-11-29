import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Card Class Tests")
public class CardTest {

    @Nested
    @DisplayName("Card Creation Tests")
    class CardCreationTests {
        //The Test Suite for testing CardCreation

        @Test
        @DisplayName("Test card creation with valid value")
        void testCardCreationWithValidValue() {
            // Using 5 create a new card and check the new card has the same value
            int value = 5;
            Card card = new Card(value);
            assertEquals(value, card.getValue());
        }
    }

    @Nested
    @DisplayName("Card ToString Tests")
    class CardToStringTests {
        //The Test Suite for testing CardToString

        @Test
        @DisplayName("Test card toString method")
        void testCardToString() {
            //Create a card of value 8 and then test to see if the toString() method works with '1'
            int value = 8;
            Card card = new Card(value);
            assertEquals(Integer.toString(value), card.toString());
        }
    }

    @Nested
    @DisplayName("Card Equality Tests")
    //The Test Suite to check the inequality and equality of different cards
    class CardEqualityTests {

        @Test
        @DisplayName("Test equality of two cards with the same value")
        void testEqualityOfTwoCardsWithSameValue() {
            //The Test to compare two cards with the same value
            int value = 10;
            Card card1 = new Card(value);
            Card card2 = new Card(value);
            assertNotEquals(card1, card2);
            assertEquals(card1.getValue(), card2.getValue());
        }

        @Test
        @DisplayName("Test inequality of two cards with different values")
        void testInequalityOfTwoCardsWithDifferentValues() {
            //The Test to compare two cards with the differing values
            int value1 = 3;
            int value2 = 7;
            Card card1 = new Card(value1);
            Card card2 = new Card(value2);
            assertNotEquals(card1, card2);
            assertNotEquals(card1.getValue(), card2.getValue());

        }
    }
}