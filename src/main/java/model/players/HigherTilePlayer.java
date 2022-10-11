package model.players;

import model.Move;
import model.Position;
import model.Tile;
import java.util.LinkedList;
import java.util.List;

public class HigherTilePlayer extends AbstractPlayer {

    public HigherTilePlayer(String _name) {
        super(_name);
    }

    @Override
    public String description() {
        return "This player selects the higher tile to match the head or the tail of the board";
    }

    @Override
    public Move selectTileEmptyBoard() {
        int max = this.getTiles().stream().mapToInt(t -> t.sum()).max().getAsInt();
        Tile t = this.getTiles().stream().filter(a -> a.sum() == max).findFirst().get();
        return new Move(t, Position.HEAD);
    }

    @Override
    public Move selectTileNonEmptyBoard(LinkedList<Tile> board) {
        int first = board.peekFirst().getFirstNumber();
        int last = board.peekLast().getSecondNumber();
        List<Tile> usableTiles = this.getPossibleTiles(first, last);
        int max = usableTiles.stream().mapToInt(t -> t.sum()).max().getAsInt();
        Tile t = usableTiles.stream().filter(a -> a.sum() == max).findFirst().get();
        return new Move(t, t.hasNumber(first) ? Position.HEAD : Position.TAIL);
    }
}
