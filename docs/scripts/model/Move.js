export default class Move {

    constructor(tile, position) {
        this.tile = tile;
        this.position = position;
    }

    getTile() {
        return this.tile;
    }

    setTile(tile) {
        this.tile = tile;
    }

    getPosition() {
        return this.position;
    }

    setPosition(position) {
        this.position = position;
    }
}
