package cooking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Meal implements Serializable
{
  private static final long serialVersionUID = -3270513840760669938L;

  private String name;
  private List<Recipe> recipes;

  public Meal()
  {
    name = "";
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
   * Return name of meal.
   * 
   * @return Name of meal
   */
  public String getName()
  {
    return name;
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
    // add checking for available recipe
    if (recipes.contains(recipe))
    {
      recipes.remove(recipe);
    }
  }

  public void setName(String name)
  {
    this.name = name;
  }
}
