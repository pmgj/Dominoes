import Result from "../../Result.js";
import ResultType from "../../ResultType.js";

export default class SmallestHandOpponentsPointSingle {

    execute(players) {
        let sums = [];
        players.map(p => p.getTiles()).forEach(tiles => sums.push(tiles.map(t => t.sum()).reduce((a, b) => a + b, 0)));
        let min = Math.min(...sums);
        let count = sums.filter(p => p === min).length;
        if (count > 1) {
            return new Result(ResultType.DRAW);
        }
        let minIndex = sums.indexOf(min);
        let opponentsSum = sums.reduce((a, b) => a + b, 0) - min;
        return new Result(minIndex, opponentsSum);
    }
}
