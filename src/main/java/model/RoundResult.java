package model;

public class RoundResult {
    public static final int NONE = -1;
    public static final int DRAW = -2;
    
    private final int winner;
    private final int score;

    public RoundResult(int _winner, int _score) {
        this.winner = _winner;
        this.score = _score;
    }

    public int getWinner() {
        return winner;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.format("(%s, %d)", this.winner, this.score);
    }
}
