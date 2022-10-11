package model.rules.blokedgame;

import model.players.Player;
import model.RoundResult;
import java.util.List;

public interface BlockedGame {

    public RoundResult execute(List<Player> players);
}
