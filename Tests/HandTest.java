import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Hand Class Tests")
public class HandTest {

    @Nested
    @DisplayName("Hand Creation Tests")
    class HandCreationTests {

        @Test
        @DisplayName("Test hand creation")
        void testHandCreation() {
            Hand hand = new Hand();
            assertNotNull(hand);
            assertEquals(0, hand.size());
        }
    }

    @Nested
    @DisplayName("Hand Random Card Tests")
    class HandRandomCardTests {

        @Test
        @DisplayName("Test get random card from hand")
        void testGetRandomCard() {
            Hand hand = new Hand();
            Set<Integer> values = new HashSet<>();

            // Add cards to the hand
            for (int i = 1; i <= 5; i++) {
                hand.addCard(new Card(i));
                values.add(i);
            }

            // Repeat to ensure randomness
            for (int i = 0; i < 10; i++) {
                Card randomCard = hand.getRandomCard();
                assertTrue(values.contains(randomCard.getValue()));
            }
        }
    }

    @Nested
    @DisplayName("Hand Remove Card Tests")
    class HandRemoveCardTests {

        @Test
        @DisplayName("Test remove card from hand")
        void testRemoveCard() {
            Hand hand = new Hand();
            Card card1 = new Card(1);
            Card card2 = new Card(2);

            hand.addCard(card1);
            hand.addCard(card2);

            assertEquals(2, hand.size());

            hand.removeCard(card1);

            assertEquals(1, hand.size());
            assertEquals(card2, hand.getCard());
        }
    }
}