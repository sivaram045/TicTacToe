package models;

import controller.GameController;
import models.types.GameState;
import models.types.PlayerType;
import strategy.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String [] a) throws Exception {
        GameController gameController = new GameController();
        Scanner sc = new Scanner(System.in);
        List<Player> players = new ArrayList<>();
        players.add(new Player("Siva",1,new Symbol('X'), PlayerType.HUMAN, sc));
        players.add(new Player("ram", 2, new Symbol('O'), PlayerType.HUMAN, sc));

        List<GameWinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new rowWinningStrategy());
        winningStrategies.add(new columnWinningStrategy());
        //winningStrategies.add(new leftDiagonalWinningStrategy());
        //winningStrategies.add(new rightDiagonalWinningStrategy());
        winningStrategies.add(new diagonalWinningStrategy());

        Game game = gameController.startGame(players,winningStrategies,3);

        while (gameController.checkState(game).equals(GameState.IN_PROGRESS)){

            gameController.printBoard(game);
            gameController.makeMove(game);
        }

        GameState state = gameController.checkState(game);
        //show game state
        if(gameController.checkState(game).equals(GameState.DRAW)) {
            game.printBoard();
            System.out.println("Game is DRAW..!");
        }

        if(gameController.checkState(game).equals(GameState.END)) {
            game.printBoard();
            System.out.println("Game ended and "+ gameController.getWinner(game).getName()+" WON the game..!");
        }

    }
}
