package model.rules;

import java.util.LinkedList;
import java.util.List;

import model.Move;
import model.RoundResult;
import model.Tile;
import model.players.Player;
import model.rules.blokedgame.BlockedGame;
import model.rules.continueplaying.ContinuePlaying;
import model.rules.drawround.DrawRound;
import model.rules.firsttoplay.FirstToPlay;
import model.rules.gamewinner.GameWinner;
import model.rules.numberofplayers.NumberOfPlayers;
import model.rules.numberoftiles.NumberOfTiles;
import model.rules.roundpoints.RoundPoints;
import model.rules.teamgame.TeamGame;
import model.rules.totaltiles.TotalTiles;
import model.rules.usestock.UseStock;

public abstract class DominoesRules {

    protected TotalTiles totalTiles;
    protected NumberOfPlayers numOfPlayers;
    protected NumberOfTiles numOfTiles;
    protected FirstToPlay firstToPlay;
    protected BlockedGame blockedGame;
    protected RoundPoints roundPoints;
    protected ContinuePlaying continuePlaying;
    protected GameWinner gameWinner;
    protected DrawRound drawRound;
    protected UseStock useStock;
    protected TeamGame teamGame;

    public boolean hasCorrectNumberOfPlayers(int numOfPlayers) {
        return this.numOfPlayers.execute(numOfPlayers);
    }

    public int numberOfTilesPerPlayer() {
        return this.numOfTiles.execute();
    }

    public int totalNumberOfTiles() {
        return this.totalTiles.execute();
    }

    public int firstToPlay(List<Player> players) {
        return this.firstToPlay.execute(players);
    }

    public RoundResult blockedGame(List<Player> players) {
        return this.blockedGame.execute(players);
    }

    public int countRoundPoints(Move move, List<Player> players, LinkedList<Tile> board) {
        return this.roundPoints.execute(move, players, board);
    }

    public boolean continuePlaying(List<Player> players) {
        return this.continuePlaying.execute(players);
    }

    public int getWinner(List<Player> players) {
        return this.gameWinner.execute(players);
    }

    public int drawRound(RoundResult r) {
        return this.drawRound.execute(r);
    }
    
    public boolean useStock() {
        return this.useStock.execute();
    }

    public boolean teamGame() {
        return this.teamGame.execute();
    }
}
