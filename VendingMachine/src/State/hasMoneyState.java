package State;
import Entities.*;
import Service.*;

public class hasMoneyState extends machineState {
    public hasMoneyState(vendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void selectItem(String code) {
        System.out.println("Item selected");
    }

    @Override
    public void insertCoin(coin.coinType type) {
        System.out.println("Coin inserted");
    }

    @Override
    public void dispenseItem() {
        System.out.println("Item dispensed");
        vendingMachine.despenseItem();
        vendingMachine.setState(new idleState(vendingMachine));
    }
}