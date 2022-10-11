package model.players;

import model.Move;
import model.Position;
import model.Tile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DifferentTilesPlayer extends AbstractPlayer {

    public DifferentTilesPlayer(String _name) {
        super(_name);
    }

    @Override
    public String description() {
        return "This player selects a tile so to keep to highest diversity of numbers";
    }

    @Override
    public Move selectTileEmptyBoard() {
        Tile t = this.getBestTile(this.getTiles());
        return new Move(t, Position.HEAD);
    }

    @Override
    public Move selectTileNonEmptyBoard(LinkedList<Tile> board) {
        int first = board.peekFirst().getFirstNumber();
        int last = board.peekLast().getSecondNumber();
        List<Tile> usableTiles = this.getPossibleTiles(first, last);
        Tile t = this.getBestTile(usableTiles);
        return new Move(t, t.hasNumber(first) ? Position.HEAD : Position.TAIL);
    }

    private Map<Integer, Integer> compute(List<Tile> tiles) {
        Map<Integer, Integer> numbers = new HashMap<>();
        tiles.stream().forEach(p -> {
            int n1 = p.getFirstNumber();
            int n2 = p.getSecondNumber();
            Integer value = numbers.get(n1);
            numbers.put(n1, value != null ? value + 1 : 1);
            value = numbers.get(n2);
            numbers.put(n2, value != null ? value + 1 : 1);
        });
        return numbers;
    }

    private Tile getBestTile(List<Tile> list) {
        List<Tile> poss = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (Tile t : list) {
            List<Tile> temp = this.getTiles();
            temp.remove(t);
            Map<Integer, Integer> map = this.compute(temp);
            if (map.size() == max) {
                poss.add(t);
            }
            if (map.size() > max) {
                max = map.size();
                poss.clear();
                poss.add(t);
            }
        }
        Collections.sort(poss, (a, b) -> b.sum() == a.sum() ? (b.isDouble() ? 1 : -1) : b.sum() - a.sum());
        return poss.remove(0);
    }
}
