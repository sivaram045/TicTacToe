package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> board;
    private Integer size;


    public Board(int dimension) {
        board = new ArrayList<>();
        for(int i=0; i<dimension; i++) {
            board.add(new ArrayList<>());
            for(int j=0; j<dimension; j++) {
                board.get(i).add(new Cell(i,j));
            }
        }
    }




    //getters
    public List<List<Cell>> getBoard() {
        return board;
    }
    public Integer getSize() {
        return this.board.size();
    }

    public void printBoard() {
        for(List<Cell> i : board) {
            for(Cell j : i) {
                j.display();
            }
            System.out.println();
        }
    }
}
