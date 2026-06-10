package State;

import Entities.*;
import Service.*;

public class selectItemState extends machineState {
    public selectItemState(vendingMachine vendingMachine, item item) {
        super(vendingMachine);
    }

    @Override
    public void selectItem(String code) {
        System.out.println("Item selected");
    }

    @Override
    public void insertCoin(coin.coinType type) {
        System.out.println("Coin inserted");
        if(vendingMachine.getCurrentItem().getPrice() > type.getValue()){
            System.out.println("Coin value is less than item price");
        }
        else{
            vendingMachine.setCoinType(type);
            vendingMachine.setState(new hasMoneyState(vendingMachine));
        }
    }

    @Override
    public void dispenseItem() {
        System.out.println("First insert coin");
    }
}