package model.rules.roundpoints;

import model.Move;
import model.Tile;
import model.players.Player;
import java.util.LinkedList;
import java.util.List;

public interface RoundPoints {

    public int execute(Move move, List<Player> players, LinkedList<Tile> board);
}
