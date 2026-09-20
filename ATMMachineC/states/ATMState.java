package states;

import model.Card;

public interface ATMState {
    public Card InsertCard(Card card);

    public boolean AuthenticatePin(Card card);

    public void Withdraw(Card card);

    public void SetATMPin(Card card);

    public void Deposit(Card card);

    public void Cancel(Card card);

    public void OptionstoSelect();

    public void ReturnCard(Card card);

    public void checkBalance(Card card);

    public void returntoOptions();

    public void cashDispense(double amt);
}
