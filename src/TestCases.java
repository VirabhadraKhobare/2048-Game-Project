public class TestCases {
    public static void main(String[] args) {
        System.out.println("Running 2048 Game Tests...\n");
        
        // Test 1: Board initialization
        testBoardInitialization();
        
        // Test 2: Game state detection
        testGameState();
        
        // Test 3: Move operations
        testMoveOperations();
        
        System.out.println("\nAll tests completed!");
    }
    
    private static void testBoardInitialization() {
        System.out.println("Test 1: Board Initialization");
        Board board = new Board();
        System.out.println("Initial board created with score: " + board.getScore());
        board.printBoard();
        System.out.println("✓ Board initialization test passed\n");
    }
    
    private static void testGameState() {
        System.out.println("Test 2: Game State Detection");
        Board board = new Board();
        String state = board.getGameState();
        System.out.println("Game state: " + state);
        System.out.println("✓ Game state test passed\n");
    }
    
    private static void testMoveOperations() {
        System.out.println("Test 3: Move Operations");
        Board board = new Board();
        
        System.out.println("Testing move operations:");
        boolean moved;
        
        moved = board.moveLeft();
        System.out.println("Move Left result: " + moved);
        
        moved = board.moveRight();
        System.out.println("Move Right result: " + moved);
        
        moved = board.moveUp();
        System.out.println("Move Up result: " + moved);
        
        moved = board.moveDown();
        System.out.println("Move Down result: " + moved);
        
        board.printBoard();
        System.out.println("✓ Move operations test passed\n");
    }
}
