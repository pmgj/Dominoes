export default class Tile {
    constructor(firstNumber, secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    getFirstNumber() {
        return this.firstNumber;
    }

    getSecondNumber() {
        return this.secondNumber;
    }

    hasNumber(number) {
        return number === this.firstNumber || number === this.secondNumber;
    }

    isDouble() {
        return this.firstNumber === this.secondNumber;
    }

    swap() {
        let temp = this.firstNumber;
        this.firstNumber = this.secondNumber;
        this.secondNumber = temp;
    }

    sum() {
        return this.firstNumber + this.secondNumber;
    }

    equals(tile) {
        return (tile.firstNumber === this.firstNumber && tile.secondNumber === this.secondNumber) || (tile.firstNumber === this.secondNumber && tile.secondNumber === this.firstNumber);
    }

    toString() {
        return `(${this.firstNumber}, ${this.secondNumber})`;
    }
}
