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
        hand = new Hand();
        leftDeck = left;
        rightDeck = right;
        identifier = counter.incrementAndGet();
    }

    private void startOutput() {
        try {
            output = new BufferedWriter( new FileWriter("player"+identifier+"_output.txt"));
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void writeOuput(String s) {
        try {
            output.write(s);
            output.write("\n");
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private Boolean checkWin() {
        Iterator<Card> iter = hand.getIterator();
        Card winningCard = iter.next();
        while (iter.hasNext()) {
            Card nextCard = iter.next();
            if (winningCard.getValue() != nextCard.getValue()) {
                return false;
            }
        }
        return true;
    }

    private Card getRandomDiscard() {
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
        startOutput();
        writeOuput("player "+identifier+" initial hand"+hand.toString());

        if (checkWin()) {
            winner.set(identifier);
            CardGame.gameOver(identifier);
            writeOuput("player "+identifier+" wins");
        }
        
        while (!checkGameOver()) {
            Card newCard = leftDeck.removeCard();
            if (newCard == null) {
                break;
            }
            hand.addCard(newCard);
            writeOuput("player "+identifier+" draws a "+newCard.toString()+" from deck "+leftDeck.getIdentifier()); 
            
            Card toDiscard = getRandomDiscard();
            hand.removeCard(toDiscard);
            rightDeck.addCard(toDiscard);
            writeOuput("player "+identifier+" discards a "+toDiscard.toString()+" to deck "+rightDeck.getIdentifier());
            writeOuput("player "+identifier+" current hand is"+hand.toString());
            
            if (checkWin() & !checkGameOver()) {
                winner.set(identifier);
                CardGame.gameOver(identifier);
                writeOuput("player "+identifier+" wins");
                break;
            } else if (checkGameOver()) {
                break;
            }
        }
       
        writeOuput("player "+winner.get()+" has informed player "+identifier+" that player "+winner.get()+" has won");
        writeOuput("player "+identifier+" exits");
        writeOuput("player "+identifier+" final hand:"+hand.toString());
        
        try {
            output.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}