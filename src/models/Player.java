package models;

import models.types.PlayerType;

import java.util.Scanner;

public class Player {
    private Scanner scanner;
    private String name;
    private Integer id;
    private Symbol symbol;
    private PlayerType type;

    public Player(String name, int i, Symbol s, PlayerType playerType, Scanner scanner) {
        this.name = name;
        this.id = i;
        this.symbol = s;
        this.type = playerType;
        this.scanner = scanner;
    }


    //getters
    public String getName() {
        return name;
    }
    public Integer getId() {
        return id;
    }
    public Symbol getSymbol() {
        return symbol;
    }
    public PlayerType getType() {
        return type;
    }

    public Move makeMove() {
        System.out.println("which row you want to move?(0-indexing)");
        int row = scanner.nextInt();
        System.out.println("which column you want to move?(0-indexing)");
        int col = scanner.nextInt();

        return new Move(new Cell(row, col), this);
    }
}
