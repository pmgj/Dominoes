package model.rules.drawround;

import model.RoundResult;

public class NoDraw implements DrawRound {

    @Override
    public int execute(RoundResult r) {
        return r.score();
    }

}
