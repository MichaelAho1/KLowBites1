package cooking;

/**
 * Enum for RecipeElementType.
 * Contains the types of RecipeElements.
 *
 * @author f24team3d
 * @version 10/31/24
 */
public enum RecipeElementType
{
  INGREDIENT("Ingredient", "Ingredient Delete"),
  UTENSIL("Utensil", "Utensil Delete"),
  STEP("Step", "Step Delete");

  String label;
  String deleteCommand;

  /**
   * Constructor for RecipeElementType.
   *
   * @param label the label of the RecipeElementType
   * @param deleteCommand the delete command of the RecipeElementType
   */
  private RecipeElementType(final String label, final String deleteCommand)
  {
    this.label = label;
    this.deleteCommand = deleteCommand;
  }

  /**
   * Get the label of the RecipeElementType.
   *
   * @param plural
   * @return the label of the RecipeElementType
   */
  public String getLabel(boolean plural)
  {
    if (plural)
    {
      return label + "s";
    }
    return label;
  }

  /**
   * Get the delete command of the RecipeElementType.
   *
   * @return the delete command of the RecipeElementType
   */
  public String getDeleteCommand()
  {
    return deleteCommand;
  }
}