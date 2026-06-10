package State;
import Entities.*;
import Service.*;

public abstract class machineState {
    vendingMachine vendingMachine;
    public machineState(vendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public void selectItem(String code) {}
    public void insertCoin(coin.coinType type) {}
    public void dispenseItem() {}
}