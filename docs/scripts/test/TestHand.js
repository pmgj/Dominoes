import Dominoes from "../model/Dominoes.js";
import {Position} from "../model/Position.js";
import Tile from "../model/Tile.js";

class TestHand {

    testEmptyBoard() {
        let std = new Dominoes();
        std.play(new Tile(6, 6), Position.TAIL);
    }

    testWrongTile() {
        try {
            let std = new Dominoes();
            std.play(new Tile(6, 6), Position.TAIL);
            std.play(new Tile(5, 5), Position.TAIL);
            console.error();
        } catch (ex) {

        }
    }

    testRepeatingTile() {
        try {
            let std = new Dominoes();
            std.play(new Tile(6, 6), Position.TAIL);
            std.play(new Tile(6, 6), Position.TAIL);
            console.error();
        } catch (ex) {

        }
    }

    testHand() {
        let std = new Dominoes();
        std.play(new Tile(6, 6), Position.TAIL);
        std.play(new Tile(5, 6), Position.TAIL);
        std.play(new Tile(4, 6), Position.HEAD);
        std.play(new Tile(4, 0), Position.HEAD);
        std.play(new Tile(0, 1), Position.HEAD);
        std.play(new Tile(5, 5), Position.TAIL);
    }
}

let t = new TestHand();
t.testEmptyBoard();
t.testWrongTile();
t.testRepeatingTile();
t.testHand();