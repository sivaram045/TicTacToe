package models;

import models.types.CellState;
import models.types.GameState;
import models.types.PlayerType;
import strategy.GameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> playerMoves;
    private Player winnerPlayer;
    private GameState gameState;
    private Integer nextPlayerIndex;
    List<GameWinningStrategy> winningStrategies;

    //constructor
    private Game(int dimension, List<Player> players, List<GameWinningStrategy> winningStrategies) {//only builder should access the constructor, so private
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.board = new Board(dimension);
        this.playerMoves = new ArrayList<>();
        this.nextPlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
    }

    //return Builder from here
    public static Builder getBuilder() {
        return new Builder();
    }


    //getters
    public Board getBoard() {
        return board;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public List<Move> getPlayerMoves() {
        return playerMoves;
    }
    public Player getWinnerPlayer() {
        return winnerPlayer;
    }
    public GameState getGameState() {
        return gameState;
    }
    public Integer getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void printBoard() {
        board.printBoard();
    }

    public void makeMove() throws Exception {
        Player currentPlayer = players.get(nextPlayerIndex);
        System.out.println("Current Player's move: " + currentPlayer.getName());

        Move move = currentPlayer.makeMove();

        if(!validateMove(move)) { //validating the move
            throw new Exception("Invalid Move");
        }

        //if validations pass, take the cell values(row,col)
        int row = move.getCell().getRow();                                                      //check Integer?
        int col = move.getCell().getCol();

        //update the cell
        Cell cellToBeUpdated = board.getBoard().get(row).get(col);
        cellToBeUpdated.setCellState(CellState.FILLED);
        cellToBeUpdated.setPlayer(move.getPlayer());

        //update moves list with this new move
        Move moveToBeUpdated = new Move(cellToBeUpdated, move.getPlayer());
        playerMoves.add(moveToBeUpdated);

        //increment the index
        nextPlayerIndex = (nextPlayerIndex + 1)% players.size();

        //updating game state and winner player if game end
        if(checkWinner(move, board)) {
            gameState = GameState.END;
            winnerPlayer = move.getPlayer();
        }else if(playerMoves.size() == board.getSize()* board.getSize()) {
            gameState = GameState.DRAW;
        }

    }

    //check winner method iterates all the winning strategies
    public boolean checkWinner(Move move, Board board) {
        for(GameWinningStrategy st:winningStrategies) { //iterating the list of winning strategies
            if(st.checkWinner(board,move)) { //if any of the strategy is true, return true
                return true;
            }
        }
        return false; //else return false
    }

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row >= board.getSize()) { //are you crossing row boundary?
            return false;
        }else if(col >= board.getBoard().get(0).size()) { //are you crossing column boundary?
            return false;
        }else { //is the selected cell empty?
            return board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
        }
    }

    //builder class
    public static class Builder {
        private List<Player> players;
        private List<GameWinningStrategy> winningStrategies;
        private int dimension;

        private Builder() {
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
            this.dimension = 0;
        }

        //setters

        public Builder setPlayers(List<Player> players) { //doubt
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<GameWinningStrategy> winningStrategies) { //doubt
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension) { //doubt
            this.dimension = dimension;
            return this;
        }

        //build method with validations
        public Game build() throws Exception { //validations modularized
            //validation
            zeroPlayerValidation();
            allBotsValidation();
            playerCountValidationForBoardDimension();
            return new Game(dimension, players, winningStrategies);
        }

        private void playerCountValidationForBoardDimension() throws Exception {
            if(players.size()!=dimension-1) {
                throw new Exception("no. of players not sufficient :O ");
            }
        }

        private void allBotsValidation() throws Exception {
            int botCount = 0;
            for(int i=0; i< players.size(); i++) {
                if(players.get(i).getType().equals(PlayerType.BOT)) {
                    botCount++;
                }
            }
            if(botCount== players.size()) {
                throw new Exception("hey no human here :| ");
            }
        }

        private void zeroPlayerValidation() throws Exception {
            if(players.isEmpty()) {
                throw new Exception("no players found :( ");
            }
        }
    }
}
