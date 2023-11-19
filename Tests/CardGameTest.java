import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;

class CardGameTest {

    @Test
    void testCreateDeck() {
        ArrayList<Deck> decks = CardGame.createDecks(4);

        assertNotNull(decks);
        assertEquals(4, decks.size());

        for (Deck deck : decks) {
            assertNotNull(decks);
            assertTrue(deck.getCards().isEmpty());
        }
    }

    @Nested
    @DisplayName("CardGame LoadPack Tests")
    class CardGameLoadPackTest {
        @Test
        public void testLoadPackValidFile() {

            String validFile = "validPack.txt";
            int length = 4;

            ArrayList<Card> cards = CardGame.loadPack(validFile, length);

            assertNotNull(cards);
            assertEquals(8 * length, cards.size());
        }

        @Test
        public void testLoadPackValidFileWithIncorrectLength() {
            String validFile = "validPack.txt";
            int length = 3;

            ArrayList<Card> cards = CardGame.loadPack(validFile, length);

            assertTrue(cards.isEmpty());
        }

        @Test
        public void testLoadPackInvalidFile() {
            String invalidFile = "invalidPack.txt";
            int length = 4;

            ArrayList<Card> cards = CardGame.loadPack(invalidFile, length);

            assertNotNull(cards);
            assertTrue(cards.isEmpty());
        }

        @Test
        public void testLoadPackNegativeValue() {
            // Arrange
            String fileWithNegativeValue = "negativeValuePack.txt";
            int length = 4;

            // Act
            ArrayList<Card> cards = CardGame.loadPack(fileWithNegativeValue, length);

            // Assert
            assertNotNull(cards);
            assertTrue(cards.isEmpty());
        }

        @Test
        public void testLoadPackFileNotFoundException() {
            // Arrange
            String nonExistentFile = "nonExistentPack.txt";
            int length = 4;

            ArrayList<Card> cards = CardGame.loadPack(nonExistentFile, length);

            assertNotNull(cards);
            assertTrue(cards.isEmpty());
        }
    }


    @Nested
    @DisplayName("CardGame AddPlayers Tests")
    class CardGameAddPlayersTest {

        private ArrayList<Deck> decks;

        @BeforeEach
        void setUp() {
            CardGame.setPlayers(new ArrayList<Player>());

            decks = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                decks.add(new Deck());
            }
        }

        @Test
        void testAddPlayers() {
            int numberOfPlayers = 4;
            CardGame.addPlayers(numberOfPlayers, decks);

            ArrayList<Player> players = CardGame.getPlayers();

            assertNotNull(players);
            assertEquals(numberOfPlayers, players.size());

            for (int i = 0; i < numberOfPlayers; i++) {
                Player player = players.get(i);
                assertNotNull(player);
                assertEquals(decks.get(i), player.getLeftDeck());
                assertEquals(decks.get((i + 1) % numberOfPlayers), player.getRightDeck());
            }
        }
    }

    @Nested
    @DisplayName("CardGame DealCardsToPlayers Tests")
    class CardGameDealCardsToPlayersTest {

        private ArrayList<Player> players;
        private ArrayList<Card> pack;

        @BeforeEach
        void setUp() {
            players = new ArrayList<Player>();
            for (int i = 0; i < 4; i++) {
                players.add(new Player(new Deck(), new Deck()));
            }

            pack = new ArrayList<Card>();
            for (int i = 1; i <= 32; i++) {
                pack.add(new Card(i));
            }
        }

        @Test
        void testDealCardsToPlayers() {
            CardGame.setPlayers(players);

            CardGame.dealCardsToPlayers(pack);

            for (Player player : players) {
                assertEquals(4, player.getHand().size());
            }

            CardGame.dealCardsToPlayers(pack);

            for (Player player : players) {
                assertEquals(8, player.getHand().size());
            }

            assertEquals(0, pack.size());

        }
    }

    @Nested
    @DisplayName("CardGame DealCardsToDecks Tests")
    class CardGameDealCardsToDecksTest {

        private ArrayList<Card> pack;

        @BeforeEach
        void setUp() {
            pack = new ArrayList<>();
            for (int i = 1; i <= 32; i++) {
                pack.add(new Card(i));
            }
        }

        @Test
        void testDealCardsToDecks() {
            ArrayList<Deck> decks = CardGame.createDecks(4);

            CardGame.dealCardsToDecks(decks, pack);

            for (Deck deck : decks) {
                assertEquals(4, deck.getCards().size());
            }

            CardGame.dealCardsToDecks(decks, pack);

            for (Deck deck : decks) {
                assertEquals(8, deck.getCards().size());
            }

            assertEquals(0, pack.size());
        }
    }
}
