import DominoesRules from "./DominoesRules.js";
import TwoToFourPlayers from "./numberofplayers/TwoToFourPlayers.js";
import DoubleSix from "./totaltiles/DoubleSix.js";
import SevenTiles from "./numberoftiles/SevenTiles.js";
import RandomPlayer from "./firsttoplay/RandomPlayer.js";
import SmallestHandOpponentsPointSingle from "./blockedgame/SmallestHandOpponentsPointSingle.js";
import SumOpponentsPoints from "./roundpoints/SumOpponentsPoints.js";
import TwoFiveZeroPoints from "./continueplaying/TwoFiveZeroPoints.js";
import HigherPointsWinner from "./gamewinner/HigherPointsWinner.js";
import NoDraw from "./endofround/NoDraw.js";
import YesStock from "./usestock/YesStock.js";

export default class SevenTileSingle extends DominoesRules {
    
    constructor() {
        super();
        this.numOfPlayers = new TwoToFourPlayers();
        this.totalTiles = new DoubleSix();
        this.numOfTiles = new SevenTiles();
        this.firstToPlay = new RandomPlayer();
        this.blockedGame = new SmallestHandOpponentsPointSingle();
        this.roundPoints = new SumOpponentsPoints();
        this.continuePlaying = new TwoFiveZeroPoints();
        this.gameWinner = new HigherPointsWinner();
        this.endOfRound = new NoDraw();
        this.useStock = new YesStock();
    }
}
