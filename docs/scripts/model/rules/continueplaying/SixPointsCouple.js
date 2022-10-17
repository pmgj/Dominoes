export default class SixPointsCouple {

    execute(players) {
        let count1 = players.get(0).getPoints() + players.get(2).getPoints();
        let count2 = players.get(1).getPoints() + players.get(3).getPoints();
        return count1 < 6 && count2 < 6;
    }

}
