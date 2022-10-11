package model.rules.blokedgame;

import model.players.Player;
import model.RoundResult;
import java.util.ArrayList;
import java.util.List;

public class SmallestHandOpponentsPointSingle implements BlockedGame {

    @Override
    public RoundResult execute(List<Player> players) {
        List<Integer> sums = new ArrayList<>();
        players.stream().map(p -> p.getTiles()).forEach(tiles -> sums.add(tiles.stream().mapToInt(t -> t.sum()).sum()));
        int min = sums.stream().min(Integer::compare).get();
        long count = sums.stream().filter(p -> p == min).count();
        if (count > 1) {
            return new RoundResult(RoundResult.DRAW, 0);
        }
        int minIndex = sums.indexOf(min);
        int opponentsSum = sums.stream().reduce(0, (a, b) -> a + b) - min;
        return new RoundResult(minIndex, opponentsSum);
    }
}
