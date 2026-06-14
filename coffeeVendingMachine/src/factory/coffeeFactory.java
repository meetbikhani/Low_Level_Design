package factory;

import enums.coffeeType;
import coffee.*;

public class coffeeFactory {

    public mainCoffee createCoffee(coffeeType type) {
        switch (type) {
            case LATTE:
                return new latte();
            case CAPPUCCINO:
                return new cappuccino();
            case ESPRESSO:
                return new espresso();
            default:
                return null;
        }
    }
}