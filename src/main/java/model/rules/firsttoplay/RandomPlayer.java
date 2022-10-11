package model.rules.firsttoplay;

import java.util.List;

import model.players.Player;

public class RandomPlayer implements FirstToPlay {

    @Override
    public int execute(List<Player> players) {
        return 0;
        // Random r = new Random();
        // return r.nextInt(players.size());
    }
    
}
