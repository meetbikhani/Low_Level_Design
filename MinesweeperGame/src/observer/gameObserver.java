package observer;

import entities.board;

public interface gameObserver{
    public void inform(board board);
}