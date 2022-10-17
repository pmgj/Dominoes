export default class BiggestDouble {

    execute(players) {
        let found = false;
        for (let i = 6; i >= 0 && !found; i--) {
            for (let j = 0; !found && j < players.size(); j++) {
                let player = players[j];
                let n = i;
                if (player.getTiles().some(p => p.isDouble() && p.getFirstNumber() === n)) {
                    return j;
                }
            }
        }
        return 0;
    }
    
}
