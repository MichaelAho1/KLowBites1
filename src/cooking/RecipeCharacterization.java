package cooking;

import cooking.Ingredients;
import cooking.Steps;
import cooking.Utensils;

import java.util.ArrayList;
import java.util.List;

public class RecipeCharacterization
{
  private String name;    // Name of the recipe
  private int serves;     // Number of people served
  private List<Ingredients> ingredients;
  private List<Utensils> utensils;
  private List<Steps> steps;
  
  public RecipeCharacterization(String name, int serves) {
    this.name = name;
    this.serves = serves;
    this.ingredients = new ArrayList<>();
    this.utensils = new ArrayList<>();
    this.steps = new ArrayList<>();
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getServes() {
    return serves;
  }
  
  public void setServes(int serves) {
    this.serves = serves;
  }
  
  public void addIngredient(Ingredients ingredient) {
    ingredients.add(ingredient);
  }
}
