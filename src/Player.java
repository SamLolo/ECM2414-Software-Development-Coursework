import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Player extends Thread {
    private static final AtomicInteger counter = new AtomicInteger();
    private static AtomicInteger winner = new AtomicInteger(-1);
    
    private Hand hand;
    private Deck leftDeck;
    private Deck rightDeck;
    private final int identifier;
    private BufferedWriter output;

    public Deck getLeftDeck() { 
        return leftDeck;
    }

    public Deck getRightDeck() {
        return rightDeck;
    }

    public Hand getHand() { 
        return hand;
    }

    public Player(Deck left, Deck right) {
        // Create hand, set decks and create unique identifier by incrementing static counter
        hand = new Hand();
        leftDeck = left;
        rightDeck = right;
        identifier = counter.incrementAndGet();
    }

    private void startOutput() {
        // Open file stream
        try {
            output = new BufferedWriter( new FileWriter("player"+identifier+"_output.txt"));
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void writeOuput(String s) {
        // Write text parameter into open file writer
        try {
            output.write(s);
            output.write("\n");
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private Boolean checkWin() {
        // Iterate through hand and check if all preciding cards have same value as first card
        Iterator<Card> iter = hand.getIterator();
        Card winningCard = iter.next();
        while (iter.hasNext()) {
            Card nextCard = iter.next();

            // If any non-match is found, return false
            if (winningCard.getValue() != nextCard.getValue()) {
                return false;
            }
        }
        return true;
    }

    private Card getRandomDiscard() {
        // Keep getting random card from hand until it doesn't match player number and return choice
        Card toDiscard = hand.getRandomCard();
        while (toDiscard.getValue() == identifier) {
            toDiscard = hand.getRandomCard();
        }
        return toDiscard;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void drawCard(Card card) {
        hand.addCard(card);
    }

    public static Boolean checkGameOver() {
        if (winner.get() == -1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void run() {
        // Start output with inital hand
        startOutput();
        writeOuput("player "+identifier+" initial hand: "+hand.toString());

        // Check if player has won with hand they've been dealt
        if (checkWin()) {
            winner.set(identifier);
            CardGame.gameOver(identifier);
            writeOuput("player "+identifier+" wins");
        }
        
        // Keep playing the game if no one has won
        while (!checkGameOver()) {

            // Try getting new card from the left deck, breaking current turn if no card found, and add this to the hand
            Card newCard = leftDeck.removeCard();
            if (newCard == null) {
                break;
            }
            hand.addCard(newCard);
            writeOuput("player "+identifier+" draws a "+newCard.toString()+" from deck "+leftDeck.getIdentifier()); 
            
            // Choose 1 of the 5 cards to discard from the hand, and add it to the right deck
            Card toDiscard = getRandomDiscard();
            hand.removeCard(toDiscard);
            rightDeck.addCard(toDiscard);
            writeOuput("player "+identifier+" discards a "+toDiscard.toString()+" to deck "+rightDeck.getIdentifier());
            writeOuput("player "+identifier+" current hand is "+hand.toString());
            
            // If game not over, check if player has won after turn
            if (!checkGameOver() & checkWin()) {
                winner.set(identifier);
                CardGame.gameOver(identifier);
                writeOuput("player "+identifier+" wins");
            }
        }
       
        // Output final information to log file and close writer
        writeOuput("player "+winner.get()+" has informed player "+identifier+" that player "+winner.get()+" has won");
        writeOuput("player "+identifier+" exits");
        writeOuput("player "+identifier+" final hand: "+hand.toString());
        try {
            output.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}