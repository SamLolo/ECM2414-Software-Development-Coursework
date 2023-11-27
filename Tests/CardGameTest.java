import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CardGameTest {

    private ArrayList<Deck> decks;

    @BeforeEach
    void setUp() {
        ArrayList<Player> players = new ArrayList<>();
        decks = new ArrayList<>();
    }

    @Nested
    @DisplayName("Load Pack Tests")
    class LoadPackTests {

        @Test
        @DisplayName("Load pack with valid file")
        void loadPackWithValidFile() {
            ArrayList<Card> cards = CardGame.loadPack("validPack.txt", 4);
            assertFalse(cards.isEmpty());
            assertEquals(32, cards.size());
        }

        @Test
        @DisplayName("Load pack with invalid file")
        void loadPackWithInvalidFile() {
            ArrayList<Card> cards = CardGame.loadPack("invalidPack.txt", 4);
            assertTrue(cards.isEmpty());
        }
    }

    @Nested
    @DisplayName("Deck and Player Tests")
    class DeckAndPlayerTests {

        @Test
        @DisplayName("Create decks")
        void createDecks() {
            int numberOfDecks = CardGame.getDecks().size();
            CardGame.createDecks(3);
            assertEquals(numberOfDecks + 3, CardGame.getDecks().size());
        }

        @Test
        @DisplayName("Add players")
        void addPlayers() {
            int numberOfDecks = CardGame.getDecks().size();
            int numberOfPlayers = CardGame.getPlayers().size();

            CardGame.createDecks(2);
            CardGame.addPlayers(2);

            assertEquals(numberOfPlayers + 2, CardGame.getPlayers().size());
            assertNotNull(CardGame.getPlayers().get(0).getLeftDeck());
            assertNotNull(CardGame.getPlayers().get(0).getRightDeck());
            assertNotEquals(CardGame.getPlayers().get(0).getLeftDeck(), CardGame.getPlayers().get(0).getRightDeck());
        }

        @Test
        @DisplayName("Deal cards to players")
        void dealCardsToPlayers() {
            ArrayList<Card> pack = new ArrayList<>();
            for (int i = 1; i <= 16; i++) {
                pack.add(new Card(i));
            }

            CardGame.createDecks(2);
            CardGame.addPlayers(2);
            CardGame.dealCardsToPlayers(pack);

            for (Player player : CardGame.getPlayers()) {
                assertEquals(4, player.getHand().size());
            }
        }

        @Test
        @DisplayName("Deal cards to decks")
        void dealCardsToDecks() {
            ArrayList<Card> pack = new ArrayList<>();
            for (int i = 1; i <= 16; i++) {
                pack.add(new Card(i));
            }

            CardGame.createDecks(2);
            CardGame.dealCardsToDecks(decks, pack);

            for (Deck deck : decks) {
                assertEquals(4, deck.size());
            }
        }
    }
}