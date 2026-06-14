package service;
import machineState.*;
import coffee.*;
import factory.coffeeFactory;
import java.util.List;

public class coffeeMachine {
    private static coffeeMachine instance;
    inventory inventory;
    machineState state;
    public mainCoffee currentCoffee;
    coffeeFactory coffeeFactory;
    public Boolean isPaymentDone=false;

    public static coffeeMachine getInstance() {
        if (instance == null) {
            instance = new coffeeMachine();
        }
        return instance;
    }
    
    public coffeeMachine() {
        this.inventory = new inventory();
        this.state = new idealState(this);
        this.coffeeFactory = new coffeeFactory();
    }

    public inventory getInventory() {
        return inventory;
    }

    public void setState(machineState state) {
        this.state = state;
    }

    public void selectCoffee(enums.coffeeType type, List<enums.extra> decorators) {
        mainCoffee selectedCoffee = coffeeFactory.createCoffee(type);

        for (enums.extra decorator : decorators) {
            switch (decorator) {
                case SUGAR:
                    selectedCoffee = new extraSugar(selectedCoffee);
                    break;
                case COFFEE:
                    selectedCoffee = new extraCoffee(selectedCoffee);
                    break;
            }
        }
        state.selectCoffee(selectedCoffee);
    }

    public void setCurrentCoffee(mainCoffee coffee) {
        this.currentCoffee = coffee;
    }

    public mainCoffee getCurrentCoffee() {
        return currentCoffee;
    }

    public void insertAmount(Integer amount) {
        // System.out.println(this.currentCoffee);
        if(this.currentCoffee == null) {
            System.out.println("No coffee selected");
            return;
        }
        state.payment(amount);
    }

    public void dispenseCoffee() {
        if(this.currentCoffee == null) {
            System.out.println("No coffee selected");
            return;
        }
        if(!this.isPaymentDone) {
            System.out.println("Payment not done");
            return;
        }
        state.dispense();
        System.out.println("Coffee dispensed");
    }

    public void addIngredient(enums.ingredients ingredient, Integer amount) {
        inventory.addIngredient(ingredient, amount);
    }

    public void showAllIngredients() {
        inventory.printInventory();
    }
}