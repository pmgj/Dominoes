import {ResultType} from "../../ResultType.js";

export default class NoDraw {

    execute(r, players) {
        let index = r.getWinner();
        if (index !== ResultType.DRAW) {
            let winner = players[index];
            let oldPoints = winner.getPoints();
            winner.setPoints(oldPoints + r.getPoints());
        }
        return index;
    }
}
