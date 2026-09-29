package Expense;

import java.util.List;
import Split.SplitExpense;
import Split.SplitFactory;
import Balance.BalanceSheetController;
import Split.Split;
import User.User;

public class ExpenseController {
    BalanceSheetController balanceSheetController;

    public ExpenseController() {
        this.balanceSheetController = new BalanceSheetController();
    }

    public Expense createExpense(String expenseId, String expenseDescription, double expenseAmount, User paidByUser,
            ExpenseSplitType splitExpenseType, List<Split> splitDetails) {
        SplitExpense splitExpense = SplitFactory.getSplitExpense(splitExpenseType);
        splitExpense.validateSplitExpense(splitDetails, expenseAmount);
        Expense expense = new Expense(expenseId, expenseDescription, expenseAmount, paidByUser, splitExpenseType,
                splitDetails);
        balanceSheetController.updateBalanceSheet(paidByUser, splitDetails, expenseAmount);
        return expense;
    }

}
