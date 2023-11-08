import java.util.InputMismatchException;
import java.util.Scanner;

public class CardGame {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the number of players:");
        int n = -1;
        try {
            n = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Number of players must be an integer!");
        };
        System.out.println("You entered "+n);
        input.close();
    }
}
