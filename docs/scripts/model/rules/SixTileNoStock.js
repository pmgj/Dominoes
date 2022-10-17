import DominoesRules from "./DominoesRules.js";

export default class SixTileNoStock extends DominoesRules {
    
    constructor() {
        super();
        this.numOfPlayers = new FourPlayers();
        this.totalTiles = new DoubleSix();
        this.numOfTiles = new SixTiles();
        this.firstToPlay = new BiggestDouble();
        this.blockedGame = new SmallestHandOnePoint();
        this.roundPoints = new OneTwoThreeFourPoints();
        this.continuePlaying = new SixPointsCouple();
        this.gameWinner = new CoupleWinner();
        this.endOfRound = new DrawDouble();
        this.useStock = new NoStock();
    }
}
