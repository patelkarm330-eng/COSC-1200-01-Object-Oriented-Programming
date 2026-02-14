 /*
 * Name:Karm Patel
 * Program name: PizzaSlicesCalculator
 * Date:8 February 2026
 * Description: This program calculates the number of slices a pizza by their diameter.
 */

import java.util.Scanner;

public class PizzaSlicesCalculator {

    public static void main(String[] args) {

        // Create a Scanner object to read input from the keyboard
        Scanner input = new Scanner(System.in);

        // Ask the user to enter the pizza diameter
        System.out.print("Please enter the diameter of the pizza: ");

        // Read the diameter as a decimal number
        double diameter = input.nextDouble();

        // Validate input
        if (diameter < 6 || diameter > 24)
        { System.out.println("Pizza must have a diameter in the range of 6\" to 24\"  Please try again.");
            return;
        }

        // Variable to store the number of slices
        int slices;

        // Determine number of slices
        if (diameter < 8) {
            slices = 4;
        }
        else if (diameter < 12) {
            slices = 6;
        }
        else if (diameter < 14) {
            slices = 8;
        }
        else if (diameter < 16) {
            slices = 10;
        }
        else if (diameter < 20) {
            slices = 12;
        }
        else {
            slices = 16;
        }

        // Calculate area
        double radius = diameter / 2.0;
        double totalArea = Math.PI * Math.pow(radius, 2);
        double sliceArea = totalArea / slices;

        // Output results
        System.out.printf("A %.2f\" pizza of %d slices.%n", diameter, slices);
        System.out.printf("Each slice will have an area of %.2f square inches.%n", sliceArea);

        input.close();
    }
}

