import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Card Class Tests")
public class CardTest {

    @Nested
    @DisplayName("Card Creation Tests")
    class CardCreationTests {

        @Test
        @DisplayName("Test card creation with valid value")
        void testCardCreationWithValidValue() {
            int value = 5;
            Card card = new Card(value);
            assertEquals(value, card.getValue());
        }

        @Test
        @DisplayName("Test card creation with zero value")
        void testCardCreationWithZeroValue() {
            int value = 0;
            Card card = new Card(value);
            assertEquals(value, card.getValue());
        }

        @Nested
        @DisplayName("Card ToString Tests")
        class CardToStringTests {

            @Test
            @DisplayName("Test card toString method")
            void testCardToString() {
                int value = 8;
                Card card = new Card(value);
                assertEquals(Integer.toString(value), card.toString());
            }
        }

        @Nested
        @DisplayName("Card Equality Tests")
        class CardEqualityTests {

            @Test
            @DisplayName("Test equality of two cards with the same value")
            void testEqualityOfTwoCardsWithSameValue() {
                int value = 10;
                Card card1 = new Card(value);
                Card card2 = new Card(value);
                assertEquals(card1.getValue(), card2.getValue());
            }

            @Test
            @DisplayName("Test inequality of two cards with different values")
            void testInequalityOfTwoCardsWithDifferentValues() {
                int value1 = 3;
                int value2 = 7;
                Card card1 = new Card(value1);
                Card card2 = new Card(value2);
                assertNotEquals(card1, card2);
            }
        }
    }
}