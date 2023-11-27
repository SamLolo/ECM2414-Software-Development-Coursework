import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class CardGame {

    private static ArrayList<Player> players = new ArrayList<Player>();
    private static ArrayList<Deck> decks = new ArrayList<Deck>();

    public static ArrayList<Player> getPlayers() {
        return players;
    }
    public static ArrayList<Deck> getDecks() {
        return decks;
    }

    public static void setPlayers(ArrayList<Player> Players) {
        players = Players;
    }

    public static void gameOver(int winner) {
        // Output winner to console
        System.out.println("player "+winner+" wins");

        // Interupt any other player threads that are not the winner so they know to finish
        for (Player player: players) {
            if (player.getIdentifier() != winner) {
                player.interrupt();
            }
        }

        // Make sure each deck creates an output file of it's contents at the end of the game
        for (Deck deck: decks) {
            deck.outputDeck();
        }
    }

    static ArrayList<Card> loadPack(String file, int length) {
        // Create an empty pack of cards
        ArrayList<Card> cards = new ArrayList<Card>();

        // Try opening the filename passed in and read the first line
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();

            // Whilst next line in the file exists, try parsing the string of each line into an integer
            while (value != null) {
                int nValue;
                try {
                    nValue = Integer.parseInt(value);

                    // If integer is negative, throw an error as this is an invalid value for a card value
                    if (nValue <= 0) {
                        throw new NumberFormatException("Negative Integer found!");
                    }

                // If integer parsing fails, or negative integer found above, output error to console, 
                // and return empty pack to show load has failed
                } catch (NumberFormatException e) {
                    System.out.println("Invalid value '" + value + "' found in " + file + "!");
                    reader.close();
                    return new ArrayList<Card>();
                }

                // If correct integer found, create new Card object with that value and add to pack
                cards.add(new Card(nValue));
                value = reader.readLine();
            }
            reader.close();

            // If pack is desired size, return pack otherwise output error to console and return empty pack
            if (cards.size() == 8 * length) {
                return cards;
            } else {
                System.out.println("The pack provided must contain " + (8 * length) + " cards to be used for " + length + " players!");
                return new ArrayList<Card>();
            }

        // If file not found, output error to console and return an empty pack to show load failed
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file: " + file);
            return cards;

        // If IOException occurs, return new ArrayList of cards to guarantee it is empty
        } catch (IOException e) {
            return new ArrayList<Card>();
        }
    }

    public static void createDecks(int n) {
        // Create n new deck objects, adding them to the private ArrayList decks
        for (int i = 0; i < n; i++) {
            decks.add(new Deck());
        }
    }

    static void addPlayers(int n) {
        // Create n players, passing in their left and right deck, with the right deck being deck 1 (index 0) for the last player
        for (int i = 0; i < n; i++) {
            if (i < n - 1) {
                players.add(new Player(decks.get(i), decks.get(i + 1)));
            } else {
                players.add(new Player(decks.get(i), decks.get(0)));
            }
        }
    }

    static void dealCardsToPlayers(ArrayList<Card> pack) {
        // Go round each player in a round-robin fashion, removing the head of the pack and adding to the players hand until all players have 4 cards
        for (int i = 0; i < 4; i++) {
            for (Player player : players) {
                Card card = pack.remove(0);
                player.drawCard(card);
            }
        }
    }

    static void dealCardsToDecks(ArrayList<Deck> decks, ArrayList<Card> pack) {
        // Deal the remaining cards to the deck in a round-robin fashion, with 4 cards in each deck
        for (int i = 0; i < 4; i++) {
            for (Deck deck : decks) {
                Card card = pack.remove(0);
                deck.addCard(card);
            }
        }
    }

    public static void main(String[] args) {
        // Create a scanner to get user input from the console
        Scanner input = new Scanner(System.in);

        // Keep taking user input of number of players until it's a valid non-negative integer
        System.out.println("Please enter the number of players:");
        int n = -1;
        while (n <= 0) {
            try {
                n = input.nextInt();

                // If input is a valid integer, but negative, output error to user. While loop will not be broken
                if (n <= 0) {
                    System.out.println("Number of players must be greater than 0!");
                }
            
            // If user doesn't enter an integer, InputMismatchException will be thrown which is output as an error to the user
            } catch (InputMismatchException e) {
                System.out.println("Number of players must be an integer!");

            // Take empty nextLine of scanner so next input is on it's own line
            } finally {
                input.nextLine();
            }
        }

        // Create blank pack of cards and keep trying to load pack of cards until it return a non-empty pack
        // Will have been checked to be a valid pack inside loadPack method
        ArrayList<Card> cards = new ArrayList<Card>();
        System.out.println("\nPlease enter the filename containing the pack of cards:");
        while (cards.isEmpty()) {
            String file = input.nextLine();
            cards = loadPack(file, n);
        }

        // Close input and add space into console so winner text is nicely spaced later
        input.close();
        System.out.println();

        // Call private methods to setup decks and players using the loaded pack
        createDecks(n);
        addPlayers(n);
        dealCardsToPlayers(cards);
        dealCardsToDecks(decks, cards);

        // Start each player thread created above
        for (Player player: players) {
            player.start();
        }
    }
}