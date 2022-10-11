package model.players;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

import model.Move;
import model.Position;
import model.Tile;

public class CLIPlayer extends AbstractPlayer {

    public CLIPlayer(String _name) {
        super(_name);
    }

    private Scanner in = new Scanner(System.in);

    @Override
    public String description() {
        return "This player allows the user to select a tile to play in CLI mode";
    }

    @Override
    public Move selectTileEmptyBoard() {
        System.out.println("My Tiles: " + this.getTiles());
        Tile tile = null;
        do {
            try {
                System.out.print("Inform the index of the tile: ");
                int a = in.nextInt();
                tile = this.getTiles().get(a);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("There is no tile in the informed index.");
            } catch (InputMismatchException ex) {
                System.out.println("The informed index is not a number.");
                in.next();
            }
        } while (tile == null);
        return new Move(tile, Position.HEAD);
    }

    @Override
    public Move selectTileNonEmptyBoard(LinkedList<Tile> board) {
        Move m = this.selectTileEmptyBoard();
        Tile tile = m.getTile();
        int head = board.peekFirst().getFirstNumber();
        int tail = board.peekLast().getSecondNumber();
        if (tile.hasNumber(head) && tile.hasNumber(tail) && head != tail) {
            int a;
            do {
                a = -1;
                try {
                    System.out.print("Put tile in head (0) or tail (1): ");
                    a = in.nextInt();
                    if(a != 0 && a != 1) {
                        System.out.println("Invalid option.");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("The informed index is not a number.");
                    in.next();
                }
            } while (a != 0 && a != 1);
            return new Move(tile, a == 0 ? Position.HEAD : Position.TAIL);
        }
        if (!tile.hasNumber(head) && tile.hasNumber(tail)) {
            m = new Move(tile, Position.TAIL);
        }
        return m;
    }
}
