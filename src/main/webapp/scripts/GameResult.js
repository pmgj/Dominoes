export default class GameResult {

    constructor(_rw, _gw) {
        this.roundWinner = _rw;
        this.gameWinner = _gw;
        this.NONE = -1;
    }

    getGameWinner() {
        return this.gameWinner;
    }

    getRoundWinner() {
        return this.roundWinner;
    }

    update(_rw, _gw) {
        this.roundWinner = _rw;
        this.gameWinner = _gw;
    }
}
