package strategy;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.ArrayList;
import java.util.List;

public class leftDiagonalWinningStrategy implements GameWinningStrategy {
    private final List<List<Symbol>> diagonalCount = new ArrayList<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        if(diagonalCount.get(row)==null) {
            diagonalCount.add(new ArrayList<>());
            ArrayList<Symbol> rowList = (ArrayList<Symbol>) diagonalCount.get(row);
            rowList.set(col,move.getPlayer().getSymbol());
        }else {
            diagonalCount.get(row).set(col,move.getPlayer().getSymbol());
        }
        //left diagonal
         if(diagonalCount.get(0).get(0) != move.getPlayer().getSymbol()
                    || diagonalCount.get(board.getSize()-1).get(board.getSize()-1) != move.getPlayer().getSymbol() ) {
            return false;
         }
         //left diagonal
         else if(diagonalCount.get(0).get(0) == move.getPlayer().getSymbol()
                 || diagonalCount.get(board.getSize()-1).get(board.getSize()-1) == move.getPlayer().getSymbol()) {
             int count = 0;
             int tempRow = board.getSize()-1;
             int tempCol = 0;
             while (tempRow>=0 && tempCol< board.getSize()) {
                 if(diagonalCount.get(tempRow).get(tempCol) == move.getPlayer().getSymbol()) {
                     count++;
                     tempRow--;
                     tempCol++;
                 }
             }
             if(count == board.getSize()-1) {
                 return true;
             }
         }
        return false;
    }
}
