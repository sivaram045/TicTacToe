package controller;

import models.Game;
import models.Player;
import models.types.GameState;
import strategy.GameWinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(List<Player> players,
                          List<GameWinningStrategy> winningStrategies,
                          Integer size) throws Exception {
        return Game.getBuilder()
                .setDimension(size)
                .setWinningStrategies(winningStrategies)
                .setPlayers(players)
                .build();
    }
    public void printBoard(Game game) {
        game.printBoard();
    }
    public void makeMove(Game game) throws Exception {
        game.makeMove();
    }
//    public void undo(Game game) {
//        game.undo();
//    }
    public GameState checkState(Game game) {
        return game.getGameState();
    }
    public Player getWinner(Game game) {
        return game.getWinnerPlayer();
    }
}
