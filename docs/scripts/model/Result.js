export default class Result {
    constructor(winner, points = 0) {
        this.winner = winner;
        this.points = points;
    }

    getWinner() {
        return this.winner;
    }

    getPoints() {
        return this.points;
    }

    toString() {
        return `(${this.winner}, ${this.points})`;
    }
}
