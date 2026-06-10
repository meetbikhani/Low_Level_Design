package Service;
import Entities.*;
import State.*;

public class vendingMachine {
    private static vendingMachine instance;
    private inventory inventory;
    private machineState state;
    private item currentItem;
    private coin.coinType coinType;

    private vendingMachine() {
        this.inventory = new inventory();
        this.state = new idleState(this);
    }

    public static synchronized vendingMachine getInstance() {
        if (instance == null) {
            instance = new vendingMachine();
        }
        return instance;
    }

    public inventory getInventory() {
        return inventory;
    }

    public void setState(machineState state) {
        this.state = state;
    }

    public void setCurrentItem(item item) {
        this.currentItem = item;
    }

    public void setCoinType(coin.coinType coinType) {
        this.coinType = coinType;
    }

    public void despenseItem() {
        currentItem.setQuantity(currentItem.getQuantity() - 1);
        if(currentItem.getQuantity() == 0){
            inventory.removeItem(currentItem.getCode());
        }
        System.out.println("Item despensed and returning change");
    }

    public item getCurrentItem() {
        return currentItem;
    }

    public item addItem(String code, String name, int price, int quantity) {
        item item = new item(code, name, price, quantity);
        inventory.addItem(item);
        System.out.println("Item added successfully"+ item.getName());
        return item;
    }

    public void removeItem(String code) {
        inventory.removeItem(code);
    }

    public void updateItem(String code, String name, int price, int quantity) {
        item item = inventory.getItem(code);
        if(item == null){
            System.out.println("Item not found");
        }
        else{
            if(name != null){
                item.setName(name);
                System.out.println("Item name updated"+ item.getName());
            }
            if(price != 0){
                item.setPrice(price);
                System.out.println("Item price updated"+ item.getPrice());
            }
            if(quantity != 0){
                item.setQuantity(quantity);
                System.out.println("Item quantity updated"+ item.getQuantity());
            }
        }
    }

    public void selectItem(String code) {
        state.selectItem(code);
        System.out.println("Item selected"+ currentItem.getName());
    }

    public void insertCoin(coin.coinType type) {
        state.insertCoin(type);
        System.out.println("Coin inserted"+ coinType.getValue());
    }

    public int getPrice(String code) {
        item item = inventory.getItem(code);
        if(item == null){
            System.out.println("Item not found");
        }
        return item.getPrice();
    }
}