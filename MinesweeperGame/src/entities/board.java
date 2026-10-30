package entities;

import enums.gameState;
import observer.gameObserver;
import observer.notification;
import command.command;
import command.revealCell;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class board {

    private static final int SIZE = 10;

    private gameState gameStatus;
    private cell[][] grid = new cell[SIZE][SIZE];

    private Random random = new Random();
    private command command;
    private gameObserver observer;

    private int totalSafeCells;
    private int revealedSafeCells;

    public board() {
        this.gameStatus = gameState.INPROGRESS;

        initializeBoard();
        calculateAdjacentBombs();

        this.command = new revealCell();
        this.observer = new notification();
    }

    public gameState getGameStatus() {
        return this.gameStatus;
    }

    public void setGameStatus(gameState status) {
        this.gameStatus = status;
    }

    private void initializeBoard() {
        totalSafeCells = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

                boolean isBomb = random.nextDouble() < 0.20;

                grid[i][j] = new cell(isBomb);

                if (!isBomb) {
                    totalSafeCells++;
                }
            }
        }
    }

    private void calculateAdjacentBombs() {

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

                if (grid[i][j].checkIsBombed()) {
                    continue;
                }

                for (int k = 0; k < 8; k++) {

                    int newRow = i + dx[k];
                    int newCol = j + dy[k];

                    if (isValid(newRow, newCol)
                            && grid[newRow][newCol].checkIsBombed()) {

                        grid[i][j].increaseAdjBombCount();
                    }
                }
            }
        }
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < SIZE
                && col >= 0 && col < SIZE;
    }

    public void cellClick(int row, int col) {

        if (gameStatus != gameState.INPROGRESS) {
            System.out.println("Game is already over");
            return;
        }

        if (!isValid(row, col)) {
            System.out.println("Wrong cell selected");
            return;
        }

        command.execute(grid[row][col], this, row, col);
    }

    public void runBFS(int row, int col) {

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{row, col});

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];

            cell currentCell = grid[r][c];

            if (currentCell.checkIsRevealed()) {
                continue;
            }

            if (currentCell.checkIsBombed()) {
                continue;
            }

            currentCell.setRevealed(true);
            revealedSafeCells++;

            // Numbered cells are revealed but not expanded.
            if (currentCell.getAdjBomb() != 0) {
                continue;
            }

            // Expand neighboring cells only for empty cells.
            for (int k = 0; k < 8; k++) {

                int newRow = r + dx[k];
                int newCol = c + dy[k];

                if (isValid(newRow, newCol)
                        && !grid[newRow][newCol].checkIsRevealed()
                        && !grid[newRow][newCol].checkIsBombed()) {

                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        checkWin();
        notifyObserver();
    }

    public void displayBoard() {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {

            cell current = grid[i][j];

            if (!current.checkIsRevealed()) {
                System.out.print("■ ");
            } else if (current.checkIsBombed()) {
                System.out.print("B ");
            } else {
                System.out.print(current.getAdjBomb() + " ");
            }
        }
        System.out.println();
    }
}

    public void revealSingleCell(int row, int col) {

        cell selectedCell = grid[row][col];

        if (selectedCell.checkIsRevealed()) {
            return;
        }

        selectedCell.setRevealed(true);
        revealedSafeCells++;

        checkWin();
        notifyObserver();
    }

    private void checkWin() {

        if (revealedSafeCells == totalSafeCells) {
            gameStatus = gameState.WON;
            System.out.println("Congratulations! You won!");
            notifyObserver();
        }
    }

    public void notifyObserver() {
        observer.inform(this);
    }
}