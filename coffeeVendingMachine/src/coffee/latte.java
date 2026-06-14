package coffee;

import java.util.Map;

import enums.ingredients;

public class latte extends mainCoffee {
    
    public latte() {
        this.coffeeName = "latte";
    }

    public Integer price() {
        return 20;
    }

    public Map<ingredients, Integer> ingredients() {
        return Map.of(ingredients.COFFEE, 1, ingredients.WATER, 2, ingredients.SUGAR, 1, ingredients.MILK, 3);
    }
}