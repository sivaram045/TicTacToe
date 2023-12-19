package models;

import models.types.CellState;

public class Cell {
    private Integer row;
    private Integer col;
    private CellState cellState;
    private Player player;

    //constructor
    public Cell(int i, int j) {
        this.row = i;
        this.col = j;
        this.cellState = CellState.EMPTY;
    }


    //getters
    public Integer getRow() {
        return row;
    }
    public Integer getCol() {
        return col;
    }
    public CellState getCellState() {
        return cellState;
    }
    public Player getPlayer() {
        return player;
    }


    //setters
    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    //functions
    public void display() {
        if(getCellState().equals(CellState.EMPTY)) {
            System.out.print("|-|");
        }else {
            System.out.print("|"+this.getPlayer().getSymbol().getPlayerSymbol()+"|");
        }
    }


}
