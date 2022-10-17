import Player from "./Player.js";
import Move from "../Move.js";
import Position from "../Position.js";

export default class HigherTilePlayer extends Player {
    constructor() {
        super();
    }

    description() {
        return "This player selects the higher tile to match the head or the tail of the board";
    }

    selectTileEmptyBoard() {
        let max = Math.max(...this.tiles.map(t => t.sum()));
        let t = this.tiles.filter(a => a.sum() === max)[0];
        return new Move(t, Position.HEAD);
    }

    selectTileNonEmptyBoard(board) {
        let first = board[0].getFirstNumber();
        let last = board[board.length - 1].getSecondNumber();
        let usableTiles = this.getPossibleTiles(first, last);
        let max = Math.max(...usableTiles.map(t => t.sum()));
        let t = usableTiles.filter(a => a.sum() === max)[0];
        return new Move(t, t.hasNumber(first) ? Position.HEAD : Position.TAIL);
    }
}
