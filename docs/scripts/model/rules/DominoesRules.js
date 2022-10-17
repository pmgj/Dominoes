export default class DominoesRules {

    constructor() {
        this.totalTiles = null;
        this.numOfPlayers = null;
        this.numOfTiles = null;
        this.firstToPlay = null;
        this.blockedGame = null;
        this.roundPoints = null;
        this.continuePlaying = null;
        this.gameWinner = null;
        this.endOfRound = null;
        this.useStock = null;
    }

    hasCorrectNumberOfPlayers(numOfPlayers) {
        return this.numOfPlayers.execute(numOfPlayers);
    }

    numberOfTilesPerPlayer() {
        return this.numOfTiles.execute();
    }

    totalNumberOfTiles() {
        return this.totalTiles.execute();
    }

    firstPlayer(players) {
        return this.firstToPlay.execute(players);
    }

    countBlockedGame(players) {
        return this.blockedGame.execute(players);
    }

    countRoundPoints(move, players, board) {
        return this.roundPoints.execute(move, players, board);
    }

    keepPlaying(players) {
        return this.continuePlaying.execute(players);
    }

    getWinner(players) {
        return this.gameWinner.execute(players);
    }

    endRound(result, players) {
        return this.endOfRound.execute(result, players);
    }

    isUseStock() {
        return this.useStock.execute();
    }
}
