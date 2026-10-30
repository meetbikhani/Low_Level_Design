package command;

import entities.board;
import entities.cell;
import enums.gameState;

public class revealCell implements command {

    @Override
    public void execute(cell cell, board board, int row, int col) {

        if (cell.checkIsRevealed()) {
            System.out.println("Cell already revealed");
            return;
        }

        if (cell.checkIsBombed()) {

            System.out.println("Oops! You hit a bomb.");

            board.setGameStatus(gameState.LOST);
            board.notifyObserver();

            return;
        }

        if (cell.getAdjBomb() == 0) {

            board.runBFS(row, col);

        } else {

            board.revealSingleCell(row, col);
        }
    }
}