export default class SumOpponentsPoints {

    execute(move, players, board) {
        let sumF = (a, b) => a + b;
        let sums = [];
        players.map(p => p.getTiles()).forEach(tiles => tiles.length === 0 ? 0 : sums.push(tiles.map(t => t.sum()).reduce(sumF, 0)));
        return sums.reduce(sumF, 0);
    }    
}
