package Expense;

import java.util.List;
import User.User;
import Split.Split;

public class Expense {
    private String expenseId;
    private String expenseDescription;
    private double expenseAmount;
    private User paidByUser;
    private ExpenseSplitType splitExpenseType;
    private List<Split> splitDetails;

    public Expense(String expenseId, String expenseDescription, double expenseAmount, User paidByUser,
            ExpenseSplitType splitExpenseType, List<Split> splitDetails) {
        this.expenseId = expenseId;
        this.expenseDescription = expenseDescription;
        this.expenseAmount = expenseAmount;
        this.paidByUser = paidByUser;
        this.splitExpenseType = splitExpenseType;
        this.splitDetails = splitDetails;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public User getPaidByUser() {
        return paidByUser;
    }

    public void setPaidByUser(User paidByUser) {
        this.paidByUser = paidByUser;
    }

    public ExpenseSplitType getSplitExpenseType() {
        return splitExpenseType;
    }

    public void setSplitExpenseType(ExpenseSplitType splitExpenseType) {
        this.splitExpenseType = splitExpenseType;
    }

    public List<Split> getSplitDetails() {
        return splitDetails;
    }

    public void setSplitDetails(List<Split> splitDetails) {
        this.splitDetails = splitDetails;
    }

    @Override
    public String toString() {
        return "Expense [expenseId=" + expenseId + ", expenseDescription=" + expenseDescription + ", expenseAmount="
                + expenseAmount + ", paidByUser=" + paidByUser + ", splitExpenseType=" + splitExpenseType
                + ", splitDetails=" + splitDetails + "]";
    }

}
