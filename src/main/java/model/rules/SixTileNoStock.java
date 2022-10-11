package model.rules;

import model.rules.blokedgame.SmallestHandOnePoint;
import model.rules.continueplaying.SixPointsCouple;
import model.rules.drawround.DrawDouble;
import model.rules.firsttoplay.BiggestDouble;
import model.rules.gamewinner.CoupleWinner;
import model.rules.numberofplayers.FourPlayers;
import model.rules.numberoftiles.SixTiles;
import model.rules.roundpoints.OneTwoThreeFourPoints;
import model.rules.teamgame.YesTeam;
import model.rules.totaltiles.DoubleSix;
import model.rules.usestock.NoStock;

public class SixTileNoStock extends DominoesRules {
    
    public SixTileNoStock() {
        this.numOfPlayers = new FourPlayers();
        this.totalTiles = new DoubleSix();
        this.numOfTiles = new SixTiles();
        this.firstToPlay = new BiggestDouble();
        this.blockedGame = new SmallestHandOnePoint();
        this.roundPoints = new OneTwoThreeFourPoints();
        this.continuePlaying = new SixPointsCouple();
        this.gameWinner = new CoupleWinner();
        this.drawRound = new DrawDouble();
        this.useStock = new NoStock();
        this.teamGame = new YesTeam();
    }
}
