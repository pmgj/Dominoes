package view.cli;

import java.util.ArrayList;
import java.util.List;

import model.GameResult;
import model.players.DifferentTilesPlayer;
import model.players.HigherTilePlayer;
import model.players.Player;
import model.players.RandomTilePlayer;
import model.rules.DominoesRules;
import model.rules.SevenTileSingle;
import model.rules.SixTileNoStock;

public class TestAlgorithms {
 
    public void testNoTeamGame() {
        Player p1 = new RandomTilePlayer("Aracy");
        Player p2 = new HigherTilePlayer("Bruno");
        Player p3 = new DifferentTilesPlayer("Carla");
        List<Player> players = List.of(p1, p2, p3);
        DominoesRules rules = new SevenTileSingle();
        System.out.println("Testing game: " + rules.getClass().getSimpleName());
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            scores.add(0);
        }
        for (int i = 0; i < 100; i++) {
            CLI cli = new CLI(players, rules, false);
            GameResult result = cli.playGame();
            Integer p = scores.get(result.getGameWinner());
            scores.set(result.getGameWinner(), p + 1);
        }
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            int score = scores.get(i);
            String s =  String.format("%s -> %d", p.getName(), score);
            System.out.println(s);
        }
    }

    public void testTeamGame1() {
        Player p1 = new RandomTilePlayer("Aracy");
        Player p2 = new DifferentTilesPlayer("Bruno");
        Player p3 = new RandomTilePlayer("Carla");
        Player p4 = new DifferentTilesPlayer("David");
        List<Player> players = List.of(p1, p2, p3, p4);
        DominoesRules rules = new SixTileNoStock();
        System.out.println("Testing game: " + rules.getClass().getSimpleName());
        int team1 = 0, team2 = 0;
        for (int i = 0; i < 100; i++) {
            CLI cli = new CLI(players, rules, false);
            GameResult result = cli.playGame();
            team1 += result.getGameWinner() == 0 ? 1 : 0;
            team2 += result.getGameWinner() == 1 ? 1 : 0;
        }
        System.out.println(String.format("%20s: %d", p1.getClass().getSimpleName(), team1));
        System.out.println(String.format("%20s: %d", p2.getClass().getSimpleName(), team2));
    }

    public void testTeamGame2() {
        Player p1 = new RandomTilePlayer("Aracy");
        Player p2 = new HigherTilePlayer("Bruno");
        Player p3 = new RandomTilePlayer("Carla");
        Player p4 = new HigherTilePlayer("David");
        List<Player> players = List.of(p1, p2, p3, p4);
        DominoesRules rules = new SixTileNoStock();
        System.out.println("Testing game: " + rules.getClass().getSimpleName());
        int team1 = 0, team2 = 0;
        for (int i = 0; i < 100; i++) {
            CLI cli = new CLI(players, rules, false);
            GameResult result = cli.playGame();
            team1 += result.getGameWinner() == 0 ? 1 : 0;
            team2 += result.getGameWinner() == 1 ? 1 : 0;
        }
        System.out.println(String.format("%20s: %d", p1.getClass().getSimpleName(), team1));
        System.out.println(String.format("%20s: %d", p2.getClass().getSimpleName(), team2));
    }

    public void testTeamGame3() {
        Player p1 = new HigherTilePlayer("Aracy");
        Player p2 = new DifferentTilesPlayer("Bruno");
        Player p3 = new HigherTilePlayer("Carla");
        Player p4 = new DifferentTilesPlayer("David");
        List<Player> players = List.of(p1, p2, p3, p4);
        DominoesRules rules = new SixTileNoStock();
        System.out.println("Testing game: " + rules.getClass().getSimpleName());
        int team1 = 0, team2 = 0;
        for (int i = 0; i < 100; i++) {
            CLI cli = new CLI(players, rules, false);
            GameResult result = cli.playGame();
            team1 += result.getGameWinner() == 0 ? 1 : 0;
            team2 += result.getGameWinner() == 1 ? 1 : 0;
        }
        System.out.println(String.format("%20s: %d", p1.getClass().getSimpleName(), team1));
        System.out.println(String.format("%20s: %d", p2.getClass().getSimpleName(), team2));
    }

    public static void main(String[] args) {
        TestAlgorithms t = new TestAlgorithms();
        t.testNoTeamGame();
        t.testTeamGame1();
        t.testTeamGame2();
        t.testTeamGame3();
    }
}
