export default class Move {

    constructor(tile, position) {
        this.tile = tile;
        this.position = position;
    }

    getTile() {
        return this.tile;
    }

    getPosition() {
        return this.position;
    }
}
