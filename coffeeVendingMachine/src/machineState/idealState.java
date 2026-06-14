package machineState;
import service.coffeeMachine;

import java.util.Map;

import coffee.mainCoffee;
import enums.ingredients;
import service.inventory;

public class idealState extends machineState {
    public idealState(coffeeMachine coffeeMachine) {
        super(coffeeMachine);
    }

    @Override
    public void dispense() {
        System.out.println("Select coffee first");
    }

    @Override
    public void payment(Integer amount) {
        System.out.println("Select coffee first");
    }

    @Override
    public void selectCoffee(mainCoffee coffee) {
        inventory inventory = this.coffeeMachine.getInventory();
        for(Map.Entry<ingredients, Integer> entry : coffee.ingredients().entrySet()) {
            if(!inventory.isAvailable(entry.getKey(), entry.getValue())) {
                System.out.println("Ingredient not available so i can't make this coffee");
                return;
            }
        }
        System.out.println("mai aaya hu yaha");
        this.coffeeMachine.setCurrentCoffee(coffee);
        this.coffeeMachine.setState(new selectState(this.coffeeMachine));
    }
}