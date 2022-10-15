import AbstractPlayer from "./AbstractPlayer.js";
import Move from "../Move.js";
import Position from "../Position.js";

export default class DifferentTilesPlayer extends AbstractPlayer {

    constructor(_name) {
        super(_name);
    }

    description() {
        return "This player selects a tile so to keep to highest diversity of numbers";
    }

    selectTileEmptyBoard() {
        let t = this.getBestTile(this.getTiles());
        return new Move(t, Position.HEAD);
    }

    selectTileNonEmptyBoard(board) {
        let first = board[0].getFirstNumber();
        let last = board.slice(-1).getSecondNumber();
        let usableTiles = this.getPossibleTiles(first, last);
        let t = this.getBestTile(usableTiles);
        return new Move(t, t.hasNumber(first) ? Position.HEAD : Position.TAIL);
    }

    compute(tiles) {
        let numbers = new Map();
        tiles.forEach(p => {
            let n1 = p.getFirstNumber();
            let n2 = p.getSecondNumber();
            let value = numbers.get(n1);
            numbers.set(n1, value ? value + 1 : 1);
            value = numbers.get(n2);
            numbers.set(n2, value ? value + 1 : 1);
        });
        return numbers;
    }

    getBestTile(list) {
        let poss = [];
        let max = Number.MIN_VALUE;
        for (let t of list) {
            let temp = this.getTiles();
            temp.remove(t);
            let map = this.compute(temp);
            if (map.size == max) {
                poss.push(t);
            }
            if (map.size > max) {
                max = map.size;
                poss = [t];
            }
        }
        poss.sort((a, b) => b.sum() == a.sum() ? (b.isDouble() ? 1 : -1) : b.sum() - a.sum());
        return poss.shift();
    }
}
