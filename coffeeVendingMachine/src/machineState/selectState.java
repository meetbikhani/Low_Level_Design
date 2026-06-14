package machineState;
import service.coffeeMachine;
import coffee.mainCoffee;

public class selectState extends machineState {
    public selectState(coffeeMachine coffeeMachine) {
        super(coffeeMachine);
    }

    @Override
    public void dispense() {
        System.out.println("Payment first");
    }

    @Override
    public void payment(Integer amount) {
        if(this.coffeeMachine.currentCoffee.price() > amount) {
            System.out.println("Amount is less than price");
            return;
        }
        else if(this.coffeeMachine.currentCoffee.price() < amount) {
            System.out.println("Returning change equal to price");
            Integer change = amount - this.coffeeMachine.currentCoffee.price();
            System.out.println(change);
        }
        this.coffeeMachine.isPaymentDone=true;
        this.coffeeMachine.setState(new paymentState(this.coffeeMachine));
    }

    @Override
    public void selectCoffee(mainCoffee coffee) {
        System.out.println("Payment first");
    }
}