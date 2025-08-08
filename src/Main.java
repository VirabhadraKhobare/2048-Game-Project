import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean playAgain = true;
        
        while (playAgain) {
            playGame(sc);
            playAgain = Utils.askPlayAgain();
        }
        
        System.out.println("Thanks for playing 2048!");
        sc.close();
        Utils.cleanup();
    }
    
    private static void playGame(Scanner sc) {
        Board board = new Board();
        
        Utils.printInstructions();
        board.printBoard();

        while (true) {
            String input;
            do {
                input = Utils.readGameInput("Enter move (W/A/S/D) or Q to quit: ");
                if (!Utils.isValidGameInput(input)) {
                    System.out.println("Invalid input! Use W/A/S/D to move or Q to quit.");
                }
            } while (!Utils.isValidGameInput(input));
            
            boolean moved = false;

            switch (input) {
                case "W": moved = board.moveUp(); break;
                case "S": moved = board.moveDown(); break;
                case "A": moved = board.moveLeft(); break;
                case "D": moved = board.moveRight(); break;
                case "Q": 
                    System.out.println("Game quit!");
                    return;
            }

            if (moved) {
                board.addRandomTile();
                board.printBoard();
                String state = board.getGameState();
                if (state.equals("WON")) {
                    Utils.printGameOver(true, board.getScore());
                    break;
                } else if (state.equals("LOSE")) {
                    Utils.printGameOver(false, board.getScore());
                    break;
                }
            } else {
                System.out.println("No tiles moved. Try a different direction.");
            }
        }
    }
}
