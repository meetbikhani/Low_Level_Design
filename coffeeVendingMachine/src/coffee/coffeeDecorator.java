package coffee;
import enums.ingredients;
import java.util.Map;


public class coffeeDecorator extends mainCoffee {
    protected mainCoffee decoratedCoffee;
    
    public coffeeDecorator(mainCoffee mainCoffee) {
        this.decoratedCoffee = mainCoffee;
    }

    public Integer price() {
        return decoratedCoffee.price();
    }

    public Map<ingredients, Integer> ingredients() {
        return decoratedCoffee.ingredients();
    }

    
}