
import Service.*;
import Entities.*;
import State.*;

public class vendingMachineDemo {
    public static void main(String[] args) {
        vendingMachine vm = vendingMachine.getInstance();
        vm.addItem("1", "Coke", 2, 10);
        vm.addItem("2", "Pepsi", 3, 20);
        vm.addItem("3", "Fanta", 4, 30);

        vm.selectItem("1");
        vm.insertCoin(coin.coinType.QUARTER);
        vm.despenseItem();

        vm.updateItem("2", null, 30, 0);

        Integer price = vm.getPrice("2");
        System.out.println(price);
    }
}