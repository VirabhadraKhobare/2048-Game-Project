import java.util.Scanner;

/**
 * Utility class for the 2048 game
 * Contains helper methods for input validation and game utilities
 */
public class Utils {
    
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Reads a single character input from the user and converts to uppercase
     * @param message The prompt message to display
     * @return The uppercase character input, or "Q" if no input available
     */
    public static String readGameInput(String message) {
        try {
            System.out.print(message);
            if (!scanner.hasNext()) {
                return "Q"; // Default to quit if no input available
            }
            return scanner.next().toUpperCase();
        } catch (Exception e) {
            return "Q"; // Default to quit on any input error
        }
    }
    
    /**
     * Validates if the input is a valid game command
     * @param input The input to validate
     * @return true if input is valid (W, A, S, D, Q), false otherwise
     */
    public static boolean isValidGameInput(String input) {
        return input.equals("W") || input.equals("A") || input.equals("S") || 
               input.equals("D") || input.equals("Q");
    }
    
    /**
     * Prints the game instructions
     */
    public static void printInstructions() {
        System.out.println("=================================");
        System.out.println("         2048 GAME");
        System.out.println("=================================");
        System.out.println("Controls:");
        System.out.println("  W - Move Up");
        System.out.println("  S - Move Down");
        System.out.println("  A - Move Left");
        System.out.println("  D - Move Right");
        System.out.println("  Q - Quit Game");
        System.out.println("=================================");
        System.out.println("Goal: Combine tiles to reach 2048!");
        System.out.println("=================================\n");
    }
    
    /**
     * Prints a formatted game over message
     * @param won true if player won, false if lost
     * @param score final score
     */
    public static void printGameOver(boolean won, int score) {
        System.out.println("\n=================================");
        if (won) {
            System.out.println("    CONGRATULATIONS!");
            System.out.println("    You reached 2048!");
        } else {
            System.out.println("      GAME OVER!");
            System.out.println("    No moves left!");
        }
        System.out.println("    Final Score: " + score);
        System.out.println("=================================");
    }
    
    /**
     * Formats a number with appropriate spacing for the board display
     * @param number The number to format
     * @return Formatted string with consistent width
     */
    public static String formatTileNumber(int number) {
        if (number == 0) {
            return "   .";
        } else {
            return String.format("%4d", number);
        }
    }
    
    /**
     * Asks user if they want to play again
     * @return true if user wants to play again, false otherwise
     */
    public static boolean askPlayAgain() {
        try {
            while (true) {
                System.out.print("\nWould you like to play again? (y/n): ");
                if (!scanner.hasNext()) {
                    return false; // No more input available
                }
                String input = scanner.next().toLowerCase();
                if (input.equals("y") || input.equals("yes")) {
                    return true;
                } else if (input.equals("n") || input.equals("no")) {
                    return false;
                } else {
                    System.out.println("Please enter 'y' for yes or 'n' for no.");
                }
            }
        } catch (Exception e) {
            return false; // Exit gracefully if input stream is closed
        }
    }
    
    /**
     * Closes the scanner when the game ends
     */
    public static void cleanup() {
        scanner.close();
    }
}
