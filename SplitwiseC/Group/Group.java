package Group;

import User.User;
import Expense.Expense;
import Expense.ExpenseController;
import Expense.ExpenseSplitType;
import Split.Split;

import java.util.ArrayList;
import java.util.List;

public class Group {

    String groupId;
    String groupName;
    List<User> groupMembers;

    List<Expense> expenseList;

    ExpenseController expenseController;

    Group() {
        groupMembers = new ArrayList<>();
        expenseList = new ArrayList<>();
        expenseController = new ExpenseController();
    }

    // add member to group
    public void addMember(User member) {
        groupMembers.add(member);
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Expense createExpense(String expenseId, String expenseDescription, double expenseAmount, User paidByUser,
            ExpenseSplitType splitExpenseType, List<Split> splitDetails) {

        Expense expense = expenseController.createExpense(expenseId, expenseDescription, expenseAmount, paidByUser,
                splitExpenseType, splitDetails);
        expenseList.add(expense);
        return expense;
    }
}
