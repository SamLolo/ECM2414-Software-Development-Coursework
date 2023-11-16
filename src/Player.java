import java.util.concurrent.atomic.AtomicInteger;
import java.io.BufferedWriter;
import java.io.File;
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
    
    public Player(Deck left, Deck right) {
        hand = new Hand();
        leftDeck = left;
        rightDeck = right;
        identifier = counter.incrementAndGet();
        new File("player"+identifier+"_output.txt");
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

    public int getIdentifier() {
        return identifier;
    }

    public void drawCard(Card card) {
        hand.addCard(card);
    }

    public Boolean checkWin() {
        Card lastCard = hand.getCard(0);
        if (lastCard.getValue() != identifier) {
            return false;
        }

        for (int i=1; i < hand.size(); i++) {
            Card newCard = hand.getCard(i);
            if (lastCard.getValue() != newCard.getValue()) {
                return false;
            }
        }
        return true;
    }

    public Card getRandomDiscard() {
        Card toDiscard = hand.getRandomCard();
        while (toDiscard.getValue() == identifier) {
            toDiscard = hand.getRandomCard();
        }
        return toDiscard;
    }

    @Override
    public void run() {
        startOutput();
        writeOuput("player "+identifier+" initial hand"+hand.toString());

        if (checkWin()) {
            System.out.println("player "+identifier+" wins");
            writeOuput("player "+identifier+" wins");
            winner.set(identifier);
        }
        
        while (winner.get() == -1) {
            Card newCard = leftDeck.removeCard(0);
            hand.addCard(newCard);
            writeOuput("player "+identifier+" draws a "+newCard.toString()+" from deck "+leftDeck.getIdentifier()); 
            
            Card toDiscard = getRandomDiscard();
            hand.removeCard(toDiscard);
            rightDeck.addCard(toDiscard);
            writeOuput("player "+identifier+" discards a "+toDiscard.toString()+" to deck "+rightDeck.getIdentifier());
            writeOuput("player "+identifier+" current hand is"+hand.toString());
            
            if (checkWin() & winner.get() == -1) {
                System.out.println("player "+identifier+" wins");
                writeOuput("player "+identifier+" wins");
                winner.set(identifier);
            } else if (winner.get() != -1) {
                writeOuput("player "+winner.get()+" has informed player "+identifier+" that player "+winner.get()+" has won");
            }
        }

        writeOuput("player "+identifier+" exits");
        writeOuput("player "+identifier+" final hand:"+hand.toString());
        
        try {
            output.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}