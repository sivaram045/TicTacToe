package strategy;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class rowWinningStrategy implements GameWinningStrategy{
    private final HashMap<Integer,HashMap> rowCountMap = new HashMap<>();

    //method to return true if there's a winner
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol playerSymbol = move.getPlayer().getSymbol();

        if(!rowCountMap.containsKey(row)) {
            rowCountMap.put(row, new HashMap<>());
        }

        Map<Symbol,Integer> symbolMap = rowCountMap.get(row);
        if(!symbolMap.containsKey(playerSymbol)) {
            symbolMap.put(playerSymbol,0);
        }

        symbolMap.put(playerSymbol,symbolMap.get(playerSymbol)+1);

        if(symbolMap.get(playerSymbol).equals(board.getSize())) {
            return true;
        }

        return false;
    }
}
