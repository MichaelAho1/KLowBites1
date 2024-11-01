package cooking;

/**
 * Utensils Class, a RecipeElement.
 *
 *  @author f24team3d
 *  @version 10/29/24
 */
public class Utensils implements RecipeElement, StepSource
{
  private String name;
  private String details;
  private RecipeElementType type = RecipeElementType.UTENSIL;

  /**
   * Default constructor.
   */
  public Utensils()
  {
    this.name = "";
    this.details = "";
  }

  /**
   * Constructor with parameters.
   *
   * @param name
   * @param details
   */
  public Utensils(final String name, final String details)
  {
    this.name = name;
    this.details = details;
  }

  /**
   * Get the name of the utensil.
   *
   * @return name
   */
  public String getName()
  {
    return name;
  }

  public RecipeElementType getType()
  {
    return type;
  }

  /**
   * Set the name of the utensil.
   *
   * @param name
   */
  public void setName(final String name)
  {
    this.name = name;
  }

  /**
   * Get the details of the utensil.
   *
   * @return details
   */
  public String getDetails()
  {
    return details;
  }

  /**
   * Set the details of the utensil.
   *
   * @param details
   */
  public void setDetails(final String details)
  {
    this.details = details;
  }

  @Override
  public String toString()
  {
    return details + " " + name;
  }
}
