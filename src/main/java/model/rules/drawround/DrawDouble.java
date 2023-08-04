package model.rules.drawround;

import model.RoundResult;

public class DrawDouble implements DrawRound {

    private boolean draw = false;

    @Override
    public int execute(RoundResult r) {
        int score = r.score();
        switch (r.winner()) {
            case RoundResult.DRAW -> {
                draw = true;
            }
            default -> {
                score = draw ? 2 * r.score() : r.score();
                draw = false;
            }
        }
        return score;
    }

}
