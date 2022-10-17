export default class HigherPointsWinner {

    execute(players) {
        let index = 0, max = Math.max(...players.map(p => p.getPoints()));
        for (let i = 0; i < players.length; i++) {
            if(players[i].getPoints() === max) {
                index = i;
            }
        }
        return index;
    }
}
