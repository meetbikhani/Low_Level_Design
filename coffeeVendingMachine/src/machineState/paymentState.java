package machineState;
import coffee.mainCoffee;
import service.coffeeMachine;

public class paymentState extends machineState {
    public paymentState(coffeeMachine coffeeMachine) {
        super(coffeeMachine);
    }

    @Override
    public void dispense() {
        this.coffeeMachine.getCurrentCoffee().prepare();
        System.out.println("Now remove ingredients");
        this.coffeeMachine.getCurrentCoffee().ingredients().forEach((key, value) -> {
            this.coffeeMachine.getInventory().removeIngredient(key, value);
        });
    }

    @Override
    public void payment(Integer amount) {
        System.out.println("Payment");
    }

    @Override
    public void selectCoffee(mainCoffee coffee) {
        System.out.println("Select coffee");
    }
}