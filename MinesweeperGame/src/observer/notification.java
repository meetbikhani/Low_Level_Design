package observer;

import entities.board;

public class notification implements gameObserver{
    @Override 
    public void inform(board board){
        System.out.println(board.getGameStatus());
    }
}