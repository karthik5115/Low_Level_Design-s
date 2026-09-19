import java.util.List;

public interface StateInterface {
    void insertCoin(VendingMachine machine, Coin coin) throws Exception;

    void productSelectionButton(VendingMachine machine) throws Exception;

    void selectProduct(VendingMachine machine, int code) throws Exception;

    int getChange(VendingMachine machine, int returnChangeMoney) throws Exception;

    List<Coin> getFullRefund(VendingMachine machine) throws Exception;

    Product dispenseProduct(VendingMachine machine, int code) throws Exception;
}