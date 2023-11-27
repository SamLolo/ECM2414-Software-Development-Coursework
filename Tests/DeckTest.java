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

        @Test
        @DisplayName("Test deck creation")
        void testDeckCreation() {
            Deck deck = new Deck();
            assertNotNull(deck);
            assertEquals(0, deck.size());
        }
    }

    @Nested
    @DisplayName("Deck Output Tests")
    class DeckOutputTests {

        @Test
        @DisplayName("Test deck output")
        void testDeckOutput() {
            Deck deck = new Deck();
            deck.addCard(new Card(1));
            deck.addCard(new Card(2));

            deck.outputDeck();

            // Check if the output file has been created and contains the expected content
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

        @Test
        @DisplayName("Test deck identifier uniqueness")
        void testDeckIdentifierUniqueness() {
            Deck deck1 = new Deck();
            Deck deck2 = new Deck();
            assertNotEquals(deck1.getIdentifier(), deck2.getIdentifier());
        }
    }
}