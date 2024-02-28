package simplegame;
import java.util.Scanner;

public class SimpleGame {

    /**
     * Write a method to convert the given seconds to hours:minutes:seconds.
     * @param seconds to convert
     * @return string for the converted seconds in the format: 23:59:59
     * 
     * Example(s): 
     * - If input seconds is 1432, print and return output in the format: 0:23:52
     * - If input seconds is 0, print and return output in the format: 0:0:0
     * - If input seconds is not valid (negative), print and return: -1:-1:-1.  
     *   So if input seconds is -2, print and return: -1:-1:-1 
     *   If input seconds is -3214, likewise print and return: -1:-1:-1
     */
    public String convertTime(int seconds){
        if (seconds < 0) {
            return "-1:-1:-1";
        }

        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int remainingSeconds = seconds % 60;

        return hours + ":" + minutes + ":" + remainingSeconds;
    }

    /**
     * Write a method that adds all the digits in the given non-negative integer.
     * @param integer to add digits
     * @return integer in which all the digits in the given non-negative integer are added.
     * 
     * Example(s): 
     * - If input is 565, print and return 16.
     * - If input is 7, print and return 7.
     * - If input is 0, print and return 0.
     */
    public int digitsSum(int input){
        if (input < 0) {
            return -1;
        }

        int sum = 0;
        while (input != 0) {
            sum += input % 10;
            input /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        SimpleGame game = new SimpleGame(); // Create an instance of the SimpleGame class.

        Scanner sc = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1. Convert time");
        System.out.println("2. Calculate the sum of digits in an integer");
        int option = sc.nextInt();

        if (option == 1) {
            System.out.print("Enter the number of seconds: ");
            int seconds = sc.nextInt();
            String time = game.convertTime(seconds);
            System.out.println("Converted time: " + time);
        } else if (option == 2) {
            System.out.print("Enter an integer: ");
            int input = sc.nextInt();
            int sum = game.digitsSum(input);
            System.out.println("Sum of digits: " + sum);
        } else {
            System.out.println("Invalid option. Please choose 1 or 2.");
        }

        sc.close();
    }    
}
