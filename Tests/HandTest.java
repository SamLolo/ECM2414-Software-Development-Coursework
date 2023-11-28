import org.junit.jupiter.api.*;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Hand Class Tests")
public class HandTest {

    @Nested
    @DisplayName("Hand Creation Tests")
    class HandCreationTests {
        //Test Suite for hand creation functionality

        @Test
        @DisplayName("Test hand creation")
        void testHandCreation() {
            //Test the creation of a hand and ensure it is not null, and its initial size is 0
            Hand hand = new Hand();
            assertNotNull(hand);
            assertEquals(0, hand.size());
        }
    }

    @Nested
    @DisplayName("Hand Random Card Tests")
    class HandRandomCardTests {
        //Test Suite for obtaining a random card from the hand

        @Test
        @DisplayName("Test get random card from hand")
        void testGetRandomCard() {
            //Test getting a random card from the hand

            //Create a hand and add cards
            Hand hand = new Hand();
            Set<Integer> values = new HashSet<>();

            for (int i = 1; i <= 5; i++) {
                hand.addCard(new Card(i));
                values.add(i);
            }

            //Repeat to ensure randomness
            for (int i = 0; i < 10; i++) {
                Card randomCard = hand.getRandomCard();

                //Check if the random card has a valid value
                assertTrue(values.contains(randomCard.getValue()));
            }
        }
    }

    @Nested
    @DisplayName("Hand Remove Card Tests")
    class HandRemoveCardTests {
        //Test Suite for removing a card from the hand

        @Test
        @DisplayName("Test remove card from hand")
        void testRemoveCard() {
            //Test removing a card from the hand

            //Create a hand and two cards
            Hand hand = new Hand();
            Card card1 = new Card(1);
            Card card2 = new Card(2);

            //Add cards to the hand and remove one
            hand.addCard(card1);
            hand.addCard(card2);

            //Check the size and the remaining card
            assertEquals(2, hand.size());

            hand.removeCard(card2);

            assertEquals(1, hand.size());
            assertEquals(card1, hand.getCard());
        }
    }
}