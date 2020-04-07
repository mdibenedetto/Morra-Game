package bk;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        System.out.println("MorraGameApp");
        getRealUserFingers();
    }

    private static int getRealUserFingers() {
        int userFingers = 0;
        Scanner sc = new Scanner(System.in);
        do {
            // ask user to input how the number of fingers
            println("Enter the number of fingers you want to show");

            if (sc.hasNextInt()) {
                userFingers = sc.nextInt();

                //check if user enter the correct input(1 to 11)
                if (userFingers > 0 && userFingers < 11) {
                    println("You have enter " + userFingers + " fingers.");
                } else {
                    println("Please enter number of fingers between 1 and 10!");
                }
            } else {
                println(
                    "Please enter a \"valid number\"  of fingers between 1 and 10!"
                );
                sc.next();
            }
        } while (userFingers <= 0 || userFingers > 10);

        return userFingers;
    }

    private static void println(String msg) {
        System.out.println(msg);
    }
}
