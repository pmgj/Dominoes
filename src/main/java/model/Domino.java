package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.exceptions.EmptyStockException;
import model.exceptions.NoUseStockException;
import model.players.Player;
import model.rules.DominoesRules;

public class Domino {
    private final LinkedList<Tile> board;
    private final DominoesRules rules;
    private final List<Player> players;
    private final List<Tile> stock;
    private GameResult result;
    private int turn;

    public Domino(DominoesRules _rules, List<Player> _players) {
        if (!_rules.hasCorrectNumberOfPlayers(_players.size())) {
            throw new IllegalArgumentException(String.format("Incorrect number of players: %d", _players.size()));
        }
        _players.stream().forEach(p -> p.resetScore());
        this.board = new LinkedList<>();
        this.rules = _rules;
        this.turn = 0;
        this.players = _players;
        this.stock = new ArrayList<>();
        this.result = new GameResult(RoundResult.DRAW, GameResult.NONE);
    }

    private boolean isRepeatedTiles(List<List<Tile>> _playersTiles, List<Tile> _stock) {
        for (int i = 0; i < _playersTiles.size() - 1; i++) {
            List<Tile> t1 = _playersTiles.get(i);
            for (int j = i + 1; j < _playersTiles.size(); j++) {
                List<Tile> t2 = _playersTiles.get(j);
                if (t1.stream().filter(t2::contains).count() != 0) {
                    return true;
                }
            }
        }
        for (int j = 0; j < _playersTiles.size(); j++) {
            List<Tile> t2 = _playersTiles.get(j);
            if (_stock.stream().filter(t2::contains).count() != 0) {
                return true;
            }
        }
        return false;
    }

    public void resetRound(List<List<Tile>> _playersTiles, List<Tile> _stock) {
        if (this.players == null) {
            throw new IllegalArgumentException("There are no players stored.");
        }
        if (_playersTiles == null) {
            throw new IllegalArgumentException("The players' tiles must not be null.");
        }
        if (this.result.roundWinner() == RoundResult.NONE) {
            throw new IllegalArgumentException("You are not allowed to distribute tiles right now.");
        }
        if (_playersTiles.size() != this.players.size()) {
            throw new IllegalArgumentException("The number of players is different from previoulsy informed.");
        }
        if (this.isRepeatedTiles(_playersTiles, _stock)) {
            throw new IllegalArgumentException("There are repeated tiles.");
        }
        if (_playersTiles.stream().anyMatch(p -> p.size() != this.rules.numberOfTilesPerPlayer())) {
            throw new IllegalArgumentException("The number of tiles of some players is incorrect.");
        }
        for (int i = 0; i < _playersTiles.size(); i++) {
            List<Tile> tiles = _playersTiles.get(i);
            Player player = this.players.get(i);
            player.resetTiles();
            tiles.forEach(t -> player.addTile(t));
        }
        this.stock.clear();
        if(_stock != null) {
            this.stock.addAll(_stock);
        }
        this.board.clear();
        if (this.result.roundWinner() == RoundResult.DRAW) {
            this.turn = this.rules.firstToPlay(this.players);
        }
    }

    public void resetRound() {
        if (this.result.roundWinner() == RoundResult.NONE) {
            throw new IllegalArgumentException("You are not allowed to distribute tiles right now.");
        }
        this.players.forEach(p -> p.resetTiles());
        this.stock.clear();
        this.board.clear();
        for (int i = 0; i <= this.rules.totalNumberOfTiles(); i++) {
            for (int j = i; j <= this.rules.totalNumberOfTiles(); j++) {
                this.stock.add(new Tile(i, j));
            }
        }
        Collections.shuffle(this.stock);
        for (Player p : this.players) {
            for (int j = 0; j < this.rules.numberOfTilesPerPlayer(); j++) {
                p.addTile(this.stock.remove(0));
            }
        }
        if (this.result.roundWinner() == RoundResult.DRAW) {
            this.turn = this.rules.firstToPlay(this.players);
        }
    }

    private void putOnBoard(int player, Tile tile, Position position) {
        if (this.board.isEmpty()) {
            this.board.add(tile);
            return;
        }
        int temp = position == Position.HEAD ? this.board.peekFirst().getFirstNumber() : this.board.peekLast().getSecondNumber();
        if (!tile.hasNumber(temp)) {
            throw new IllegalArgumentException(
                    String.format("It is not possible to put the tile %s in the %s of the board.", tile, position));
        }
        if (this.board.contains(tile)) {
            throw new IllegalArgumentException("This tile is already in the board.");
        }
        if (position == Position.HEAD) {
            if (tile.getSecondNumber() != temp) {
                tile.swap();
            }
            this.board.addFirst(tile);
        } else {
            if (tile.getFirstNumber() != temp) {
                tile.swap();
            }
            this.board.addLast(tile);
        }
    }

    public void play(int player, Tile tile, Position position) {
        if (this.result.gameWinner() != GameResult.NONE) {
            return;
        }
        if (player != this.turn) {
            throw new IllegalArgumentException("It is not your turn.");
        }
        if (!this.players.get(this.turn).getTiles().contains(tile)) {
            throw new IllegalArgumentException("The player does not have this tile.");
        }
        this.putOnBoard(player, tile, position);
        Player current = this.players.get(this.turn);
        current.removeTile(tile);
        int gw = GameResult.NONE;
        if (current.getTiles().isEmpty()) {
            int count = this.rules.countRoundPoints(new Move(tile, position), this.players, this.board);
            int score = this.rules.drawRound(new RoundResult(this.turn, count));
            current.addScore(score);
            if (!this.rules.continuePlaying(this.players)) {
                gw = this.rules.getWinner(this.players);
            }
            this.result = new GameResult(this.turn, gw);
            return;
        }
        this.blockedGame();
        if (this.result.roundWinner() != RoundResult.NONE) {
            return;
        }
        this.turn = (this.turn + 1) % this.players.size();
    }

    public void getFromStock(int player) throws NoUseStockException, EmptyStockException {
        if (player != this.turn) {
            throw new IllegalArgumentException("It is not your turn.");
        }
        Player p = this.players.get(player);
        if (p.hasPossibleTiles(this.board)) {
            throw new IllegalArgumentException(
                    "You can not get a piece from the stock because you have cards to play.");
        }
        if (!this.rules.useStock()) {
            throw new NoUseStockException("The game's rule does not allow grabbing pieces from the stock.");
        }
        if (this.stock.isEmpty()) {
            throw new EmptyStockException("The stock is empty.");
        }
        p.addTile(this.stock.remove(0));
        this.blockedGame();
    }

    private void blockedGame() {
        int rw = RoundResult.NONE;
        int gw = GameResult.NONE;
        if (!this.canAnyonePlay()) {
            RoundResult r = this.rules.blockedGame(this.players);
            int score = this.rules.drawRound(r);
            this.turn = r.winner();
            if (r.winner() != RoundResult.DRAW) {
                this.players.get(this.turn).addScore(score);
            }
            if (!this.rules.continuePlaying(this.players)) {
                gw = this.rules.getWinner(this.players);
            }
            rw = this.turn;
        }
        this.result = new GameResult(rw, gw);
    }

    public void skip(int player) {
        if (player != this.turn) {
            throw new IllegalArgumentException("It is not your turn.");
        }
        Player current = this.players.get(this.turn);
        if (current.hasPossibleTiles(this.board)) {
            throw new UnsupportedOperationException("You can not skip because you have cards to play.");
        }
        if (this.rules.useStock() && !this.stock.isEmpty()) {
            throw new UnsupportedOperationException("You can not skip because you must get cards from the stock.");
        }
        this.turn = (this.turn + 1) % this.players.size();
    }

    private boolean canAnyonePlay() {
        boolean playersHasTile = this.players.stream().anyMatch(p -> p.hasPossibleTiles(this.board));
        if (!playersHasTile && this.rules.useStock()) {
            return !this.stock.isEmpty();
        }
        return playersHasTile;
    }

    public LinkedList<Tile> getBoard() {
        return board;
    }

    public int getTurn() {
        return turn;
    }

    public List<Tile> getStock() {
        return stock;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameResult getResult() {
        return result;
    }
}
