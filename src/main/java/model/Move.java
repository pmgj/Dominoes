package model;

public class Move {

    private Tile tile;
    private Position position;

    public Move(Tile tile, Position position) {
        this.tile = tile;
        this.position = position;
    }

    public Tile getTile() {
        return tile;
    }

    public Position getPosition() {
        return position;
    }
}
