package model.rules.gamewinner;

import java.util.List;

import model.players.Player;

public class HigherPointsWinner implements GameWinner {

    @Override
    public int execute(List<Player> players) {
        int index = 0, max = players.stream().mapToInt(a -> a.getScore()).max().getAsInt();
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getScore() == max) {
                index = i;
            }
        }
        return index;
    }
}
