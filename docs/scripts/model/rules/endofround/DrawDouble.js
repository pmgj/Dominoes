import ResultType from "../../ResultType.js";

export default class DrawDouble {

    constructor() {
        this.draw = false;
    }

    execute(r, players) {
        let index = -1;
        switch (r.getWinner()) {
            case ResultType.DRAW:
                draw = true;
                break;
            default:
                index = r.getWinner();
                let winner = players.get(index);
                let oldPoints = winner.getPoints();
                winner.setPoints(oldPoints + (draw ? 2 * r.getPoints() : r.getPoints()));
                draw = false;
                break;
        }
        return index;
    }
}
