import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class CardGame {

    private static ArrayList<Player> players = new ArrayList<Player>();

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(ArrayList<Player> Players) {
        players = Players;
    }


    static ArrayList<Card> loadPack(String file, int length) {
        ArrayList<Card> cards = new ArrayList<Card>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();

            while (value != null) {
                int nValue = 0;

                try {
                    nValue = Integer.parseInt(value);
                    if (nValue <= 0) {
                        throw new NumberFormatException("Negative Integer found!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid value '" + value + "' found in " + file + "!");
                    reader.close();
                    return new ArrayList<Card>();
                }

                cards.add(new Card(nValue));
                value = reader.readLine();
            }
            reader.close();

            if (cards.size() == 8 * length) {
                return cards;
            } else {
                System.out.println("The pack provided must contain " + (8 * length) + " cards to be used for " + length + " players!");
                return new ArrayList<Card>();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file: " + file);
            return cards;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ArrayList<Card>();
        }
    }

    public static ArrayList<Deck> createDecks(int n) {
        ArrayList<Deck> decks = new ArrayList<Deck>();
        for (int i = 0; i < n; i++) {
            decks.add(new Deck());
        }
        return decks;
    }

    static void addPlayers(int n, ArrayList<Deck> decks) {
        for (int i = 0; i < n; i++) {
            if (i < n - 1) {
                players.add(new Player(decks.get(i), decks.get(i + 1)));
            } else {
                players.add(new Player(decks.get(i), decks.get(0)));
            }
        }
    }

    static void dealCardsToPlayers(ArrayList<Card> pack) {
        for (int i = 0; i < 4; i++) {
            for (Player player : players) {
                Card card = pack.remove(0);
                player.drawCard(card);
            }
        }
    }

    static void dealCardsToDecks(ArrayList<Deck> decks, ArrayList<Card> pack) {
        for (int i = 0; i < 4; i++) {
            for (Deck deck : decks) {
                Card card = pack.remove(0);
                deck.addCard(card);
            }
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the number of players:");
        int n = -1;
        while (n <= 0) {
            try {
                n = input.nextInt();
                if (n <= 0) {
                    System.out.println("Number of players must be greater than 0!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Number of players must be an integer!");
            } finally {
                input.nextLine();
            }
        }

        ArrayList<Card> cards = new ArrayList<Card>();
        System.out.println("\nPlease enter the filename containing the pack of cards:");
        while (cards.isEmpty()) {
            String file = input.nextLine();
            cards = loadPack(file, n);
        }
        input.close();
        System.out.println();

        ArrayList<Deck> decks = createDecks(n);
        addPlayers(n, decks);
        dealCardsToPlayers(cards);
        dealCardsToDecks(decks, cards);

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            player.start();
        }
    }

}