package model.players;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.Move;
import model.Tile;

public abstract class AbstractPlayer implements Player {

    private final List<Tile> tiles;
    private final String name;
    private int score;

    public AbstractPlayer(String _name) {
        this.name = _name;
        this.score = 0;
		this.tiles = new ArrayList<>();
    }

    public void resetTiles() {
        tiles.clear();
    }

    public void resetScore() {
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public List<Tile> getTiles() {
        return new ArrayList<>(tiles);
    }

    public void addTile(Tile tile) {
        this.tiles.add(tile);
    }

    public void removeTile(Tile tile) {
        this.tiles.remove(tile);
    }

    public Optional<Move> play(LinkedList<Tile> board) {
        return board.isEmpty() ? Optional.of(this.selectTileEmptyBoard())
                : this.hasPossibleTiles(board) ? Optional.of(this.selectTileNonEmptyBoard(board)) : Optional.empty();
    }

    public boolean hasPossibleTiles(LinkedList<Tile> board) {
        if (board.isEmpty()) {
            return true;
        }
        int first = board.peekFirst().getFirstNumber();
        int last = board.peekLast().getSecondNumber();
        return tiles.stream().anyMatch(t -> t.hasNumber(first) || t.hasNumber(last));
    }

    protected List<Tile> getPossibleTiles(int first, int last) {
        return tiles.stream().filter(t -> t.hasNumber(first) || t.hasNumber(last)).collect(Collectors.toList());
    }

    protected abstract Move selectTileEmptyBoard();

    protected abstract Move selectTileNonEmptyBoard(LinkedList<Tile> board);

    protected abstract String description();

}
