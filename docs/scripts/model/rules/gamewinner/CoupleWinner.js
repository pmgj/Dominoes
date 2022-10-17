export default class CoupleWinner {

    execute(players) {
        let count1 = players[0].getPoints() + players[2].getPoints();
        let count2 = players[1].getPoints() + players[3].getPoints();
        return count1 > count2 ? 0 : 1;
    }
}
