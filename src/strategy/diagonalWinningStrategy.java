package strategy;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Math.abs;

public class diagonalWinningStrategy implements GameWinningStrategy {

    private final Map<Integer, HashMap<Symbol,Integer>> diagonalMap = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if(abs(row-col)==0) {
            if(!diagonalMap.containsKey(0)) {
                diagonalMap.put(0,new HashMap<>());
            }
            HashMap<Symbol,Integer> intMap = diagonalMap.get(0);
            if(!intMap.containsKey(symbol)) {
                intMap.put(symbol,0);
            }
            intMap.put(symbol,intMap.get(symbol)+1);

            if(Objects.equals(intMap.get(symbol), board.getSize())) {
                return true;
            }
        }

        if(row+col==board.getSize()-1) {
            int key = board.getSize()-1;
            if(!diagonalMap.containsKey(key)) {
                diagonalMap.put(key,new HashMap<>());
            }
            HashMap<Symbol,Integer> intMap = diagonalMap.get(key);
            if(!intMap.containsKey(symbol)) {
                intMap.put(symbol,0);
            }
            intMap.put(symbol,intMap.get(symbol)+1);

            if(intMap.get(symbol).equals(board.getSize())) {
                return true;
            }
        }

        return false;
    }
}
