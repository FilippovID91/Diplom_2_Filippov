package user_and_order_pojo;

import java.util.List;

public class OrderCreate {
    private List<String> ingredients;

    public OrderCreate(List<String> ingredients) {

        this.ingredients = ingredients;
    }

    public List<String> getIngredients() {

        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {

        this.ingredients = ingredients;
    }

    public static OrderCreate testOrder() {
        return new OrderCreate(List.of("61c0c5a71d1f82001bdaaa71", "61c0c5a71d1f82001bdaaa72", "61c0c5a71d1f82001bdaaa78", "61c0c5a71d1f82001bdaaa6e"));
    }
    public static OrderCreate nullIngredientsOrder() {
        return new OrderCreate(List.of());
    }
    public static OrderCreate wrongIdIngredientsOrder() {
        return new OrderCreate(List.of("TEST", "тест"));
    }
}