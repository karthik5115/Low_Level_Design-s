package Split;

import Expense.ExpenseSplitType;

public class SplitFactory {
    public static SplitExpense getSplitExpense(ExpenseSplitType expenseSplitType) {
        switch (expenseSplitType) {
            case EQUAL:
                return new EqualSplitExpense();
            case UNEQUAL:
                return new UnequalSplitExpense();
            case PERCENTAGE:
                return new PercentageSplitExpense();
            default:
                throw new IllegalArgumentException("Invalid split type");
        }
    }
}