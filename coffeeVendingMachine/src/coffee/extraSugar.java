package coffee;

import java.util.HashMap;
import java.util.Map;
import enums.*;

public class extraSugar extends coffeeDecorator {
    
    public extraSugar(mainCoffee mainCoffee) {
        super(mainCoffee);
    }

    public Integer price() {
        return decoratedCoffee.price() + 2;
    }

    public Map<ingredients, Integer> ingredients() {
        // Map<ingredients, Integer> ingredients = decoratedCoffee.ingredients();
        // ingredients.merge(enums.ingredients.SUGAR, 1, Integer::sum);
        // return ingredients;

        Map<ingredients, Integer> recipe = new HashMap<>(decoratedCoffee.ingredients());
        recipe.merge(enums.ingredients.SUGAR, 1, Integer::sum);
        return recipe;
    }

    @Override
    public String getCoffeeName() {
        return decoratedCoffee.getCoffeeName() + " + Extra Sugar";
    }
}