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
  public boolean addRecipe(Recipe recipe)
  {
    boolean exists = false;

    for (Recipe r : recipes)
    {
      if (r.getName().equals(recipe.getName()))
      {
        exists = true;
        // System.out.println("this recipe exists");
      }
    }

    if (!exists)
    {
      recipes.add(recipe);
      // System.out.println("Should not get here");
    }

    return exists;
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
  public void removeRecipe(String recipeName)
  {
    // add checking for available recipe
    for (Recipe r : recipes)
    {
      if (r.getName().equals(recipeName))
      {
        recipes.remove(r);
        return;
      }
    }
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void printRecipes()
  {
    for (Recipe r : recipes)
    {
      System.out.println(r.getName());
    }
  }
}
