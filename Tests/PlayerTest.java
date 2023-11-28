import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Player Class Tests")
public class PlayerTest {

    @Nested
    @DisplayName("Player Creation Tests")
    class PlayerCreationTests {
        //Test Suite for player creation functionality

        @Test
        @DisplayName("Test player creation")
        void testPlayerCreation() {
            //Test the creation of a player with left and right decks, and ensure the hand is not null
            Deck leftDeck = new Deck();
            Deck rightDeck = new Deck();
            Player player = new Player(leftDeck, rightDeck);
            assertNotNull(player);
            assertEquals(leftDeck, player.getLeftDeck());
            assertEquals(rightDeck, player.getRightDeck());
            assertNotNull(player.getHand());
        }
    }

    @Nested
    @DisplayName("Player Drawing and Discarding Tests")
    class PlayerDrawingAndDiscardingTests {
        //Test Suite for player drawing and discarding functionality

        @Test
        @DisplayName("Test player drawing and discarding")
        void testPlayerDrawingAndDiscarding() {
            //Test the player drawing cards, discarding, and updating the decks accordingly

            //Create player and decks, add cards to the left deck
            Deck leftDeck = new Deck();
            Deck rightDeck = new Deck();
            Player player = new Player(leftDeck, rightDeck);

            leftDeck.addCard(new Card(1));
            leftDeck.addCard(new Card(2));
            leftDeck.addCard(new Card(3));
            leftDeck.addCard(new Card(4));

            //Player draws and discards
            player.drawCard(new Card(1));
            player.drawCard(new Card(2));
            player.drawCard(new Card(3));
            player.drawCard(new Card(4));

            assertEquals(4, player.getHand().size());
            assertEquals(1, player.getHand().getCard().getValue());

            Card discardedCard = player.get_getRandomDiscard();
            player.getHand().removeCard(discardedCard);
            rightDeck.addCard(discardedCard);

            //Check the state of the player's hand and the decks
            assertEquals(3, player.getHand().size());
            assertEquals(1, rightDeck.size());
            assertEquals(discardedCard, rightDeck.getCard());
        }
    }

    @Nested
    @DisplayName("Player Run Tests")
    class PlayerRunTests {
        //Test Suite for player run method

        @Test
        @DisplayName("Test player run method")
        void testPlayerRun() throws InterruptedException {
            //Test the player's run method in a simulated winning scenario

            //Create player and mock a winning hand
            Deck leftDeck = new Deck();
            Deck rightDeck = new Deck();
            Player player = new Player(leftDeck, rightDeck);

            player.drawCard(new Card(1));
            player.drawCard(new Card(1));
            player.drawCard(new Card(1));
            player.drawCard(new Card(1));

            //Simulate the player's run method
            leftDeck.addCard(new Card(2));
            player.start();
            player.join();

            //Check if the output file contains the expected content
            assertTrue(checkOutputFileContainsLine(player, "player " + player.getIdentifier() + " wins"));
        }

        private boolean checkOutputFileContainsLine(Player player, String expectedLine) {
            //Method to check if the output file contains a specific line
            try (BufferedReader reader = new BufferedReader(new FileReader("player" + player.getIdentifier() + "_output.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(expectedLine)) {
                        return true;
                    }
                }
            } catch (IOException e) {
                fail("IOException occurred while reading the output file");
            }
            return false;
        }
    }
}