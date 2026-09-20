package model;

public class User {
    private Card card;

    public User(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return this.card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
