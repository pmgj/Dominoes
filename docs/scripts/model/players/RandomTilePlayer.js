import Player from "./Player.js";
import Move from "../Move.js";
import Position from "../Position.js";

export default class RandomTilePlayer extends Player {
    constructor() {
        super();
    }
    description() {
        return "This player selects a random tile";
    }

    selectTileEmptyBoard() {
        return new Move(this.tiles[0], Position.HEAD);
    }

    selectTileNonEmptyBoard(board) {
        let first = board[0].getFirstNumber();
        let last = board[board.length - 1].getSecondNumber();
        let usableTiles = this.getPossibleTiles(first, last);
        let t = usableTiles.shift();
        return new Move(t, t.hasNumber(first) ? Position.HEAD : Position.TAIL);
    }
}