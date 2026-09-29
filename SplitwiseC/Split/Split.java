package Split;

import User.User;

public class Split {
    private User user;
    private double amountOwe;

    public Split(User user, double amountOwe) {
        this.user = user;
        this.amountOwe = amountOwe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmountOwe() {
        return amountOwe;
    }

    public void setAmountOwe(double amountOwe) {
        this.amountOwe = amountOwe;
    }

    @Override
    public String toString() {
        return "Split [user=" + user + ", amountOwe=" + amountOwe + "]";
    }

}
