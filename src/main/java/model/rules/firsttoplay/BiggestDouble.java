package model.rules.firsttoplay;

import model.players.Player;
import java.util.List;

public class BiggestDouble implements FirstToPlay {

    @Override
    public int execute(List<Player> players) {
        boolean found = false;
        for (int i = 6; i >= 0 && !found; i--) {
            for (int j = 0; !found && j < players.size(); j++) {
                Player player = players.get(j);
                int n = i;
                if (player.getTiles().stream().anyMatch(p -> p.isDouble() && p.getFirstNumber() == n)) {
                    return j;
                }
            }
        }
        return 0;
    }
}
