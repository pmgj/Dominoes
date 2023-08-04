package model;

public record GameResult(int roundWinner, int gameWinner) {
    public static final int NONE = -1;

}