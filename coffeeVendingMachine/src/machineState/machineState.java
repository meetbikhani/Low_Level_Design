package machineState;
import coffee.mainCoffee;
import service.coffeeMachine;

public abstract class machineState {
    protected coffeeMachine coffeeMachine;
    
    public machineState(coffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    public coffeeMachine getCoffeeMachine() {
        return coffeeMachine;
    }

    public abstract void dispense();
    public abstract void payment(Integer amount);
    public abstract void selectCoffee(mainCoffee coffee);
}