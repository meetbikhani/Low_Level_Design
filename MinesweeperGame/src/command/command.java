package command;

import entities.board;
import entities.cell;

public interface command{
    public void execute(cell cell,board board, int row, int col);
}