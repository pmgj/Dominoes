export default class RoundResult {
    
    constructor(_winner, _score) {
        this.winner = _winner;
        this.score = _score;
        this.NONE = -1;
        this.DRAW = -2;    
    }

    getWinner() {
        return this.winner;
    }

    getScore() {
        return this.score;
    }

    toString() {
        return `(${this.winner}, ${this.score})`;
    }
}
