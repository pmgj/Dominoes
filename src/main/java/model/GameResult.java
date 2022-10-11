package model;

public class GameResult {
    public static final int NONE = -1;

    private int roundWinner;
    private int gameWinner;

    public GameResult(int _rw, int _gw) {
        this.roundWinner = _rw;
        this.gameWinner = _gw;
    }

    public int getGameWinner() {
        return gameWinner;
    }

    public int getRoundWinner() {
        return roundWinner;
    }

    public void update(int _rw, int _gw) {
        this.roundWinner = _rw;
        this.gameWinner = _gw;
    }
}
