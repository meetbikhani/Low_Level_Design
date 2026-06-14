package coffee;
import enums.ingredients;
import java.util.Map;

public abstract class mainCoffee {

    protected String coffeeName;

    public String getCoffeeName() {
        return coffeeName;
    }
    public abstract Integer price();
    public abstract Map<ingredients, Integer> ingredients();

    public void prepare() {
        System.out.println("Preparing coffee");
        System.out.println("Coffee: " + getCoffeeName());
        ingredients().forEach((key, value) -> System.out.println(key.toString() + ": " + value));
        System.out.println("Price: " + price());
        System.out.println("Coffee ready to be served");
    }
}