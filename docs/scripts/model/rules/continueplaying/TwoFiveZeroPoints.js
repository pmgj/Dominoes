export default class TwoFiveZeroPoints {

    execute(players) {
        return players.every(p => p.getPoints() < 250);
    }

}
