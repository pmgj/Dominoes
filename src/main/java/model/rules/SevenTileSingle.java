package model.rules;

import model.rules.blokedgame.SmallestHandOpponentsPointSingle;
import model.rules.continueplaying.TwoFiveZeroPoints;
import model.rules.drawround.NoDraw;
import model.rules.firsttoplay.RandomPlayer;
import model.rules.gamewinner.HigherPointsWinner;
import model.rules.numberofplayers.TwoToFourPlayers;
import model.rules.numberoftiles.SevenTiles;
import model.rules.roundpoints.SumOpponentsPoints;
import model.rules.teamgame.NoTeam;
import model.rules.totaltiles.DoubleSix;
import model.rules.usestock.YesStock;

public class SevenTileSingle extends DominoesRules {
    
    public SevenTileSingle() {
        this.numOfPlayers = new TwoToFourPlayers();
        this.totalTiles = new DoubleSix();
        this.numOfTiles = new SevenTiles();
        this.firstToPlay = new RandomPlayer();
        this.blockedGame = new SmallestHandOpponentsPointSingle();
        this.roundPoints = new SumOpponentsPoints();
        this.continuePlaying = new TwoFiveZeroPoints();
        this.gameWinner = new HigherPointsWinner();
        this.drawRound = new NoDraw();
        this.useStock = new YesStock();
        this.teamGame = new NoTeam();
    }
}
