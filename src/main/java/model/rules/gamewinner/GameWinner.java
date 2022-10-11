package model.rules.gamewinner;

import java.util.List;

import model.players.Player;

public interface GameWinner {

    public int execute(List<Player> players);
}
