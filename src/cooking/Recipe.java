package cooking;

import java.util.ArrayList;
import java.util.List;

/**
 * Recipe class. Contains a name, people served.
 * Also includes ingredients, utensils, and steps of a recipe.
 *
 * @author f24team3d
 * @version 10/24/24
 */
public class Recipe
{
  private String name; // Name of the recipe
  private int serves; // Number of people served
  private List<Ingredients> ingredients;
  private List<Utensils> utensils;
  private List<Steps> steps;

  /**
   * Default constructor.
   */
  public Recipe()
  {
    this.name = "";
    this.serves = 0;
    this.ingredients = new ArrayList<>();
    this.utensils = new ArrayList<>();
    this.steps = new ArrayList<>();
  }

  /**
   * Constructor with parameters.
   *
   * @param name
   * @param serves
   */
  public Recipe(String name, int serves)
  {
    this.name = name;
    this.serves = serves;
    this.ingredients = new ArrayList<>();
    this.utensils = new ArrayList<>();
    this.steps = new ArrayList<>();
  }

  /**
   * Get the name of the recipe.
   *
   * @return the name of the recipe
   */
  public String getName()
  {
    return name;
  }

  /**
   * Set the name of the recipe.
   *
   * @param name
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Get the number of people served.
   *
   * @return the number of people served
   */
  public int getServes()
  {
    return serves;
  }

  /**
   * Set the number of people served.
   *
   * @param serves
   */
  public void setServes(int serves)
  {
    this.serves = serves;
  }

  /**
   * Add an ingredient to the recipe.
   *
   * @param ingredient
   */
  public void addIngredient(Ingredients ingredient)
  {
    ingredients.add(ingredient);
  }

  /**
   * Get the ingredients of the recipe.
   *
   * @return the ingredients of the recipe
   */
  public List<Ingredients> getIngredients()
  {
    return ingredients;
  }

  /**
   * Add a utensil to the recipe.
   *
   * @param utensil
   */
  public void addUtensils(Utensils utensil)
  {
    utensils.add(utensil);
  }

  public void removeUtensils(Utensils utensil)
  {
    utensils.remove(utensil);
  }

  public void removeIngredients(Ingredients ingredient)
  {
    ingredients.remove(ingredient);
  }

  public void removeSteps(Steps step)
  {
    steps.remove(step);
  }

  /**
   * Get the utensils of the recipe.
   *
   * @return the utensils of the recipe
   */
  public List<Utensils> getUtensils()
  {
    return utensils;
  }

  /**
   * Add a step to the recipe.
   *
   * @param step
   */
  public void addStep(Steps step)
  {
    steps.add(step);
  }

  /**
   * Get the steps of the recipe.
   *
   * @return the steps of the recipe
   */
  public List<Steps> getSteps()
  {
    return steps;
  }

  /**
   * Get the scaling factor for the recipe.
   *
   * @param newServings
   * @return the scaling factor for the recipe
   */
  public double getScalingFactor(int newServings)
  {
    if (serves == 0)
    {
      throw new IllegalArgumentException("Current serving cannot be zero");
    }

    return (double) newServings / serves; // return the correct amount of serving per person
  }
}
