package model;

public class Tile {

    private int firstNumber;
    private int secondNumber;

    public Tile(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public boolean hasNumber(int number) {
        return number == this.firstNumber || number == this.secondNumber;
    }

    public boolean isDouble() {
        return this.firstNumber == this.secondNumber;
    }

    public void swap() {
        int temp = this.firstNumber;
        this.firstNumber = this.secondNumber;
        this.secondNumber = temp;
    }

    public int sum() {
        return this.firstNumber + this.secondNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) obj;
        return (tile.firstNumber == this.firstNumber && tile.secondNumber == this.secondNumber) || (tile.firstNumber == this.secondNumber && tile.secondNumber == this.firstNumber);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", this.firstNumber, this.secondNumber);
    }
}
