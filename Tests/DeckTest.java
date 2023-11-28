import org.junit.jupiter.api.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Deck Class Tests")
public class DeckTest {

    @Nested
    @DisplayName("Deck Creation Tests")
    class DeckCreationTests {
        //Test Suite for deck creation functionality

        @Test
        @DisplayName("Test deck creation")
        void testDeckCreation() {
            //Test the creation of a deck and ensure it is not null, and its initial size is 0
            Deck deck = new Deck();
            assertNotNull(deck);
            assertEquals(0, deck.size());
        }
    }

    @Nested
    @DisplayName("Deck Output Tests")
    class DeckOutputTests {
        //Test Suite for deck output functionality

        @Test
        @DisplayName("Test deck output")
        void testDeckOutput() {
            //Test deck output by adding cards, invoking outputDeck(), and checking the output file

            //Create a new deck and add two cards to it
            Deck deck = new Deck();
            deck.addCard(new Card(1));
            deck.addCard(new Card(2));

            //Output the Deck
            deck.outputDeck();

            //Check if the output file has been created and contains the expected content
            try (BufferedReader reader = new BufferedReader(new FileReader("deck" + deck.getIdentifier() + "_output.txt"))) {
                String line = reader.readLine();
                assertEquals("deck " + deck.getIdentifier() + " contents: 1 2", line);

            } catch (IOException e) {
                fail("IOException occurred while reading the output file");
            }
        }
    }

    @Nested
    @DisplayName("Deck Identifier Tests")
    class DeckIdentifierTests {
        //Test Suite for deck identifier uniqueness

        @Test
        @DisplayName("Test deck identifier uniqueness")
        void testDeckIdentifierUniqueness() {
            //Test that the identifiers of two different decks are not equal
            Deck deck1 = new Deck();
            Deck deck2 = new Deck();
            assertNotEquals(deck1.getIdentifier(), deck2.getIdentifier());
        }
    }
}