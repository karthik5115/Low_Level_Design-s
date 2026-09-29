package Split;

import java.util.*;

public class PercentageSplitExpense implements SplitExpense {

    @Override
    public void validateSplitExpense(List<Split> splitsList, double totalAmount) {
        double sumOfSplits = 0;
        for (Split split : splitsList) {
            sumOfSplits += split.getAmountOwe();
        }
        if (sumOfSplits != totalAmount) {
            throw new IllegalArgumentException("Invalid split amount");
        }

    }
}
