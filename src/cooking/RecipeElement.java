package cooking;

/**
 * An interface for RecipeElements.
 * Allows grouping of Ingredients, Utensils, and Steps.
 *
 *  @author f24team3d
 *  @version 10/29/24
 */
public interface RecipeElement
{
  /**
   * Get the details of the RecipeElement.
   *
   * @return the details of the RecipeElement
   */
  public String getDetails();

  /**
   * Get the name and details of the RecipeElement.
   *
   * @return the name and details of the RecipeElement
   */
  public String toString();
}
