package model.rules.teamgame;

public class NoTeam implements TeamGame {

    @Override
    public boolean execute() {
        return false;
    }

}
