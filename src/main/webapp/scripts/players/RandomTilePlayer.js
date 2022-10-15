import AbstractPlayer from "./AbstractPlayer.js";
import Move from "../Move.js";
import Position from "../Position.js";

export default class RandomTilePlayer extends AbstractPlayer {

    constructor(_name) {
        super(_name);
    }

    description() {
        return "This player selects a random tile";
    }

    selectTileEmptyBoard() {
        return new Move(this.getTiles()[0], Position.HEAD);
    }

    selectTileNonEmptyBoard(board) {
        let first = board[0].getFirstNumber();
        let last = board.slice(-1).getSecondNumber();
        let usableTiles = this.getPossibleTiles(first, last);
        let t = usableTiles.shift();
        return new Move(t, t.hasNumber(first) ? Position.HEAD : Position.TAIL);
    }
}
