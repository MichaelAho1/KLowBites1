package cooking;

/**
 * Utensils Class, a RecipeElement.
 *
 *  @author f24team3d
 *  @version 10/29/24
 */
public class Utensils
{
  private String name;
  private String details;

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
  public Utensils(String name, String details) 
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

  /**
   * Set the name of the utensil.
   *
   * @param name
   */
  public void setName(String name)
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
  public void setDetails(String details)
  {
    this.details = details;
  }

  @Override
  public String toString()
  {
    return name + "(" + details + ")";
  }
}
