import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class CardGame {

    private static ArrayList<Card> loadPack(String file) {
        ArrayList<Card> cards = new ArrayList<Card>();

        try {
            BufferedReader reader = new BufferedReader( new FileReader(file));
            String value = reader.readLine();

            while (value != null) {
                int nValue = 0;
                
                try {
                    nValue = Integer.parseInt(value);
                    if (nValue <= 0) {
                        throw new NumberFormatException("Negative Integer found!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid value '"+value+"' found in "+file+"!");
                    reader.close();
                    return new ArrayList<Card>();
                }
                
                cards.add(new Card(nValue));
                value = reader.readLine();
            }
            
            reader.close();
            return cards;
        
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file: "+file);
            return cards;
        
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ArrayList<Card>();
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
                };
            } catch (InputMismatchException e) {
                System.out.println("Number of players must be an integer!");
                n = -1;
            } finally {
                input.nextLine();
            };
        };
        System.out.println("You entered "+n+"\n");

        ArrayList<Card> cards = new ArrayList<Card>();
        System.out.println("Please enter the filename containing the pack of cards:");
        while (cards.isEmpty()) {
            String file = input.nextLine();
            cards = loadPack(file);

        System.out.println("You entered "+file);
        input.close();
        }
    }
}