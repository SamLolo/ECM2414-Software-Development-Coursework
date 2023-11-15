import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

class PlayerTest {

    static Deck leftDeck;
    static Deck rightDeck;

    @BeforeAll
    static void createTestDecks() {
        leftDeck = new Deck();
        rightDeck = new Deck();
    }

    @Test
    void testGetIdentifier() {
        // Test Player gets assigned an identifier
        Player firstPlayer = new Player(leftDeck, rightDeck);
        assertEquals(firstPlayer.getIdentifier(), 1);

        // Test identifier increments with new player
        Player secondPlayer = new Player(leftDeck, rightDeck);
        assertEquals(secondPlayer.getIdentifier(), 2);
    }

    @Test
    void testDrawCard() {
    }

    @Test
    void testRun() {
    }
}