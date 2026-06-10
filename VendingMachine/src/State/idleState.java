package State;
import Entities.*;
import Service.*;

public class idleState extends machineState {
    
    public idleState(vendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void selectItem(String code) {
        item item = vendingMachine.getInventory().getItem(code);
        if(item == null){
            System.out.println("Item not found");
        }
        else{
            if(vendingMachine.getInventory().isAvailable(code) == 0){
                System.out.println("Item is out of stock");
            }
            else{
                vendingMachine.setCurrentItem(item);
                vendingMachine.setState(new selectItemState(vendingMachine, item));
            }
        }
    }

    @Override
    public void insertCoin(coin.coinType type) {
        System.out.println("First select item");
    }

    @Override
    public void dispenseItem() {
        System.out.println("First select item");
    }
}