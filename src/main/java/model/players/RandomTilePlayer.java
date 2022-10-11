package model.players;

import model.Move;
import model.Position;
import model.Tile;
import java.util.LinkedList;
import java.util.List;

public class RandomTilePlayer extends AbstractPlayer {

    public RandomTilePlayer(String _name) {
        super(_name);
    }

    @Override
    public String description() {
        return "This player selects a random tile";
    }

    @Override
    public Move selectTileEmptyBoard() {
        return new Move(this.getTiles().get(0), Position.HEAD);
    }

    @Override
    public Move selectTileNonEmptyBoard(LinkedList<Tile> board) {
        int first = board.peekFirst().getFirstNumber();
        int last = board.peekLast().getSecondNumber();
        List<Tile> usableTiles = this.getPossibleTiles(first, last);
        Tile t = usableTiles.remove(0);
        return new Move(t, t.hasNumber(first) ? Position.HEAD : Position.TAIL);
    }
}
