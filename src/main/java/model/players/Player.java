package model.players;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import model.Move;
import model.Tile;

public interface Player {

    public void resetTiles();

    public void resetScore();

    public String getName();

    public int getScore();

    public void addScore(int score);

    public List<Tile> getTiles();

    public void addTile(Tile tile);

    public void removeTile(Tile tile);

    public Optional<Move> play(LinkedList<Tile> board);

    public boolean hasPossibleTiles(LinkedList<Tile> board);

}
