import java.util.Random;

public class Main {
    public static boolean solveSudoku(int[][] board) {
        int[] emptyCell = findEmptyCell(board);
        if (emptyCell == null) {
            return true;
        }

        int row = emptyCell[0];
        int col = emptyCell[1];

        for (int num = 1; num <= 9; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;

                if (solveSudoku(board)) {
                    return true;
                }

                board[row][col] = 0;
            }
        }

        return false;
    }

    public static boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int startRow = 3 * (row / 3);
        int startCol = 3 * (col / 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int[] findEmptyCell(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
                if (j == 2 || j == 5) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if (i == 2 || i == 5) {
                System.out.println("---------------------");
            }
        }
    }

    public static class RandomSudokuGenerator {
        public static int[][] generateRandomSudoku() {
            int[][] board = new int[9][9];
            solveSudoku(board);

            Random rand = new Random();
            int numbersToRemove = rand.nextInt(16) + 30; // Remove 30 to 45 numbers
            int count = 0;

            while (count < numbersToRemove) {
                int row = rand.nextInt(9);
                int col = rand.nextInt(9);
                if (board[row][col] != 0) {
                    board[row][col] = 0;
                    count++;
                }
            }

            return board;
        }

        public static boolean solveSudoku(int[][] board) {
            int[] emptyCell = findEmptyCell(board);
            if (emptyCell == null) {
                return true;
            }

            int row = emptyCell[0];
            int col = emptyCell[1];

            for (int num = 1; num <= 9; num++) {
                if (isValid(board, row, col, num)) {
                    board[row][col] = num;

                    if (solveSudoku(board)) {
                        return true;
                    }

                    board[row][col] = 0;
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        int[][] randomSudoku = RandomSudokuGenerator.generateRandomSudoku();
        System.out.println("Random Sudoku puzzle:");
        Main.printBoard(randomSudoku);

        if (Main.solveSudoku(randomSudoku)) {
            System.out.println("\nSolved Random Sudoku:");
            Main.printBoard(randomSudoku);
        } else {
            System.out.println("No solution exists for the random Sudoku puzzle.");
        }
    }
}
