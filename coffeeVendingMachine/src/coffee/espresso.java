package coffee;
import enums.ingredients;
import java.util.Map;

public class espresso extends mainCoffee {
    
    public espresso() {
        this.coffeeName = "espresso";
    }

    public Integer price() {
        return 10;
    }

    public Map<ingredients, Integer> ingredients() {
        return Map.of(ingredients.COFFEE, 2, ingredients.WATER, 3);
    }
}