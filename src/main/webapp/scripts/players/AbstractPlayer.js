export default class AbstractPlayer {

    constructor(_name) {
        this.name = _name;
        this.score = 0;
		this.tiles = [];
    }

    resetTiles() {
        tiles = [];
    }

    resetScore() {
        this.score = 0;
    }

    getName() {
        return this.name;
    }

    getScore() {
        return this.score;
    }

    addScore(score) {
        this.score += score;
    }

    getTiles() {
        return [...tiles];
    }

    addTile(tile) {
        this.tiles.push(tile);
    }

    removeTile(tile) {
        this.tiles = this.tiles.filter(t => !t.equals(tile));
    }

    play(board) {
        return board.length === 0 ? this.selectTileEmptyBoard()
                : this.hasPossibleTiles(board) ? this.selectTileNonEmptyBoard(board) : [];
    }

    hasPossibleTiles(board) {
        if (board.length === 0) {
            return true;
        }
        let first = board[0].getFirstNumber();
        let last = board.slice(-1).getSecondNumber();
        return tiles.some(t => t.hasNumber(first) || t.hasNumber(last));
    }

    getPossibleTiles(first, last) {
        return tiles.filter(t => t.hasNumber(first) || t.hasNumber(last));
    }
}
