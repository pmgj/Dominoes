package model.rules.roundpoints;

import model.Move;
import model.Tile;
import model.players.Player;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SumOpponentsPoints implements RoundPoints {

    @Override
    public int execute(Move move, List<Player> players, LinkedList<Tile> board) {
        List<Integer> sums = new ArrayList<>();
        players.stream().map(p -> p.getTiles()).forEach(tiles -> sums.add(tiles.stream().mapToInt(t -> t.sum()).sum()));
        return sums.stream().reduce(0, (a, b) -> a + b);
    }    
}
