package model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import model.players.Player;
import model.players.RandomTilePlayer;
import model.rules.DominoesRules;
import model.rules.SevenTileSingle;
import model.rules.SixTileNoStock;

public class DominoesTest {

        @Test
        public void testWinner1() {
                System.out.println("********** Start Game **********");
                DominoesRules rules = new SixTileNoStock();
                Player p1 = new RandomTilePlayer("Aracy");
                Player p2 = new RandomTilePlayer("Bruno");
                Player p3 = new RandomTilePlayer("Carla");
                Player p4 = new RandomTilePlayer("David");
                List<Player> players = List.of(p1, p2, p3, p4);
                List<Tile> stock;
                Domino d = new Domino(rules, players);

                Runnable scores = () -> {
                        for (int i = 0; i < players.size(); i++) {
                                Player p = players.get(i);
                                System.out.println(String.format("%s -> %d points", p.getName(),
                                                d.getPlayers().get(i).getScore()));
                        }
                };

                System.out.println("---------- Start Round ----------");
                List<Tile> t1 = List.of(new Tile(2, 4),
                                new Tile(0, 5),
                                new Tile(2, 6),
                                new Tile(1, 5),
                                new Tile(4, 4),
                                new Tile(1, 6));
                List<Tile> t2 = List.of(new Tile(0, 3),
                                new Tile(6, 6),
                                new Tile(5, 6),
                                new Tile(3, 4),
                                new Tile(3, 5),
                                new Tile(3, 3));
                List<Tile> t3 = List.of(new Tile(1, 1),
                                new Tile(4, 6),
                                new Tile(2, 3),
                                new Tile(0, 1),
                                new Tile(2, 5),
                                new Tile(2, 2));
                List<Tile> t4 = List.of(new Tile(3, 6),
                                new Tile(5, 5),
                                new Tile(0, 6),
                                new Tile(0, 2),
                                new Tile(4, 5),
                                new Tile(1, 2));
                List<List<Tile>> allTiles = List.of(t1, t2, t3, t4);
                stock = new ArrayList<>();
                stock.add(new Tile(0, 0));
                stock.add(new Tile(0, 4));
                stock.add(new Tile(1, 3));
                stock.add(new Tile(1, 4));
                d.resetRound(allTiles, stock);

                try {
                        d.play(0, new Tile(6, 6), Position.TAIL);
                        Assertions.fail("It is not your turn.");
                } catch (Exception e) {

                }
                d.play(1, new Tile(6, 6), Position.TAIL);
                try {
                        d.play(2, new Tile(3, 6), Position.TAIL);
                        Assertions.fail("You do not have this card.");
                } catch (Exception e) {

                }
                d.play(2, new Tile(4, 6), Position.TAIL);
                d.play(3, new Tile(4, 5), Position.TAIL);
                d.play(0, new Tile(2, 6), Position.HEAD);
                d.play(1, new Tile(5, 6), Position.TAIL);
                try {
                        d.skip(2);
                        Assertions.fail();
                } catch (Exception e) {

                }
                d.play(2, new Tile(2, 2), Position.HEAD);
                d.play(3, new Tile(3, 6), Position.TAIL);
                d.play(0, new Tile(2, 4), Position.HEAD);
                d.play(1, new Tile(3, 4), Position.HEAD);
                d.play(2, new Tile(2, 3), Position.HEAD);
                d.play(3, new Tile(0, 2), Position.HEAD);
                d.play(0, new Tile(0, 5), Position.HEAD);
                d.play(1, new Tile(3, 5), Position.HEAD);
                d.skip(2);
                d.skip(3);
                d.skip(0);
                d.play(1, new Tile(3, 3), Position.HEAD);
                d.skip(2);
                d.skip(3);
                d.skip(0);
                d.play(1, new Tile(0, 3), Position.HEAD);
                Assertions.assertEquals(1, d.getResult().roundWinner());
                scores.run();

                System.out.println("---------- Start Round ----------");
                t1 = List.of(new Tile(2, 4),
                                new Tile(0, 4),
                                new Tile(2, 6),
                                new Tile(1, 5),
                                new Tile(2, 3),
                                new Tile(4, 4));
                t2 = List.of(new Tile(0, 3),
                                new Tile(0, 1),
                                new Tile(6, 6),
                                new Tile(3, 4),
                                new Tile(3, 5),
                                new Tile(3, 3));
                t3 = List.of(new Tile(1, 3),
                                new Tile(1, 1),
                                new Tile(0, 2),
                                new Tile(4, 6),
                                new Tile(2, 5),
                                new Tile(1, 4));
                t4 = List.of(new Tile(2, 2),
                                new Tile(3, 6),
                                new Tile(0, 5),
                                new Tile(5, 5),
                                new Tile(1, 6),
                                new Tile(0, 6));
                allTiles = List.of(t1, t2, t3, t4);
                stock.clear();
                stock.add(new Tile(0, 0));
                stock.add(new Tile(4, 5));
                stock.add(new Tile(5, 6));
                stock.add(new Tile(1, 2));
                d.resetRound(allTiles, stock);
                d.play(1, new Tile(6, 6), Position.TAIL);
                d.play(2, new Tile(4, 6), Position.TAIL);
                d.play(3, new Tile(0, 6), Position.HEAD);
                d.play(0, new Tile(0, 4), Position.HEAD);
                d.play(1, new Tile(3, 4), Position.HEAD);
                d.play(2, new Tile(1, 3), Position.HEAD);
                d.play(3, new Tile(1, 6), Position.HEAD);
                d.play(0, new Tile(4, 4), Position.TAIL);
                try {
                        d.resetRound();
                        Assertions.fail();
                } catch (Exception e) {

                }
                d.skip(1);
                d.play(2, new Tile(1, 4), Position.TAIL);
                d.play(3, new Tile(3, 6), Position.HEAD);
                d.play(0, new Tile(2, 3), Position.HEAD);
                d.play(1, new Tile(0, 1), Position.TAIL);
                d.play(2, new Tile(0, 2), Position.TAIL);
                d.play(3, new Tile(2, 2), Position.HEAD);
                d.play(0, new Tile(2, 6), Position.HEAD);
                d.skip(1);
                d.play(2, new Tile(2, 5), Position.TAIL);
                d.play(3, new Tile(5, 5), Position.TAIL);
                d.play(0, new Tile(1, 5), Position.TAIL);
                d.skip(1);
                d.play(2, new Tile(1, 1), Position.TAIL);
                Assertions.assertEquals(2, d.getResult().roundWinner());
                scores.run();

                System.out.println("---------- Start Round ----------");
                t1 = List.of(new Tile(0, 0),
                                new Tile(0, 4),
                                new Tile(2, 6),
                                new Tile(1, 5),
                                new Tile(2, 3),
                                new Tile(4, 4));
                t2 = List.of(new Tile(0, 3),
                                new Tile(0, 1),
                                new Tile(6, 6),
                                new Tile(3, 4),
                                new Tile(3, 5),
                                new Tile(3, 3));
                t3 = List.of(new Tile(1, 3),
                                new Tile(1, 1),
                                new Tile(0, 2),
                                new Tile(4, 6),
                                new Tile(2, 5),
                                new Tile(1, 4));
                t4 = List.of(new Tile(2, 2),
                                new Tile(3, 6),
                                new Tile(0, 5),
                                new Tile(5, 5),
                                new Tile(1, 6),
                                new Tile(0, 6));
                allTiles = List.of(t1, t2, t3, t4);
                stock.clear();
                stock.add(new Tile(2, 4));
                stock.add(new Tile(4, 5));
                stock.add(new Tile(5, 6));
                stock.add(new Tile(1, 2));
                d.resetRound(allTiles, stock);
                d.play(2, new Tile(1, 1), Position.TAIL);
                d.play(3, new Tile(1, 6), Position.TAIL);
                d.play(0, new Tile(1, 5), Position.HEAD);
                d.play(1, new Tile(6, 6), Position.TAIL);
                d.play(2, new Tile(2, 5), Position.HEAD);
                d.play(3, new Tile(2, 2), Position.HEAD);
                d.play(0, new Tile(2, 6), Position.HEAD);
                d.skip(1);
                d.play(2, new Tile(4, 6), Position.HEAD);
                d.play(3, new Tile(3, 6), Position.TAIL);
                d.play(0, new Tile(4, 4), Position.HEAD);
                d.play(1, new Tile(3, 4), Position.HEAD);
                d.play(2, new Tile(1, 3), Position.HEAD);
                d.skip(3);
                d.play(0, new Tile(2, 3), Position.TAIL);
                d.play(1, new Tile(0, 1), Position.HEAD);
                d.play(2, new Tile(0, 2), Position.HEAD);
                Assertions.assertEquals(0, d.getResult().roundWinner());
                scores.run();

                System.out.println("---------- Start Round ----------");
                t1 = List.of(new Tile(0, 2),
                                new Tile(4, 4),
                                new Tile(2, 5),
                                new Tile(6, 6),
                                new Tile(0, 3),
                                new Tile(3, 5));
                t2 = List.of(new Tile(0, 1),
                                new Tile(4, 6),
                                new Tile(1, 5),
                                new Tile(3, 6),
                                new Tile(0, 5),
                                new Tile(0, 6));
                t3 = List.of(new Tile(1, 3),
                                new Tile(2, 6),
                                new Tile(1, 1),
                                new Tile(2, 3),
                                new Tile(1, 4),
                                new Tile(3, 3));
                t4 = List.of(new Tile(3, 4),
                                new Tile(2, 2),
                                new Tile(1, 6),
                                new Tile(0, 0),
                                new Tile(5, 5),
                                new Tile(0, 4));
                allTiles = List.of(t1, t2, t3, t4);
                stock.clear();
                stock.add(new Tile(2, 4));
                stock.add(new Tile(4, 5));
                stock.add(new Tile(5, 6));
                stock.add(new Tile(1, 2));
                d.resetRound(allTiles, stock);

                d.play(0, new Tile(0, 2), Position.HEAD);
                d.play(1, new Tile(0, 1), Position.HEAD);
                d.play(2, new Tile(1, 3), Position.HEAD);
                d.play(3, new Tile(3, 4), Position.HEAD);
                d.play(0, new Tile(4, 4), Position.HEAD);
                d.play(1, new Tile(4, 6), Position.HEAD);
                d.play(2, new Tile(2, 6), Position.HEAD);
                d.play(3, new Tile(2, 2), Position.HEAD);
                d.play(0, new Tile(2, 5), Position.HEAD);
                d.play(1, new Tile(1, 5), Position.HEAD);
                d.play(2, new Tile(1, 1), Position.HEAD);
                d.play(3, new Tile(1, 6), Position.HEAD);
                d.play(0, new Tile(6, 6), Position.HEAD);
                d.play(1, new Tile(3, 6), Position.HEAD);
                d.play(2, new Tile(2, 3), Position.HEAD);
                Assertions.assertEquals(RoundResult.DRAW, d.getResult().roundWinner());
                scores.run();

                System.out.println("---------- Start Round ----------");
                t1 = List.of(new Tile(2, 4),
                                new Tile(0, 5),
                                new Tile(2, 6),
                                new Tile(1, 5),
                                new Tile(4, 4),
                                new Tile(1, 6));
                t2 = List.of(new Tile(0, 3),
                                new Tile(6, 6),
                                new Tile(5, 6),
                                new Tile(3, 4),
                                new Tile(3, 5),
                                new Tile(3, 3));
                t3 = List.of(new Tile(1, 1),
                                new Tile(4, 6),
                                new Tile(2, 3),
                                new Tile(0, 1),
                                new Tile(2, 5),
                                new Tile(2, 2));
                t4 = List.of(new Tile(3, 6),
                                new Tile(5, 5),
                                new Tile(0, 6),
                                new Tile(0, 2),
                                new Tile(4, 5),
                                new Tile(1, 2));
                allTiles = List.of(t1, t2, t3, t4);
                stock.clear();
                stock.add(new Tile(0, 0));
                stock.add(new Tile(0, 4));
                stock.add(new Tile(1, 3));
                stock.add(new Tile(1, 4));
                d.resetRound(allTiles, stock);
                try {
                        d.play(0, new Tile(6, 6), Position.TAIL);
                        Assertions.fail("It is not your turn.");
                } catch (Exception e) {

                }
                d.play(1, new Tile(6, 6), Position.TAIL);
                try {
                        d.play(2, new Tile(3, 6), Position.TAIL);
                        Assertions.fail("You do not have this card.");
                } catch (Exception e) {

                }
                d.play(2, new Tile(4, 6), Position.TAIL);
                d.play(3, new Tile(4, 5), Position.TAIL);
                d.play(0, new Tile(2, 6), Position.HEAD);
                d.play(1, new Tile(5, 6), Position.TAIL);
                try {
                        d.skip(2);
                        Assertions.fail();
                } catch (Exception e) {

                }
                d.play(2, new Tile(2, 2), Position.HEAD);
                d.play(3, new Tile(3, 6), Position.TAIL);
                d.play(0, new Tile(2, 4), Position.HEAD);
                d.play(1, new Tile(3, 4), Position.HEAD);
                d.play(2, new Tile(2, 3), Position.HEAD);
                d.play(3, new Tile(0, 2), Position.HEAD);
                d.play(0, new Tile(0, 5), Position.HEAD);
                d.play(1, new Tile(3, 5), Position.HEAD);
                d.skip(2);
                d.skip(3);
                d.skip(0);
                d.play(1, new Tile(3, 3), Position.HEAD);
                d.skip(2);
                d.skip(3);
                d.skip(0);
                d.play(1, new Tile(0, 3), Position.HEAD);
                Assertions.assertEquals(1, d.getResult().roundWinner());
                scores.run();
        }

        @Test
        public void testWinner2() throws Exception {
                System.out.println("********** Start Game **********");
                DominoesRules rules = new SevenTileSingle();
                Player p1 = new RandomTilePlayer("Aracy");
                Player p2 = new RandomTilePlayer("Bruno");
                Player p3 = new RandomTilePlayer("Carla");
                List<Player> players = List.of(p1, p2, p3);
                List<Tile> stock = new ArrayList<>();
                Domino d = new Domino(rules, players);

                Runnable scores = () -> {
                        for (int i = 0; i < players.size(); i++) {
                                Player p = players.get(i);
                                System.out.println(String.format("%s -> %d points", p.getName(),
                                                d.getPlayers().get(i).getScore()));
                        }
                };

                System.out.println("---------- Start Round ----------");
                List<Tile> t1 = List.of(new Tile(2, 4),
                                new Tile(0, 5),
                                new Tile(2, 6),
                                new Tile(1, 5),
                                new Tile(4, 4),
                                new Tile(1, 4),
                                new Tile(1, 6));
                List<Tile> t2 = List.of(new Tile(0, 3),
                                new Tile(6, 6),
                                new Tile(5, 6),
                                new Tile(5, 5),
                                new Tile(3, 4),
                                new Tile(3, 5),
                                new Tile(3, 3));
                List<Tile> t3 = List.of(new Tile(1, 1),
                                new Tile(4, 6),
                                new Tile(2, 3),
                                new Tile(0, 1),
                                new Tile(2, 5),
                                new Tile(0, 2),
                                new Tile(2, 2));
                List<List<Tile>> allTiles = List.of(t1, t2, t3);
                stock.clear();
                stock.add(new Tile(3, 6));
                stock.add(new Tile(0, 6));
                stock.add(new Tile(0, 0));
                stock.add(new Tile(1, 2));
                stock.add(new Tile(4, 5));
                stock.add(new Tile(0, 4));
                stock.add(new Tile(1, 3));
                d.resetRound(allTiles, stock);

                d.play(0, new Tile(0, 5), Position.TAIL);
                d.play(1, new Tile(5, 6), Position.TAIL);
                d.play(2, new Tile(4, 6), Position.TAIL);
                d.play(0, new Tile(4, 4), Position.TAIL);
                d.play(1, new Tile(3, 4), Position.TAIL);
                d.play(2, new Tile(2, 3), Position.TAIL);
                d.play(0, new Tile(2, 6), Position.TAIL);
                d.play(1, new Tile(6, 6), Position.TAIL);
                d.play(2, new Tile(0, 1), Position.HEAD);
                d.play(0, new Tile(1, 6), Position.HEAD);
                d.getFromStock(1);
                d.play(1, new Tile(3, 6), Position.HEAD);
                d.getFromStock(2);
                d.play(2, new Tile(0, 6), Position.TAIL);
                d.getFromStock(0);
                d.play(0, new Tile(0, 0), Position.TAIL);
                d.play(1, new Tile(3, 3), Position.HEAD);
                d.play(2, new Tile(0, 2), Position.TAIL);
                d.play(0, new Tile(2, 4), Position.TAIL);
                d.play(1, new Tile(3, 5), Position.HEAD);
                d.play(2, new Tile(2, 5), Position.HEAD);
                d.play(0, new Tile(1, 4), Position.TAIL);
                d.getFromStock(1);
                d.play(1, new Tile(1, 2), Position.HEAD);
                d.play(2, new Tile(1, 1), Position.TAIL);
                d.play(0, new Tile(1, 5), Position.TAIL);
                Assertions.assertEquals(0, d.getResult().roundWinner());
                Assertions.assertEquals(17, d.getPlayers().get(0).getScore());
                scores.run();

                System.out.println("---------- Start Round ----------");
                t1 = List.of(new Tile(2, 4),
                                new Tile(0, 5),
                                new Tile(2, 6),
                                new Tile(0, 0),
                                new Tile(4, 4),
                                new Tile(1, 5),
                                new Tile(1, 6));
                t2 = List.of(new Tile(1, 2),
                                new Tile(6, 6),
                                new Tile(5, 6),
                                new Tile(3, 4),
                                new Tile(3, 5),
                                new Tile(1, 4),
                                new Tile(3, 3));
                t3 = List.of(new Tile(1, 1),
                                new Tile(4, 6),
                                new Tile(2, 3),
                                new Tile(0, 1),
                                new Tile(2, 5),
                                new Tile(1, 3),
                                new Tile(2, 2));
                allTiles = List.of(t1, t2, t3);
                stock.clear();
                stock.add(new Tile(0, 3));
                stock.add(new Tile(3, 6));
                stock.add(new Tile(5, 5));
                stock.add(new Tile(0, 6));
                stock.add(new Tile(0, 2));
                stock.add(new Tile(4, 5));
                stock.add(new Tile(0, 4));
                d.resetRound(allTiles, stock);
                d.play(0, new Tile(0, 5), Position.TAIL);
                d.play(1, new Tile(5, 6), Position.TAIL);
                d.play(2, new Tile(4, 6), Position.TAIL);
                d.play(0, new Tile(4, 4), Position.TAIL);
                d.play(1, new Tile(3, 4), Position.TAIL);
                d.play(2, new Tile(2, 3), Position.TAIL);
                d.play(0, new Tile(2, 6), Position.TAIL);
                d.play(1, new Tile(6, 6), Position.TAIL);
                d.play(2, new Tile(0, 1), Position.HEAD);
                d.play(0, new Tile(1, 6), Position.TAIL);
                d.play(1, new Tile(1, 2), Position.TAIL);
                d.play(2, new Tile(2, 2), Position.TAIL);
                d.play(0, new Tile(2, 4), Position.TAIL);
                d.play(1, new Tile(1, 4), Position.TAIL);
                d.play(2, new Tile(1, 1), Position.TAIL);
                d.play(0, new Tile(1, 5), Position.TAIL);
                d.play(1, new Tile(3, 5), Position.TAIL);
                d.play(2, new Tile(1, 3), Position.TAIL);
                d.getFromStock(0);
                d.getFromStock(0);
                d.getFromStock(0);
                d.getFromStock(0);
                d.getFromStock(0);
                d.getFromStock(0);
                d.getFromStock(0);
                Assertions.assertEquals(1, d.getResult().roundWinner());
                Assertions.assertEquals(50, d.getPlayers().get(1).getScore());

                scores.run();
                System.out.println(String.format("Board: %s", d.getBoard()));
                players.forEach(p -> System.out.println(String.format("%s: %s", p.getName(), p.getTiles())));
                System.out.println(String.format("Stock: %s", d.getStock()));
        }
}