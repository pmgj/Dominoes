package model;

public record RoundResult(int winner, int score) {
    public static final int NONE = -1;
    public static final int DRAW = -2;

    @Override
    public String toString() {
        return String.format("(%s, %d)", this.winner, this.score);
    }
}