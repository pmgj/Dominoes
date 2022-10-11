package model.rules.gamewinner;

import java.util.List;

import model.players.Player;

public class CoupleWinner implements GameWinner {

    @Override
    public int execute(List<Player> players) {
        int count1 = players.get(0).getScore() + players.get(2).getScore();
        int count2 = players.get(1).getScore() + players.get(3).getScore();
        return count1 > count2 ? 0 : 1;
    }
}
