package model.rules.firsttoplay;

import model.players.Player;
import java.util.List;

public interface FirstToPlay {
    public int execute(List<Player> players);    
}
