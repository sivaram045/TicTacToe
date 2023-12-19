package strategy;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class columnWinningStrategy implements GameWinningStrategy {
    private final Map<Integer, HashMap<Symbol, Integer>> colCountMap = new HashMap<>();

    //method to return true if there's a winner in column strategy
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol playerSymbol = move.getPlayer().getSymbol();

        if(!colCountMap.containsKey(col)) { //adding new column to MainMap
            colCountMap.put(col, new HashMap<>());
        }

        Map<Symbol , Integer> symbolMap = colCountMap.get(col);//adding player symbol to subMap with 0 count(intializing)
        if(!symbolMap.containsKey(playerSymbol)) {
            symbolMap.put(playerSymbol,0); //initially there's no symbol in column
        }

        symbolMap.put(playerSymbol, symbolMap.get(playerSymbol)+1); //increment if symbol placed in this column

        //check if all columns fill with same symbol
        if(symbolMap.get(playerSymbol).equals(board.getSize())) {
            return true;
        }
        return false;
    }
}
