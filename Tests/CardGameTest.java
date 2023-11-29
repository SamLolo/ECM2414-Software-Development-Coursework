import org.junit.jupiter.api.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CardGameTest {

    @Nested
    @DisplayName("Load Pack Tests")
    class LoadPackTests {
        //The Test Suite to test the ability of the function LoadPack

        @Test
        @DisplayName("Load pack with valid file")
        void loadPackWithValidFile() {
            //Test with a valid file - Should produce no Error
            ArrayList<Card> cards = CardGame.get_loadPack("validPack.txt", 4);
            assertFalse(cards.isEmpty());
            assertEquals(32, cards.size());
        }

        @Test
        @DisplayName("Load pack with Short file")
        void loadPackWithTooShortFile() {
            //Test With a file that doesn't contain enough values
            ArrayList<Card> cards = CardGame.get_loadPack("tooShortPack.txt", 4);
            assertTrue(cards.isEmpty());
        }

        @Test
        @DisplayName("Load pack with contains a negative file")
        void loadPackWithNegativeFile() {
            //Test With a file that contains a negative
            ArrayList<Card> cards = CardGame.get_loadPack("negativePack.txt", 4);
            assertTrue(cards.isEmpty());
        }

        @Test
        @DisplayName("Load pack with contains a zero file")
        void loadPackWithZeroFile() {
            //Test With a file that contains a zero
            ArrayList<Card> cards = CardGame.get_loadPack("zeroPack.txt", 4);
            assertTrue(cards.isEmpty());
        }

        @Test
        @DisplayName("Load pack with contains a zero file")
        void loadPackWithNonExistentFile() {
            //Test With a file that contains a zero
            ArrayList<Card> cards = CardGame.get_loadPack("NonExistentPack.txt", 4);
            assertTrue(cards.isEmpty());
        }
    }

    @Nested
    @DisplayName("Deck and Player Tests")
    class DeckAndPlayerTests {
        //The Test Suite to test the functionality of the deck and the player from the GardGame

        @Test
        @DisplayName("Create decks")
        void createDecks() {
            //Test that creating a new deck actually adds a new deck to the CardGame
            int numberOfDecks = CardGame.getDecks().size();
            CardGame.get_createDecks(3);
            assertEquals(numberOfDecks + 3, CardGame.getDecks().size());
        }

        @Test
        @DisplayName("Add players")
        void addPlayers() {
            //Test that adding players will increase the number of players in the GardGame
            int numberOfPlayers = CardGame.getPlayers().size();

            CardGame.get_createDecks(2);
            CardGame.get_addPlayers(2);

            assertEquals(numberOfPlayers + 2, CardGame.getPlayers().size());

            //Test the decks linked to the new player are present and correct
            assertNotNull(CardGame.getPlayers().get(0).getLeftDeck());
            assertNotNull(CardGame.getPlayers().get(0).getRightDeck());
            assertNotEquals(CardGame.getPlayers().get(0).getLeftDeck(), CardGame.getPlayers().get(0).getRightDeck());
        }

        @Test
        @DisplayName("Deal cards to players")
        void dealCardsToPlayers() {
            //Test that cards uploaded from a pack are successfully dealt to the created players

            //Generates a Pack of Cards
            ArrayList<Card> pack = new ArrayList<>();
            for (int i = 1; i <= 16; i++) {
                pack.add(new Card(i));
            }

            //Creates decks and players and then deals the cards to the players
            CardGame.get_createDecks(2);
            CardGame.get_addPlayers(2);
            CardGame.get_dealCardsToPlayers(pack);

            //Checks that the hands of the players contain the correct number of cards
            for (Player player : CardGame.getPlayers()) {
                assertEquals(4, player.getHand().size());
            }

            //Check that the cards were dealt in a Round Robin fashion
            ArrayList<Player> players = CardGame.getPlayers();

            //Player 1 should have all odd cards
            assertEquals(1, players.get(0).getHand().removeCard().getValue());
            assertEquals(3, players.get(0).getHand().removeCard().getValue());
            assertEquals(5, players.get(0).getHand().removeCard().getValue());
            assertEquals(7, players.get(0).getHand().removeCard().getValue());

            //Player 2 should have all even cards
            assertEquals(2, players.get(1).getHand().removeCard().getValue());
            assertEquals(4, players.get(1).getHand().removeCard().getValue());
            assertEquals(6, players.get(1).getHand().removeCard().getValue());
            assertEquals(8, players.get(1).getHand().removeCard().getValue());
        }

        @Test
        @DisplayName("Deal cards to decks")
        void dealCardsToDecks() {
            //Test that cards uploaded from a pack are successfully dealt to the created decks

            //Generates a Pack of Cards
            ArrayList<Card> pack = new ArrayList<>();
            for (int i = 1; i <= 16; i++) {
                pack.add(new Card(i));
            }

            //Creates two decks and adds cards to it
            CardGame.get_createDecks(2);
            CardGame.get_dealCardsToDecks(CardGame.getDecks(), pack);

            //Checks that the size of the decks is equal to the number of cards put into each one
            for (Deck deck : CardGame.getDecks()) {
                assertEquals(4, deck.size());
            }

            //Check that the cards were dealt in a Round Robin fashion
            ArrayList<Deck> decks = CardGame.getDecks();

            //Deck 1 should have all odd cards
            assertEquals(1, decks.get(0).removeCard().getValue());
            assertEquals(3, decks.get(0).removeCard().getValue());
            assertEquals(5, decks.get(0).removeCard().getValue());
            assertEquals(7, decks.get(0).removeCard().getValue());

            //Deck 2 should have all even cards
            assertEquals(2, decks.get(1).removeCard().getValue());
            assertEquals(4, decks.get(1).removeCard().getValue());
            assertEquals(6, decks.get(1).removeCard().getValue());
            assertEquals(8, decks.get(1).removeCard().getValue());
        }
    }
}