package coffee;

import java.util.Map;

import enums.ingredients;

public class cappuccino extends mainCoffee {
    
    public cappuccino() {
        this.coffeeName = "cappuccino";
    }

    public Integer price() {
        return 30;
    }

    public Map<ingredients, Integer> ingredients() {
        return Map.of(ingredients.COFFEE, 2, ingredients.WATER, 2, ingredients.SUGAR, 1, ingredients.MILK, 2);
    }
}