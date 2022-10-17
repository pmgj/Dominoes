export default class RandomPlayer {

    execute(players) {
        let r = Math.random();
        return Math.floor(r * players.length);
    }
    
}
