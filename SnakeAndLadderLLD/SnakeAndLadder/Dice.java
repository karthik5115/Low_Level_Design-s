interface Dice {
    int roll();
}

class SixSidedDice implements Dice {
    int numberOfDices;

    SixSidedDice(int numberOfDices) {
        this.numberOfDices = numberOfDices;
    }

    public int roll() {
        int sum = 0;
        for (int i = 0; i < numberOfDices; i++) {
            sum += (int) (Math.random() * 6) + 1;
        }
        return sum;
    }
}