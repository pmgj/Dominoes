import {Position} from "../../Position.js";
import Tile from "../../Tile.js";

export default class SumOpponentsPoints {

    execute(move, players, board) {
        let lastTile = move.getTile();
        let lastPosition = move.getPosition();
        let first, last;
        if (lastPosition === Position.HEAD) {
            let fTile = board.get(1);
            let lTile = board.peekLast();
            first = fTile.getFirstNumber();
            last = lTile.getSecondNumber();
        } else {
            let fTile = board.peekFirst();
            let lTile = board.get(board.size() - 1);
            first = fTile.getFirstNumber();
            last = lTile.getSecondNumber();
        }
        let temp = new Tile(first, last);
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
