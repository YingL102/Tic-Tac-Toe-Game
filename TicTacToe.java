import java.util.Scanner;

public class TicTacToe {
    static char[][] board = new char[3][3];

    public static void main(String[] args) {
        // Initialize board with spaces for better display
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        
        boolean win = false;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("New Game?");
            String newGameChoice = scanner.nextLine();
            if (newGameChoice.equals("no")) {
                break;
            }
            System.out.println("Starting game...");
            System.out.println("Here is the board for reference");
            System.out.println("(0,0) | (0,1) | (0,2)");
            System.out.println("---------------------");
            System.out.println("(1,0) | (1,1) | (1,2)");
            System.out.println("---------------------");
            System.out.println("(2,0) | (2,1) | (2,2)");
            boolean xTurn = true;
            while (win != true) {
                printBoard();
                System.out.println("Input your move (e.g. '0,0'): ");
                String move = scanner.nextLine();
                String[] parts = move.split(",");
                if (parts.length == 2) {
                    try {
                        int row = Integer.parseInt(parts[0].trim());
                        int col = Integer.parseInt(parts[1].trim());
                        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                            board[row][col] = xTurn ? 'X' : 'O';
                            if (checkWin(xTurn ? 'X' : 'O')) {
                                printBoard();
                                System.out.println((xTurn ? "X" : "O") + " wins!");
                                win = true;
                                continue;
                            } else if (isBoardFull()) {
                                printBoard();
                                System.out.println("It's a draw!");
                                win = true;
                                continue;
                            }
                            xTurn = !xTurn;
                        } else {
                            System.out.println("Invalid move. Try again.");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input format. Try again.");
                        continue;
                    }
                } else {
                    System.out.println("Invalid input format. Try again.");
                    continue;
                }
            }
        }
        System.out.println("Thanks for playing!");
        scanner.close();
    }

    public static void printBoard() {
        System.out.println("Current board:");
        for (int i = 0; i < 3; i++) {
            System.out.print(" ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("-----------");
        }
    }

    // Check if the given player has won
    public static boolean checkWin(char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }
        return false;
    }

    // Check if the board is full
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
