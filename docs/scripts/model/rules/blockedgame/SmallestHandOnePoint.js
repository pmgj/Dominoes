import Result from "../../Result.js";
import {ResultType} from "../../ResultType.js";

export default class SmallestHandOnePoint {

    execute(players) {
        let sums = [];
        players.map(p => p.getTiles()).forEach(tiles => sums.push(tiles.map(t => t.sum()).reduce((a, b) => a + b, 0)));
        let min = Math.min(...sums);
        let count = sums.filter(p => p === min).length;
        let sameCouple = (sums.get(0) === min && sums.get(2) === min) || (sums.get(1) === min && sums.get(3) === min);
        if (count > 1 && !sameCouple) {
            return new Result(ResultType.DRAW);
        }
        return new Result(sums.indexOf(min), 1);
    }
}
