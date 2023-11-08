import java.util.InputMismatchException;
import java.util.Scanner;

public class CardGame {
    
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

        System.out.println("Please enter the filename containing the pack of cards:");
        String file = input.nextLine();
        System.out.println("You entered "+file);
        
        input.close();
    }
}
