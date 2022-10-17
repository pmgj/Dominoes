import Tile from "../model/Tile.js";
import Result from "../model/Result.js";
import {ResultType} from "../model/ResultType.js";
import SevenTileSingle from "../model/rules/SevenTileSingle.js";
import RandomTilePlayer from "../model/players/RandomTilePlayer.js";
import DifferentTilesPlayer from "../model/players/DifferentTilesPlayer.js";
import HigherTilePlayer from "../model/players/HigherTilePlayer.js";
import Dominoes from "../model/Dominoes.js";

class TestGame {

    constructor() {
        this.players = null;
        this.stock = [];
        this.std = null;
        this.rules = new SevenTileSingle();
        this.index = 0;
    }

    testManyGames() {
        let map = new Map();
        for (let i = 0; i < 100; i++) {
            let r = this.playGame();
            let key = this.players[r.getWinner()].constructor.name;
            let v = map.get(key);
            map.set(key, v ? v + 1 : 1);
        }
        console.log(`testManyRounds:`);
        console.table(map);
    }

    testOneGame() {
        let r = this.playGame();
        console.log(`testOneGame: ${this.players[r.getWinner()].constructor.name} with ${r.getPoints()} points`);
    }

    playGame() {
        this.players = [new RandomTilePlayer(), new HigherTilePlayer(), new DifferentTilesPlayer()];
        this.stock = [];
        this.distributeTiles();
        this.std = new Dominoes();
        this.index = this.rules.firstPlayer(this.players);
        do {
            let r = this.playRound();
            this.index = this.rules.endRound(r, this.players);
            if (r.getWinner() === ResultType.DRAW) {
                this.index = this.rules.firstPlayer(this.players);
            }
            this.players.forEach(p => p.resetTiles());
            this.stock = [];
            this.distributeTiles();
            this.std = new Dominoes();
        } while (this.rules.keepPlaying(this.players));
        let i = this.rules.getWinner(this.players);
        return new Result(i, this.players[i].getPoints());
    }

    playRound() {
        let r;
        do {
            r = this.playTile();
//            console.log(`Winner: ${r}`);
//            console.log(`Board.: ${this.std.getBoard()}`);
//            this.players.forEach(p => console.log(`${p.getPoints()}: ${p.getTiles()}`));
//            if (this.rules.isUseStock()) {
//                console.log(`Stock: ${this.stock}`);
//            }
        } while (r.getWinner() === ResultType.NONE);
        return r;
    }

    playTile() {
        if (!this.canAnyonePlay()) {
            return this.rules.countBlockedGame(this.players);
        }
        let current = this.players[this.index];
        while (this.rules.isUseStock() && !current.hasPossibleTiles(this.std.getBoard()) && this.stock.length > 0) {
            current.addTile(this.stock.shift());
        }
        if (current.hasPossibleTiles(this.std.getBoard())) {
            let move = current.play(this.std.getBoard());
            this.std.play(move.getTile(), move.getPosition());
            current.removeTile(move.getTile());
            if (current.countTiles() === 0) {
                let count = this.rules.countRoundPoints(move, this.players, this.std.getBoard());
                return new Result(this.index, count);
            }
        }
        this.index = (this.index + 1) % this.players.length;
        return new Result(ResultType.NONE);
    }

    canAnyonePlay() {
        let playersHasTile = this.players.some(p => p.hasPossibleTiles(this.std.getBoard()));
        if (!playersHasTile && this.rules.isUseStock()) {
            return this.stock.length !== 0;
        }
        return playersHasTile;
    }

    distributeTiles() {
        this.createTiles();
        for (let i = 0; i < this.players.length; i++) {
            for (let j = 0; j < this.rules.numberOfTilesPerPlayer(); j++) {
                this.players[i].addTile(this.stock.shift());
            }
        }
    }

    createTiles() {
        for (let i = 0; i <= this.rules.totalNumberOfTiles(); i++) {
            for (let j = i; j <= this.rules.totalNumberOfTiles(); j++) {
                this.stock.push(new Tile(i, j));
            }
        }
        this.shuffle(this.stock);
    }

    shuffle(array) {
        let currentIndex = array.length, randomIndex;

        // While there remain elements to shuffle...
        while (currentIndex !== 0) {

            // Pick a remaining element...
            randomIndex = Math.floor(Math.random() * currentIndex);
            currentIndex--;

            // And swap it with the current element.
            [array[currentIndex], array[randomIndex]] = [array[randomIndex], array[currentIndex]];
        }

        return array;
    }
}

let t = new TestGame();
t.testOneGame();
t.testManyGames();
