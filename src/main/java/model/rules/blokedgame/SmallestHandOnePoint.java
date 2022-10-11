package model.rules.blokedgame;

import model.players.Player;
import model.RoundResult;
import java.util.ArrayList;
import java.util.List;

public class SmallestHandOnePoint implements BlockedGame {

    @Override
    public RoundResult execute(List<Player> players) {
        List<Integer> sums = new ArrayList<>();
        players.stream().map(p -> p.getTiles()).forEach(tiles -> sums.add(tiles.stream().mapToInt(t -> t.sum()).sum()));
        int min = sums.stream().min(Integer::compare).get();
        long count = sums.stream().filter(p -> p == min).count();
        boolean sameCouple = (sums.get(0) == min && sums.get(2) == min) || (sums.get(1) == min && sums.get(3) == min);
        if (count > 2 || (count == 2 && !sameCouple)) {
            return new RoundResult(RoundResult.DRAW, 0);
        }
        return new RoundResult(sums.indexOf(min), 1);
    }
    
}
