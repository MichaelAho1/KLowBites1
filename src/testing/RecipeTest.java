package testing;
import cooking.Ingredients;
import cooking.Recipe;
import cooking.Utensils;


import java.util.List;

public class RecipeTest {

    public static void main(String[] args) {
        testUtensilSorting();
        testIngredientSorting();
    }

    public static void testUtensilSorting() {
        // Create a new recipe
        Recipe recipe = new Recipe("Test Recipe", 4);

        // Add unsorted utensils
        recipe.addUtensils(new Utensils("Pan", "Frying"));
        recipe.addUtensils(new Utensils("Marmit", "Cooking"));
        recipe.addUtensils(new Utensils("Bowl", "Mixing"));

        // Get the list of sorted utensils
        List<Utensils> utensils = recipe.getUtensils();

        // Print the utensils to check if they are sorted
        System.out.println("Utensils after sorting:");
        for (Utensils utensil : utensils) {
            System.out.println(utensil.getName() + " - " + utensil.getDetails());
        }

        // Simple check if sorted correctly
        if (utensils.get(0).getName().equals("Bowl") &&
            utensils.get(1).getName().equals("Marmit") &&
            utensils.get(2).getName().equals("Pan")) {
            System.out.println("Utensils are sorted correctly!");
        } else {
            System.out.println("Utensils are NOT sorted correctly!");
        }
    }

    public static void testIngredientSorting() {
        // Create a new recipe
        Recipe recipe = new Recipe("Test Recipe", 4);

        // Add unsorted ingredients
        recipe.addIngredient(new Ingredients(500, "grams", "", "Flour"));
        recipe.addIngredient(new Ingredients(200, "grams", "", "Sugar"));
        recipe.addIngredient(new Ingredients(250, "grams", "", "Butter"));

        // Get the list of sorted ingredients
        List<Ingredients> ingredients = recipe.getIngredients();

        // Print the ingredients to check if they are sorted
        System.out.println("Ingredients after sorting:");
        for (Ingredients ingredient : ingredients) {
            System.out.println(ingredient.getName() + " - " + ingredient.getAmount() + " " + ingredient.getUnit());
        }

        // Simple check if sorted correctly
        if (ingredients.get(0).getName().equals("Butter") &&
            ingredients.get(1).getName().equals("Flour") &&
            ingredients.get(2).getName().equals("Sugar")) {
            System.out.println("Ingredients are sorted correctly!");
        } else {
            System.out.println("Ingredients are NOT sorted correctly!");
        }
    }
}
