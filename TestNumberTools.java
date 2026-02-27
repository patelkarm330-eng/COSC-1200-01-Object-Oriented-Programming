 /*
  * Name:Karm Patel
  * Program name: TestNumberTools
  * Date:26 February 2026
  * Description: This program tests the functions inside NumberTools.
  */

import java.util.Scanner;

    public class TestNumberTools {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);

            while (true) {
                System.out.print("Please enter an integer to test: ");

                // If the user enters something that is NOT an integer and exit
                if (!input.hasNextInt()) {
                    System.out.println("Non-integer entered.");
                    break;
                }

                int value = input.nextInt();

                // Test each function and print the result
                System.out.println("Is " + value + " an even number? " +
                        (NumberTools.isEven(value) ? "Yes." : "No."));

                System.out.println("Is " + value + " a positive number? " +
                        (NumberTools.isPositive(value) ? "Yes." : "No."));

                System.out.println("Is " + value + " a power of two? " +
                        (NumberTools.isPowerOfTwo(value) ? "Yes." : "No."));

                System.out.println("Is " + value + " a square number? " +
                        (NumberTools.isSquare(value) ? "Yes." : "No."));

                System.out.println();
            }

            input.close();
        }
    }



