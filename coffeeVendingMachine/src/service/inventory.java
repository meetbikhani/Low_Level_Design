package service;
import java.util.Map;
import java.util.HashMap;

public class inventory {
    Map<enums.ingredients, Integer> ingredients;
    
    public inventory() {
        this.ingredients = new HashMap<>();
    }

    public void addIngredient(enums.ingredients ingredient, Integer amount) {
        this.ingredients.put(ingredient, amount);
    }

    public Integer getIngredient(enums.ingredients ingredient) {
        return this.ingredients.get(ingredient);
    }

    public void removeIngredient(enums.ingredients ingredient, Integer amount) {
        this.ingredients.put(ingredient, this.ingredients.get(ingredient) - amount);
    }

    public Boolean isAvailable(enums.ingredients ingredient, Integer amount) {
        if(this.ingredients.get(ingredient) < amount) {
            System.out.println("Item is out of stock");
            return false;
        }
        return true;
    }

    public void printInventory() {
        if(this.ingredients.isEmpty()) {
            System.out.println("Inventory is empty");
            return;
        }
        this.ingredients.forEach((key, value) -> {
            System.out.println(key.toString() + ": " + value);
        });
    }
}