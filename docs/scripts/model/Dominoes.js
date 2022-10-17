import {Position} from "./Position.js";

export default class Dominoes {
    constructor() {
        this.board = [];
    }
    getBoard() {
        return this.board;
    }
    play(tile, position) {
        if (this.board.length === 0) {
            this.board.push(tile);
            return;
        }
        let temp = position === Position.HEAD ? this.board[0].getFirstNumber() : this.board[this.board.length - 1].getSecondNumber();
        if (!tile.hasNumber(temp)) {
            throw new Error(`It is not possible to place the tile ${tile} in the ${position} of the board.`);
        }
        if (this.board.filter(t => t.equals(tile)).length !== 0) {
            throw new Error("This tile is already in the board.");
        }
        if (position === Position.HEAD) {
            if (tile.getSecondNumber() !== temp) {
                tile.swap();
            }
            this.board.unshift(tile);
        } else {
            if (tile.getFirstNumber() !== temp) {
                tile.swap();
            }
            this.board.push(tile);
        }
    }
}