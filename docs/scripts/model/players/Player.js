export default class Player {
    
    constructor() {
        this.tiles = [];
        this.points = 0;
    }

    resetTiles() {
        this.tiles = [];
    }

    resetPoints() {
        this.points = 0;
    }

    countTiles() {
        return this.tiles.length;
    }

    getTiles() {
        return this.tiles;
    }

    addTile(tile) {
        this.tiles.push(tile);
    }

    removeTile(tile) {
        this.tiles = this.tiles.filter(t => !t.equals(tile));
    }

    getPoints() {
        return this.points;
    }

    setPoints(points) {
        this.points = points;
    }

    play(board) {
        return board.length === 0 ? this.selectTileEmptyBoard() : this.selectTileNonEmptyBoard(board);
    }

    hasPossibleTiles(board) {
        if (board.length === 0) {
            return true;
        }
        let first = board[0].getFirstNumber();
        let last = board[board.length - 1].getSecondNumber();
        let usableTiles = this.getPossibleTiles(first, last);
        return usableTiles.length !== 0;
    }
    
    getPossibleTiles(first, last) {
        return this.tiles.filter(t => t.hasNumber(first) || t.hasNumber(last));
    }
}