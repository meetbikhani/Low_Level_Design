package entities;

public class cell{
    private boolean isBomb;
    private boolean isRevealed;
    private int adjBomb;

    public cell(boolean isBomb){
        this.isBomb=isBomb;
        this.isRevealed=false;
        this.adjBomb=0;
    }

    public boolean checkIsBombed(){
        return isBomb;
    }

    public boolean checkIsRevealed(){
        return isRevealed;
    }

    public int getAdjBomb(){
        return adjBomb;
    }

    public void increaseAdjBombCount(){
        adjBomb++;
    }

     public void setRevealed(boolean revealed) {
        this.isRevealed = revealed;
    }
}