package model.rules.drawround;

import model.RoundResult;

public class DrawDouble implements DrawRound {

    private boolean draw = false;

    @Override
    public int execute(RoundResult r) {
        int score = r.getScore();
        switch (r.getWinner()) {
            case RoundResult.DRAW -> {
                draw = true;
            }
            default -> {
                score = draw ? 2 * r.getScore() : r.getScore();
                draw = false;
            }
        }
        return score;
    }

}
