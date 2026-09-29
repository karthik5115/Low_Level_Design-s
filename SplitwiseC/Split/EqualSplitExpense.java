package Split;

import java.util.*;

public class EqualSplitExpense implements SplitExpense {

    @Override
    public void validateSplitExpense(List<Split> splitsList, double totalAmount) {

        // validate total amount in splits of each user is equal and overall equals to
        // totalAmount or not
        double amountShouldBePresent = totalAmount / splitsList.size();
        for (Split split : splitsList) {
            if (split.getAmountOwe() != amountShouldBePresent) {
                throw new IllegalArgumentException("Invalid split amount");
            }
        }

    }

}
