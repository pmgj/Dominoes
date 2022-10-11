package model.rules.roundpoints;

import model.Move;
import model.Position;
import model.Tile;
import model.players.Player;
import java.util.LinkedList;
import java.util.List;

public class OneTwoThreeFourPoints implements RoundPoints {

    @Override
    public int execute(Move move, List<Player> players, LinkedList<Tile> board) {
        Tile lastTile = move.getTile();
        Position lastPosition = move.getPosition();
        int first, last;
        if (lastPosition == Position.HEAD) {
            Tile fTile = board.get(1);
            Tile lTile = board.peekLast();
            first = fTile.getFirstNumber();
            last = lTile.getSecondNumber();
        } else {
            Tile fTile = board.peekFirst();
            Tile lTile = board.get(board.size() - 1);
            first = fTile.getFirstNumber();
            last = lTile.getSecondNumber();
        }
        Tile temp = new Tile(first, last);
        if (lastTile.isDouble() && temp.isDouble()) {
            return 4;
        }
        if (lastTile.equals(temp)) {
            return 3;
        }
        if (lastTile.isDouble()) {
            return 2;
        }
        return 1;
    }
    
}
