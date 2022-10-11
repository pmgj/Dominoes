package model.rules.continueplaying;

import java.util.List;

import model.players.Player;

public interface ContinuePlaying {

    public boolean execute(List<Player> players);

}
