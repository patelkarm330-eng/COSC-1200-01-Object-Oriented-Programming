/*
 * Name: Karm Patel
 * Program name: ProjectileGame
 * Date: 4 March 2026
 * Description: Simple projectile motion game where two players take turns shooting.
 */

import java.util.Scanner;
import java.util.Random;

public class ProjectileGame {

    // Constant gravity value used in projectile motion formula
    private static final double GRAVITY = 9.81;

    // Small time step used to simulate projectile flight
    private static final double TIME_STEP = 0.01;

    // Scanner for user input
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        // Get player names at the start of the program
        System.out.print("Enter Player 1 name: ");
        String playerOne = input.nextLine();

        System.out.print("Enter Player 2 name: ");
        String playerTwo = input.nextLine();

        boolean playAgain = true;

        // Main loop
        while (playAgain) {

            // Run one full game and return the winning player number
            int winner = runGame(playerOne, playerTwo);
            System.out.println("Winner: Player " + winner);

            // Ask user if they want to play again
            System.out.print("Play again? (yes/no): ");
            String answer = input.nextLine().trim().toLowerCase();

            if (answer.equals("no")) {
                playAgain = false;
            }
        }

        System.out.println("Thanks for playing!");
    }

    // Gets a valid power value from the user.
    private static double getPower() {
        double power;

        while (true) {
            System.out.print("Enter power (1–100): ");

            // Validate numeric input
            if (input.hasNextDouble()) {
                power = input.nextDouble();
                input.nextLine();

                if (power >= 1 && power <= 100) {
                    return power;
                }
            } else {
                input.nextLine();
            }

            System.out.println("Invalid input. Retry.");
        }
    }

    // Gets a valid angle value from the user.
    private static double getAngle() {
        double angle;

        while (true) {
            System.out.print("Enter angle (10–80): ");

            // Validate numeric input
            if (input.hasNextDouble()) {
                angle = input.nextDouble();
                input.nextLine();

                if (angle >= 10 && angle <= 80) {
                    return angle;
                }
            } else {
                input.nextLine();
            }

            System.out.println("Invalid input. Retry.");
        }
    }

    // Calculates where a projectile lands
    private static double getShot(double startX) {

        double velocity = getPower();
        double angleDegrees = getAngle();
        double angleRadians = Math.toRadians(angleDegrees);

        double time = 0;
        double height;

        // Simulate projectile flight until it hits the ground
        do {
            time += TIME_STEP;

            // Height formula for projectile motion
            height = (velocity * Math.sin(angleRadians)) * time
                    - 0.5 * GRAVITY * time * time;

        } while (height > 0);

        // Horizontal landing position
        double landingX = (velocity * Math.cos(angleRadians)) * time + startX;

        // Keep landing inside the map (0 to 120)
        if (landingX < 0) landingX = 0;
        if (landingX > 120) landingX = 120;

        return landingX;
    }

    // Runs one full game round
    private static int runGame(String playerOne, String playerTwo) {

        Random random = new Random();

        // Random starting positions for both players (0–60 to keep them closer)
        double playerOneX = random.nextInt(61);
        double playerTwoX = random.nextInt(61);

        System.out.println("\nNew Game");
        System.out.println(playerOne + " starts at x = " + playerOneX);
        System.out.println(playerTwo + " starts at x = " + playerTwoX);

        // Game loop: players alternate shots
        while (true) {

            // Player 1 turn
            System.out.println("\n" + playerOne + "s turn:");
            double shotOne = getShot(playerOneX);
            System.out.println("Shot landed at x = " + shotOne);

            // Check if Player 1 hit Player 2
            if (Math.abs(shotOne - playerTwoX) < 15) {
                return 1;
            }

            // Player 2 turn
            System.out.println("\n" + playerTwo + "s turn:");
            double shotTwo = getShot(playerTwoX);
            System.out.println("Shot landed at x = " + shotTwo);

            // Check if Player 2 hit Player 1
            if (Math.abs(shotTwo - playerOneX) < 15) {
                return 2;
            }
        }
    }
}