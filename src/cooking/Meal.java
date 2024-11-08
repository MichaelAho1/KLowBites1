package cooking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Meal implements Serializable
{
  private static final long serialVersionUID = -3270513840760669938L;

  private List<Recipe> recipes;

  public Meal()
  {
    recipes = new ArrayList<>();
  }

  /**
   * Add recipe to meal.
   * 
   * @param recipe
   *          Recipe being added to meal
   */
  public void addRecipe(Recipe recipe)
  {
    recipes.add(recipe);
  }

  /**
   * Retrieve recipes in meal.
   * 
   * @return Recipes in meal
   */
  public List<Recipe> getRecipes()
  {
    return recipes;
  }

  /**
   * Remove recipe from meal.
   * 
   * @param recipe
   *          Recipe being removed from meal
   */
  public void removeRecipe(Recipe recipe)
  {
    recipes.remove(recipe);
  }
}
