package view.cli;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import model.Domino;
import model.GameResult;
import model.Move;
import model.RoundResult;
import model.exceptions.EmptyStockException;
import model.exceptions.NoUseStockException;
import model.players.CLIPlayer;
import model.players.DifferentTilesPlayer;
import model.players.HigherTilePlayer;
import model.players.Player;
import model.players.RandomTilePlayer;
import model.rules.DominoesRules;
import model.rules.SevenTileSingle;

public class CLI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private Domino domino;
    private boolean toPrint;

    public CLI(List<Player> _players, DominoesRules _rules, boolean _print) {
        this.toPrint = _print;
        this.domino = new Domino(_rules, _players);
    }

    private void print(String s, String color) {
        if (toPrint) {
            System.out.println(color + s + ANSI_RESET);
        }
    }

    private void pause() {
        if (toPrint) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {

            }
        }
    }

    private void printState() {
        print(String.format("Board: %s", this.domino.getBoard()), ANSI_PURPLE);
        print(String.format("Stock: %d tiles", this.domino.getStock().size()), ANSI_WHITE);
        for (int i = 0; i < this.domino.getPlayers().size(); i++) {
            Player p = this.domino.getPlayers().get(i);
            print(String.format("%s (%3d points): %d tiles", p.getName(), this.domino.getPlayers().get(i).getScore(),
                    p.getTiles().size()),
                    ANSI_WHITE);
        }
        this.pause();
    }

    private void printEndRound() {
        print("-------------- End Round --------------", ANSI_YELLOW);
        print(String.format("Board: %s", this.domino.getBoard()), ANSI_BLUE);
        print(String.format("Stock: %s", this.domino.getStock()), ANSI_CYAN);
        for (int i = 0; i < this.domino.getPlayers().size(); i++) {
            Player p = this.domino.getPlayers().get(i);
            print(String.format("%s (%3d points): %s", p.getName(), this.domino.getPlayers().get(i).getScore(),
                    p.getTiles()),
                    ANSI_GREEN);
        }
        this.pause();
    }

    private void playTile() {
        int index = this.domino.getTurn();
        this.printState();
        Player current = this.domino.getPlayers().get(index);
        Optional<Move> m;
        do {
            m = current.play(this.domino.getBoard());
            if (m.isPresent()) {
                break;
            }
            print(String.format("%s does not have a tile.", current.getName()), ANSI_RED);
            try {
                this.domino.getFromStock(index);
            } catch (NoUseStockException ex) {
                break;
            } catch (EmptyStockException ex) {
                print("Blocked.", ANSI_RED);
                this.pause();
                break;
            }
            print("Grabbing one from the stock.", ANSI_RED);
            this.pause();
        } while (this.domino.getResult().getRoundWinner() == RoundResult.NONE);
        try {
            m.ifPresentOrElse(move -> this.domino.play(index, move.tile(), move.position()), () -> this.domino.skip(index));
        } catch (Exception ex) {
            print(ex.getMessage(), ANSI_RED);
        }
    }

    private void playRound() {
        print("-------------- Start Round --------------", ANSI_BLUE);
        this.domino.resetRound();
        do {
            this.playTile();
        } while (this.domino.getResult().getRoundWinner() == RoundResult.NONE);
        this.printEndRound();
    }

    public GameResult playGame() {
        do {
            this.playRound();
        } while (this.domino.getResult().getGameWinner() == RoundResult.NONE);
        return this.domino.getResult();
    }

    public static void main(String[] args) throws Exception {
        Player p1 = new RandomTilePlayer("Aracy");
        Player p2 = new HigherTilePlayer("Bruno");
        Player p3 = new DifferentTilesPlayer("Carla");
        Player p4 = new CLIPlayer("Paulo");
        List<Player> players = Arrays.asList(new Player[] { p2, p3, p4 });
        DominoesRules rules = new SevenTileSingle();
        CLI cli = new CLI(players, rules, true);
        GameResult r = cli.playGame();
        String s;
        if (rules.teamGame()) {
            switch (r.getGameWinner()) {
                case 0:
                    s = String.format("%s and %s won the game!", p1.getName(), p3.getName());
                    break;
                case 1:
                    s = String.format("%s and %s won the game!", p2.getName(), p4.getName());
                    break;
                default:
                    s = String.format("Invalid result: %s", r);
            }
        } else {
            s = String.format("%s won the game!",
                    players.get(r.getGameWinner()).getName());
        }
        System.out.println(s);
    }

}
