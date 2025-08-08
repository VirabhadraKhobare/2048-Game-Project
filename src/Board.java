import java.util.*;

public class Board {
    private int[][] grid;
    private Random random;
    private int score;

    public Board() {
        grid = new int[4][4];
        random = new Random();
        score = 0;
        addRandomTile();
        addRandomTile();
    }

    public int getScore() {
        return score;
    }

    // Get empty cells for spawn
    private List<int[]> getEmptyCells() {
        List<int[]> empty = new ArrayList<>();
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (grid[r][c] == 0) {
                    empty.add(new int[]{r, c});
                }
            }
        }
        return empty;
    }

    // Add a random tile (2 with 90%, 4 with 10%)
    public void addRandomTile() {
        List<int[]> emptyCells = getEmptyCells();
        if (emptyCells.isEmpty()) return;
        int[] cell = emptyCells.get(random.nextInt(emptyCells.size()));
        grid[cell[0]][cell[1]] = (random.nextDouble() < 0.9) ? 2 : 4;
    }

    // Compress row: shift non-zeros left
    private int[] compress(int[] row) {
        int[] newRow = new int[4];
        int index = 0;
        for (int val : row) {
            if (val != 0) newRow[index++] = val;
        }
        return newRow;
    }

    // Merge row: merge once per move
    private int[] merge(int[] row) {
        for (int i = 0; i < 3; i++) {
            if (row[i] != 0 && row[i] == row[i+1]) {
                row[i] *= 2;
                score += row[i];
                row[i+1] = 0;
                i++; // skip next
            }
        }
        return row;
    }

    // Apply move left logic to whole board
    public boolean moveLeft() {
        boolean changed = false;
        for (int r = 0; r < 4; r++) {
            int[] compressed = compress(grid[r]);
            int[] merged = merge(compressed);
            int[] finalRow = compress(merged);
            if (!Arrays.equals(grid[r], finalRow)) {
                changed = true;
                grid[r] = finalRow;
            }
        }
        return changed;
    }

    public boolean moveRight() {
        reverseRows();
        boolean changed = moveLeft();
        reverseRows();
        return changed;
    }

    public boolean moveUp() {
        transpose();
        boolean changed = moveLeft();
        transpose();
        return changed;
    }

    public boolean moveDown() {
        transpose();
        boolean changed = moveRight();
        transpose();
        return changed;
    }

    // Reverse each row
    private void reverseRows() {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 2; c++) {
                int temp = grid[r][c];
                grid[r][c] = grid[r][3 - c];
                grid[r][3 - c] = temp;
            }
        }
    }

    // Transpose grid
    private void transpose() {
        for (int r = 0; r < 4; r++) {
            for (int c = r+1; c < 4; c++) {
                int temp = grid[r][c];
                grid[r][c] = grid[c][r];
                grid[c][r] = temp;
            }
        }
    }

    // Check game state
    public String getGameState() {
        if (maxTile() >= 2048) return "WON";
        if (hasMoves()) return "CONTINUE";
        return "LOSE";
    }

    private int maxTile() {
        int max = 0;
        for (int[] row : grid)
            for (int val : row)
                max = Math.max(max, val);
        return max;
    }

    private boolean hasMoves() {
        if (!getEmptyCells().isEmpty()) return true;
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if ((r < 3 && grid[r][c] == grid[r+1][c]) ||
                    (c < 3 && grid[r][c] == grid[r][c+1])) return true;
            }
        }
        return false;
    }

    // Print board
    public void printBoard() {
        System.out.println("\n+--------------------------------+");
        System.out.println("|           Score: " + String.format("%-10d", score) + "|");
        System.out.println("+--------------------------------+");
        for (int i = 0; i < 4; i++) {
            System.out.print("|");
            for (int j = 0; j < 4; j++) {
                System.out.print(Utils.formatTileNumber(grid[i][j]));
                if (j < 3) System.out.print(" |");
            }
            System.out.println(" |");
            if (i < 3) {
                System.out.println("+----+----+----+----+");
            }
        }
        System.out.println("+--------------------------------+");
    }
}
