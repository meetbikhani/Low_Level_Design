import java.util.Scanner;

import entities.board;

public class gameDemo {

    public static void main(String[] args) {

        board board = new board();

        Scanner sc = new Scanner(System.in);

        System.out.println("===== MINESWEEPER =====");
        System.out.println("Board size: 10 x 10");
        System.out.println("Enter row and column (0-9) to reveal a cell.");
        System.out.println("Enter -1 to quit.");

        while (board.getGameStatus().toString().equals("INPROGRESS")) {

            System.out.print("\nEnter row: ");
            int row = sc.nextInt();

            if (row == -1) {
                break;
            }

            System.out.print("Enter column: ");
            int col = sc.nextInt();

            board.cellClick(row, col);

            board.displayBoard();
        }

        System.out.println("\nGame ended: " + board.getGameStatus());

        sc.close();
    }
}