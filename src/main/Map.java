package main;

import utils.Array2Dprinter;
import utils.IPrintable;

public class Map implements IPrintable, ICommand {

    @Override
    public String getPrintableString() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrintableString'");
    }

    @Override
    public boolean isGrayedOut() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isGrayedOut'");
    }

    @Override
    public void execute(String[] args, Game game) {

    }

    /*public void print() {
        String result = Array2Dprinter.print2DArray(map, playerRow, playerCol);
        System.out.println(result);
    }

    public int getPlayerRow() {
        return playerRow;
    }

    public int getPlayerCol() {
        return playerCol;
    }*/

}
