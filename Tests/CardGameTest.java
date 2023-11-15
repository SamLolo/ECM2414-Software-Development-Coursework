import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CardGameTest {

    @Test
    void testCreateDeck() {
        ArrayList<Deck> decks = CardGame.createDecks(3);

    }
}
