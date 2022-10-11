package model.rules.continueplaying;

import java.util.List;

import model.players.Player;

public class TwoFiveZeroPoints implements ContinuePlaying {

    @Override
    public boolean execute(List<Player> players) {
        return players.stream().allMatch(p -> p.getScore() < 250);
    }
    
}
