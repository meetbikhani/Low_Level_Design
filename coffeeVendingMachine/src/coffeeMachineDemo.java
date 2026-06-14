import service.*;
import java.util.*;

public class coffeeMachineDemo {
    public static void main(String[] args) {
        coffeeMachine coffeeKaMachine = coffeeMachine.getInstance();
        // coffeeKaMachine.showAllIngredients();
        // coffeeKaMachine.addIngredient(enums.ingredients.SUGAR, 2);
        // coffeeKaMachine.addIngredient(enums.ingredients.COFFEE, 2);
        // coffeeKaMachine.addIngredient(enums.ingredients.WATER, 2);
        // coffeeKaMachine.addIngredient(enums.ingredients.MILK, 2);
        // System.out.println("**************");
        // coffeeKaMachine.selectCoffee(enums.coffeeType.LATTE, List.of(enums.extra.SUGAR));
        // System.out.println("**************");
        // coffeeKaMachine.insertAmount(30);
        // System.out.println("**************");
        // coffeeKaMachine.dispenseCoffee();
        // System.out.println("**************");
        // coffeeKaMachine.showAllIngredients();



        // coffeeKaMachine.showAllIngredients();
        // coffeeKaMachine.addIngredient(enums.ingredients.SUGAR, 3);
        // coffeeKaMachine.addIngredient(enums.ingredients.COFFEE, 3);
        // coffeeKaMachine.addIngredient(enums.ingredients.WATER, 3);
        // coffeeKaMachine.addIngredient(enums.ingredients.MILK, 3);
        // System.out.println("**************");
        // coffeeKaMachine.selectCoffee(enums.coffeeType.LATTE, List.of(enums.extra.SUGAR));
        // System.out.println("**************");
        // coffeeKaMachine.insertAmount(30);
        // System.out.println("**************");
        // coffeeKaMachine.dispenseCoffee();
        // System.out.println("**************");
        // coffeeKaMachine.showAllIngredients();


        coffeeKaMachine.showAllIngredients();
        coffeeKaMachine.addIngredient(enums.ingredients.SUGAR, 3);
        coffeeKaMachine.addIngredient(enums.ingredients.COFFEE, 3);
        coffeeKaMachine.addIngredient(enums.ingredients.WATER, 3);
        coffeeKaMachine.addIngredient(enums.ingredients.MILK, 3);
        System.out.println("**************");
        coffeeKaMachine.selectCoffee(enums.coffeeType.LATTE, List.of(enums.extra.SUGAR));
        System.out.println("**************");
        coffeeKaMachine.insertAmount(10);
        System.out.println("**************");
        coffeeKaMachine.dispenseCoffee();
        System.out.println("**************");
        coffeeKaMachine.showAllIngredients();
    }
}