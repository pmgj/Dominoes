package model.rules.continueplaying;

import java.util.List;

import model.players.Player;

public class SixPointsCouple implements ContinuePlaying {

    @Override
    public boolean execute(List<Player> players) {
        int count1 = players.get(0).getScore() + players.get(2).getScore();
        int count2 = players.get(1).getScore() + players.get(3).getScore();
        return count1 < 6 && count2 < 6;
    }
    
}
