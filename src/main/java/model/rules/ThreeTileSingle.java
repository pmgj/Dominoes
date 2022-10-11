package model.rules;

import model.rules.blokedgame.SmallestHandOnePoint;
import model.rules.continueplaying.SixPoints;
import model.rules.drawround.NoDraw;
import model.rules.firsttoplay.BiggestDouble;
import model.rules.gamewinner.HigherPointsWinner;
import model.rules.numberofplayers.TwoToNinePlayers;
import model.rules.numberoftiles.ThreeTiles;
import model.rules.roundpoints.OneTwoThreeFourPoints;
import model.rules.teamgame.NoTeam;
import model.rules.totaltiles.DoubleSix;
import model.rules.usestock.YesStock;

public class ThreeTileSingle extends DominoesRules {
    
    public ThreeTileSingle() {
        this.numOfPlayers = new TwoToNinePlayers();
        this.totalTiles = new DoubleSix();
        this.numOfTiles = new ThreeTiles();
        this.firstToPlay = new BiggestDouble();
        this.blockedGame = new SmallestHandOnePoint();
        this.roundPoints = new OneTwoThreeFourPoints();
        this.continuePlaying = new SixPoints();
        this.gameWinner = new HigherPointsWinner();
        this.drawRound = new NoDraw();
        this.useStock = new YesStock();
        this.teamGame = new NoTeam();
    }
}
